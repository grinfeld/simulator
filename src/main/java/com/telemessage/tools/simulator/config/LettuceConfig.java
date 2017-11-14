package com.telemessage.tools.simulator.config;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.resource.ClientResources;
import com.lambdaworks.redis.resource.DefaultClientResources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Grinfeld Mikhail
 * @since 11/6/2017.
 */
@Configuration
public class LettuceConfig {

    private static final int DEFAULT_SENTINAL_PORT = 6379;

    @Value("${simulator.redis.host}")
    private String host;

    @Value("${simulator.redis.sentinal.master}")
    private String sentinelMaster;

    @Value("${simulator.redis.sentinal.hosts}")
    private String sentinelHosts;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "simulator.redis.sentinal.enabled", havingValue = "false", matchIfMissing = true)
    ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "simulator.redis.sentinal.enabled", havingValue = "false", matchIfMissing = true)
    RedisClient redisClient(ClientResources clientResources) {
        return RedisClient.create(clientResources, RedisURI.create(host));
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "simulator.redis.sentinal.enabled", havingValue = "false", matchIfMissing = true)
    StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
        return redisClient.connect();
    }

    // sentinel
    @Bean
    @ConditionalOnProperty(name = "simulator.redis.sentinal.enabled", havingValue = "true")
    public RedisConnectionFactory lettuceConnectionFactory() {
        List<RedisNode> nodes = Arrays.stream(Optional.ofNullable(sentinelHosts).orElse("").split(","))
                .map(String::trim).map(LettuceConfig::getAddress).collect(Collectors.toList());
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(sentinelMaster);
        sentinelConfig.setSentinels(nodes);

        return new LettuceConnectionFactory(sentinelConfig);
    }

    private static RedisNode getAddress(String address) {
        try {
            address = address.contains("://") ? address : "http://" + address;
            URL u = new URL(address);
            return new RedisNode(u.getHost(), u.getPort() <= 0 ? DEFAULT_SENTINAL_PORT : u.getPort());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    // RedisSentinelConfiguration can also be defined via PropertySource.

    // Configuration Properties
    // spring.redis.sentinel.master: name of the master node.

    // spring.redis.sentinel.nodes: Comma delimited list of host:port pairs.
     */

}
