package com.immobylette.api.photo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageException;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GCPStorage {
    private final String projectId;
    private final String bucketName;
    private final String credentialsLocation;
    private Storage storage;

    public GCPStorage(
            @Value("${spring.gcp.project-id}") String projectId,
            @Value("${spring.gcp.bucket-name}") String bucketName,
            @Value("${spring.gcp.credentials-location}") String credentialsLocation
    ) throws IOException {
        this.projectId = projectId;
        this.bucketName = bucketName;
        this.credentialsLocation = credentialsLocation;
        this.setup();
    }

    private void setup() throws IOException {
        Credentials credentials = GoogleCredentials
                .fromStream(new FileInputStream(this.credentialsLocation));

        this.storage = StorageOptions.newBuilder().setCredentials(credentials)
                .setProjectId(this.projectId).build().getService();
    }

    public URL getSignedUrl(String objectName) throws StorageException {
        int signedUrlDuration = 1;
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).build();
        return storage.signUrl(blobInfo, signedUrlDuration, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
    }
}
