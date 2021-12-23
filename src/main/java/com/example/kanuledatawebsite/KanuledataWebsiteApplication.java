package com.example.kanuledatawebsite;

import com.example.kanuledatawebsite.configurations.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class KanuledataWebsiteApplication {


    @Autowired
    private JdbcTemplate jdbcTemplate;



    public static void main(String[] args) {
        SpringApplication.run(KanuledataWebsiteApplication.class, args);
    }

}
