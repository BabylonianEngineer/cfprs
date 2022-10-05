package com.hf.cfprs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.hf.cfprs.dao.es")
@EnableJpaRepositories(basePackages = "com.hf.cfprs.dao.mysql")
public class CfprsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfprsApplication.class, args);
    }

}
