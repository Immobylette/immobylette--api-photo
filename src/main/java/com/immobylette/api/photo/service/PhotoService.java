package com.immobylette.api.photo.service;

import com.google.cloud.storage.StorageException;
import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.mapper.PhotoMapper;
import com.immobylette.api.photo.repository.PhotoRepository;
import com.immobylette.api.photo.resource.GCS;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.immobylette.api.photo.entity.Photo;
import com.immobylette.api.photo.exception.PhotoNotFoundException;
import com.immobylette.api.photo.exception.GCPStorageException;

import java.net.URL;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final GCS gcs;
    private final PhotoMapper photoMapper;

    public PhotoDto getPhoto(UUID id) throws PhotoNotFoundException, GCPStorageException {
        URL url;
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(id));
        try{
            url = gcs.getSignedUrl(photo.getId().toString());
        } catch (StorageException e) {
            throw new GCPStorageException("Error getting signed URL");
        }

        return photoMapper.fromPhoto(photo, url);
    }
}
