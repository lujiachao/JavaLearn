package org.example.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    // redis服务器地址
    @Value("${spring.redis.host}")
    private String host;

    // redis服务器端口
    @Value("${spring.redis.port}")
    private int port;

    // 密码
    @Value("${spring.redis.password}")
    private String password;

    // 超时时间
    @Value("${spring.redis.timeout}")
    private int timeout;

    // 数据库
    @Value("${spring.redis.database}")
    private int database;

    // redis最大空连接数
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    // redis最大空闲数
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    // redis最小空闲数
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
        logger.info("JedisPool连接成功:"+host+"\t"+port);
        return jedisPool;
    }
}
