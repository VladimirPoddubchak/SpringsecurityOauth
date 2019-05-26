package com.poddubchak.oauth.App;

import com.poddubchak.oauth.Security.TokenHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.poddubchak.oauth")
@EnableJpaRepositories(basePackages = "com.poddubchak.oauth.Repositories")
@EntityScan(basePackages = "com.poddubchak.oauth.Model")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    



}
