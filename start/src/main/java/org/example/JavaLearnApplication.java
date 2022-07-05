package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@SpringBootApplication
@EnableScheduling
public class JavaLearnApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(JavaLearnApplication.class, args);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
