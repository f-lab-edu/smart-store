package com.project.smartStore.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 *
 * @Configuration : 환경설정을 담당하는 클래스임을 스프링에게 알리는 어노테이션이며 컨테이너가 관리하는 Bean을 선언할 수 있습니다.
 * PathMatchingResourcePatternResolver클래스는  ResourcePatternResolver 인터페이스의 구현체이며 매칭되는 하나 이상의 리소스 경로를 찾도록 해줍니다.
 * classpath 또는 Ant 스타일 정규식 (Spring의 AntPathMatcher유틸리티를 사용하여 일치 )을 사용하여 찾을 수 있습니다.
 *
 */
@Configuration
@MapperScan(basePackages = "com.project.smartStore.mapper")
public class DatabaseConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{

		SqlSessionFactoryBean sessionFactory  = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));

		return sessionFactory.getObject();
	}
}
