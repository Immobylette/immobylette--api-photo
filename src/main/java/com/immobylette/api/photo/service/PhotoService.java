package com.immobylette.api.photo.service;

import com.google.cloud.storage.StorageException;
import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.mapper.PhotoMapper;
import com.immobylette.api.photo.repository.PhotoRepository;
import com.immobylette.api.photo.resource.GCSResource;
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

    private final GCSResource gcsResource;

    private final PhotoMapper photoMapper;

    public PhotoDto getPhoto(UUID id) throws PhotoNotFoundException{
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(id));
        URL url = gcsResource.getSignedUrl(id.toString());

        return photoMapper.fromPhoto(photo, url);
    }

    public PhotoDto getPhotoInFolder(UUID folder, UUID photoId) throws PhotoNotFoundException {
        Photo photo = photoRepository.findById(photoId).orElseThrow(() -> new PhotoNotFoundException(photoId));
        URL url = gcsResource.getSignedUrl(String.format("%s/%s", folder, photoId));

        return photoMapper.fromPhoto(photo, url);
    }
}
