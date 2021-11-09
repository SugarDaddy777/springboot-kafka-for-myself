package org.multicat.springbootkafkaformyself.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/10/28 9:33
 */
@Component
@PropertySource("classpath:/config/test.properties")
public class TestConfig {


    @Autowired
    private Environment environment;

    public void test() {
        String value = environment.getProperty("test.name.prefix");
        System.out.println(value);
    }

}
