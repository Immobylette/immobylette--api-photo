package com.immobylette.api.photo.controller;

import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.exception.GCPStorageException;
import com.immobylette.api.photo.exception.PhotoNotFoundException;
import com.immobylette.api.photo.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping("/photos/{id}")
    public PhotoDto getPhoto(@PathVariable UUID id) throws PhotoNotFoundException, GCPStorageException {
        return photoService.getPhoto(id);
    }

    @GetMapping("/photos/{folderId}/{photoId}")
    public PhotoDto getPhoto(@PathVariable UUID folderId, @PathVariable UUID photoId) throws PhotoNotFoundException, GCPStorageException {
        return photoService.getPhoto(folderId, photoId);
    }
}
