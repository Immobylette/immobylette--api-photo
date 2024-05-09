package com.immobylette.api.photo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gcs")
@Getter
@Setter
public class GCSconfig {
    private String projectId;
    private String bucketName;
    private String credentialsLocation;
}
