package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置 Key 和 Value 的序列化方式，这里使用默认的 JdkSerializationRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        // 注意：如果 Value 中包含了对象，则需要将对象类注册到 Redis 中以便正确序列化、反序列化对象实例
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }
}
