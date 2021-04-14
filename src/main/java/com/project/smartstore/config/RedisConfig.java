package com.project.smartstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @EnableRedisHttpSession
 * - @EnableRedisHttpSession은 Filter를 구현한 springSessionRepositoryFilter라는 이름으로 Spring Bean을 생성합니다.
 *   springSessionRepositoryFilter은  Spring Session에서 지원하는 HttpSession구현을 대체하는 역할을합니다.
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {

	@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private int port;
	
	/**
	 * Lettuce는 Netty기반의 오픈소스 커넥터이며 Redis의 클라이언트 라이브러리입니다. 
	 * 기본적으로 LettuceConnectionFactory에 의해 생성된 모든 LettuceConnection 인스턴스는 non-blocking / non-transaction / thread-safe한 연결을 공유합니다. 
	 * @return
	 */
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}
}
