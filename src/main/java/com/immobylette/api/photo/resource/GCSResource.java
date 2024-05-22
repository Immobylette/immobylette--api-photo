package com.immobylette.api.photo.resource;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.immobylette.api.photo.config.GCSConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class GCSResource {

    private final GCSConfig gcsConfig;

    private Storage storage;

    public URL getSignedUrl(String objectName) {
        int signedUrlDuration = 1;
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(this.gcsConfig.getBucketName(), objectName)).build();
        return storage.signUrl(blobInfo, signedUrlDuration, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
    }

    public void uploadFile(Storage storageClient, String fileName, MultipartFile file) throws IOException {
        BlobInfo blobInfo =
                BlobInfo.newBuilder(BlobId.of(this.gcsConfig.getBucketName(), fileName)).build();
        storageClient.createFrom(blobInfo, file.getInputStream());
    }
}