package com.immobylette.api.photo.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "gcs")
@Getter
@Setter
public class GCSConfig {
    
    private String projectId;

    private String bucketName;

    private String credentialsLocation;


    @Bean
    public Storage GCSStorage() throws IOException {
        Credentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(credentialsLocation));

        return StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
    }
}
