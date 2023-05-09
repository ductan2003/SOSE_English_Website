package com.elearningweb.admin.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.elearningweb.library.*", "com.elearningweb.admin.*"}
,exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(value = "com.elearningweb.library.repository")
@EntityScan(value = "com.elearningweb.library.model")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
