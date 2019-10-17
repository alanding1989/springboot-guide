package com.how2java.springboot.config;

import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *  Author      :   AlanDing
 *  Time        :   2019/10/1 上午9:53
 *  File        :   RedisConfig.java
 *  Description :
 */

// @Configuration
// @EnableCaching
// // Redis 缓存配置类
// public class RedisConfig1 extends CachingConfigurerSupport {
//
//     @Bean
//     public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
//         RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//         Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
//             new Jackson2JsonRedisSerializer<>(Object.class);
//
//         ObjectMapper om = new ObjectMapper();
//         om.setVisibility(PropertyAccessor.ALL, Visibility.PUBLIC_ONLY);
//         om.enableDefaultTyping(DefaultTyping.NON_FINAL);
//         jackson2JsonRedisSerializer.setObjectMapper(om);
//
//         redisTemplate.setKeySerializer(stringSerializer);
//         redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//         redisTemplate.setHashKeySerializer(stringSerializer);
//         redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//         RedisCacheWriter cw = new RedisCacheWriter() {};
//         return new RedisCacheManager();
//     }
// }

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    // @Bean
    // public RedisConnectionFactory redisConnectionFactory() {
    //     RedisStandaloneConfiguration factory = new RedisStandaloneConfiguration();
    //     factory.setHostName(host);
    //     factory.setPort(port);
    //     factory.setPassword(RedisPassword.of(password));
    //     return new JedisConnectionFactory(factory);
    // }

    @Bean
    public KeyGenerator primaryKeyGenerator() {
        // 主键生成策咯
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
