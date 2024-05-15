package com.immobylette.api.photo.resource;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.immobylette.api.photo.config.GCSConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
}