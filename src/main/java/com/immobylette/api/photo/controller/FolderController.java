package com.immobylette.api.photo.controller;

import com.immobylette.api.photo.dto.FolderDto;
import com.immobylette.api.photo.dto.FolderSummaryDto;
import com.immobylette.api.photo.exception.FolderNotFoundException;
import com.immobylette.api.photo.service.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class FolderController {
    private final FolderService folderService;

    @GetMapping("/folders/{id}/photos")
    public FolderDto getPhoto(@PathVariable UUID id) throws FolderNotFoundException {
        return folderService.getPhotos(id);
    }

    @GetMapping("/folders/{id}")
    public FolderSummaryDto getFolder(@PathVariable UUID id) throws FolderNotFoundException{
        return folderService.getFolder(id);
    }


    @PostMapping("/folders")
    public UUID addStep(@RequestPart("photos") List<MultipartFile> photos,
                        @RequestPart("descriptions") List<String> descriptions
    ) throws IOException {
        return folderService.createFolder(photos, descriptions);
    }
}
