package org.multicat.springbootkafkaformyself.config;

import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/11/9 15:19
 */
@Configuration
public class RedissonConfig {

    @Bean
    public Config configRedisson() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:7181");
        return config;
    }
}
