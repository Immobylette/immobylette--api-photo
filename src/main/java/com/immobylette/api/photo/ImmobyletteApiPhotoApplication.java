package com.immobylette.api.photo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableConfigurationProperties
public class ImmobyletteApiPhotoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmobyletteApiPhotoApplication.class, args);
    }

}
