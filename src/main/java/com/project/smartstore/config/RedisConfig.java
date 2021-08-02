package com.project.smartstore.config;



import com.project.smartstore.constants.CacheNameConstants;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/*
 * @EnableRedisHttpSession
 * - @EnableRedisHttpSession은 Filter를 구현한 springSessionRepositoryFilter라는 이름으로 Spring Bean을 생성합니다.
 *   springSessionRepositoryFilter은  Spring Session에서 지원하는 HttpSession구현을 대체하는 역할을합니다.
 */
@EnableCaching
@Configuration
@EnableRedisHttpSession
public class RedisConfig {

  @Value("${redis.host}")
  private String host;

  @Value("${redis.port}")
  private int port;


  /**
   * Lettuce는 Netty기반의 오픈소스 커넥터이며 Redis의 클라이언트 라이브러리입니다. 기본적으로 LettuceConnectionFactory에 의해 생성된 모든
   * LettuceConnection 인스턴스는 non-blocking / non-transaction / thread-safe한 연결을 공유합니다.
   */
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
  }

  /**
   * RedisTemplate - RedisTemplate은 주어진 객체와 Redis 저장소 간에 직렬화/역직렬화를 수앵합니다. 기본적으로
   * JdkSerializationRedisSerializerf를 통해 자바 직렬화를 사용하지만 , 문자열 위주의 작업의 경우 StringRedisTemplate을 사용합니다.
   * RedisTemplate 클래스는 RedisCallback 인터페이스의 구현이나, Redis connection을 검색하고 닫는 호출 코드를  신경을 쓸 필요가 없도록
   * RedisConnection 처리를 제공합니다. thread-safe합니다.
   */
  @Bean
  public RedisTemplate<String, Object> redisTemplate() {

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());

    return redisTemplate;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
    cacheConfigurationMap.put(CacheNameConstants.PRODUCT.toString(),
        defaultRedisCacheConfiguration().entryTtl(Duration.ofSeconds(600)));

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .withInitialCacheConfigurations(cacheConfigurationMap)
        .cacheDefaults(defaultRedisCacheConfiguration())
        .build();
  }

  private RedisCacheConfiguration defaultRedisCacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues()
        .computePrefixWith(CacheKeyPrefix.simple())
        .serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(RedisSerializationContext.SerializationPair
            .fromSerializer(new GenericJackson2JsonRedisSerializer()))
        .entryTtl(Duration.ofSeconds(180));
  }
}
