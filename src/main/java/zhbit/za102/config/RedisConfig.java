package zhbit.za102.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
//Redis 缓存配置类（防止可视化工具查看redis时数据为乱码）
public class RedisConfig extends CachingConfigurerSupport {

    private RedisSerializer<String> keySerializer() {

        return new StringRedisSerializer();  //key值使用json序列化器
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();//value值也使用json序列化器
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
/*      不同的缓存对应不同的存储类型，不同的存储类型对应着不同的序列化和反序列化器，因此要统一使用的缓存；
        RedisCacheConfiguration 为容器注入了新的缓存管理器 RedisCacheManager ,其他的CacheManager将不在起作用。防止可视化工具查看为乱码；
        容器中由 ConcurrentMapCacheManager 变为 RedisCacheManager。*/
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))//设置缓存延时时间为30分钟
                .disableCachingNullValues()//如果是空值，不缓存
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))//设置key值序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));//设置value值序列化为json
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }
}
