package com.buddy.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TutorialApplication {
    /**
     * main method of the application.
     *
     * @param args arguments of main method
     */
    public static void main(final String[] args) {

        SpringApplication.run(TutorialApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
