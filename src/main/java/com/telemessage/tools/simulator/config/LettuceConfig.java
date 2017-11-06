package com.telemessage.tools.simulator.config;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.resource.ClientResources;
import com.lambdaworks.redis.resource.DefaultClientResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Grinfeld Mikhail
 * @since 11/6/2017.
 */
@Configuration
public class LettuceConfig {

    @Bean(destroyMethod = "shutdown")
    ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean(destroyMethod = "shutdown")
    RedisClient redisClient(ClientResources clientResources) {
        return RedisClient.create(clientResources, RedisURI.create("host"));
    }

    @Bean(destroyMethod = "close")
    StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
        return redisClient.connect();
    }

    /*
    // sentinel
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {
      RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
      .master("mymaster")
      .sentinel("127.0.0.1", 26379)
      .sentinel("127.0.0.1", 26380);
      return new LettuceConnectionFactory(sentinelConfig);
    }

    // RedisSentinelConfiguration can also be defined via PropertySource.

    // Configuration Properties
    // spring.redis.sentinel.master: name of the master node.

    // spring.redis.sentinel.nodes: Comma delimited list of host:port pairs.
     */

}
