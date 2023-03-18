package com.kirilyuk.test_oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
public class TestOracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOracleApplication.class, args);
    }

}
