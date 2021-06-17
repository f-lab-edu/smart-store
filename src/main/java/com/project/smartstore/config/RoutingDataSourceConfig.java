package com.project.smartstore.config;

import com.project.smartstore.enumeration.ClientDatabaseType;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/*
 * @EnableTransactionManagement
 * : Spring의 annotation 기반 트랜잭션 관리 기능을 활성화합니다.
 * @PropertySource
 * : Spring의 환경에 PropertySource를 추가하기위한 편리하고 선언적인 메커니즘을 제공하는 annotation입니다.
 *   @Configuration 클래스와 함께 사용됩니다.
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class RoutingDataSourceConfig {

  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.master")
  public DataSource masterDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave")
  public DataSource slaveDataSource() {
    return DataSourceBuilder.create().build();
  }


  /**
    AbstractRoutingDataSource
    : 조회 키를 기반으로 대상 DataSource 중 하나를 getConnection()로 호출하여 라우팅합니다.
     AbstractRoutingDataSource는 targetDataSources라는 Map 오브젝트를 가지고 있는데
          여기에는 조회 키-DataSource 쌍들이 담겨져 있습니다. determineCurrentLookupKey()를
          호출하여 조회 키를 리턴받고, 해당 키로 targetDataSource에 저장된 DataSource 중
          라우팅할 DataSource가 결정합니다.
  */
  @Bean(name = "routingDataSource")
  public DataSource routingDataSource(@Qualifier(value = "masterDataSource")
                                      DataSource masterDataSource,
                                      @Qualifier(value = "slaveDataSource")
                                      DataSource slaveDataSource) {

    AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {

      @Override
      protected Object determineCurrentLookupKey() {
        boolean isReadonly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        return isReadonly ?  ClientDatabaseType.SLAVE : ClientDatabaseType.MASTER;
      }
    };

    Map<Object, Object> targetDataSources = new HashMap<>();

    targetDataSources.put(ClientDatabaseType.MASTER, masterDataSource);
    targetDataSources.put(ClientDatabaseType.SLAVE, slaveDataSource);

    routingDataSource.setTargetDataSources(targetDataSources);
    routingDataSource.setDefaultTargetDataSource(masterDataSource);

    return routingDataSource;
  }

  /*
     LazyConnectionDataSourceProxy
     : Spring은 기본적으로 트랜잭션을 시작할 때 쿼리를 실행하기 전에 DataSource를 정해놓습니다.
         그리고 Transaction은 일단 시작이 되면 같은 DataSource만을 이용하게 됩니다.
         쿼리 메소드에 따라서 DataSource를 선택하는 로직이 가능하려면 DataSource연결을 쿼리 메소드 실행 전까지 늦춰주도록 구현할 필요가 있습니다.
     LazyConnectionDataSourceProxy를 사용하면 트랜잭션이 시작 되더라도 실제로 커넥션이 필요한 경우에만 데이터소스에서 커넥션을 반환합니다.
     RoutingDataSource를 LazyConnectionDataSourceProxy로 감싸서 구현함으로 가능해진다.
   */
  @Bean(name = "proxyDataSource")
  public DataSource proxyDataSource(@Qualifier(value = "routingDataSource")
                                    DataSource routingDataSource) {

    return new LazyConnectionDataSourceProxy(routingDataSource);
  }

  @Bean
  public PlatformTransactionManager transactionManager(@Qualifier(value = "proxyDataSource")
                                                       DataSource dataSource) {

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);

    return transactionManager;
  }
}
