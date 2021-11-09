package org.multicat.springbootkafkaformyself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author admin
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringbootKafkaForMyselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaForMyselfApplication.class, args);
    }

}
