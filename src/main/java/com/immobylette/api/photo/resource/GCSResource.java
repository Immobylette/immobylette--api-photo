package com.immobylette.api.photo.resource;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.immobylette.api.photo.config.GCSconfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class GCSResource {
    private final GCSconfig gcsConfig;
    private Storage storage;

    public GCSResource(GCSconfig gcsConfig) throws IOException {
        this.gcsConfig = gcsConfig;
        this.setup();
    }

    private void setup() throws IOException {
         Resource resource = new ClassPathResource(this.gcsConfig.getCredentialsLocation());
         resource.getFilename();

        Credentials credentials = GoogleCredentials
                .fromStream(resource.getInputStream());

        this.storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(this.gcsConfig.getProjectId()).build().getService();
    }

    public URL getSignedUrl(String objectName) throws StorageException {
        int signedUrlDuration = 1;
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(this.gcsConfig.getBucketName(), objectName)).build();
        return storage.signUrl(blobInfo, signedUrlDuration, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
    }
}