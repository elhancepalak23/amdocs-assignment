package com.uxpsystems.assignment.config;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.uxpsystems" })
@EnableJpaRepositories(basePackages = { "com.uxpsystems.assignment.repository" })
@EntityScan(basePackages = { "com.uxpsystems.assignment.model" })
public class ApplicationConfig {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationConfig.class).bannerMode(Mode.LOG).run(args);
    }
}