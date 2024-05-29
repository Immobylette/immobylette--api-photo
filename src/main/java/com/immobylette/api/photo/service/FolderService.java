package com.immobylette.api.photo.service;

import com.immobylette.api.photo.dto.FolderDto;
import com.immobylette.api.photo.dto.FolderSummaryDto;
import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.entity.Folder;
import com.immobylette.api.photo.entity.Photo;
import com.immobylette.api.photo.exception.FolderNotFoundException;
import com.immobylette.api.photo.exception.GCPStorageException;
import com.immobylette.api.photo.exception.PhotoNotFoundException;
import com.immobylette.api.photo.exception.PhotosAndDescriptionsSizeException;
import com.immobylette.api.photo.mapper.FolderMapper;
import com.immobylette.api.photo.repository.FolderRepository;
import com.immobylette.api.photo.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class FolderService {

    private final PhotoRepository photoRepository;

    private final FolderRepository folderRepository;

    private final FolderMapper folderMapper;

    private final PhotoService photoService;


    public FolderDto getPhotos(UUID id) throws FolderNotFoundException, GCPStorageException {
        Folder folder = folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
        List<Photo> photos = photoRepository.findByFolderId(folder.getId());
        List<PhotoDto> photoDtos = photos.stream().map(photo -> {
            try {
                return photoService.getPhotoInFolder(folder.getId(), photo.getId());
            } catch (PhotoNotFoundException | GCPStorageException e) {
                return null;
            }
        }).toList();

        return folderMapper.fromFolder(folder, photoDtos);
    }

    public FolderSummaryDto getFolder(UUID id) throws FolderNotFoundException {
        Folder folder = folderRepository.findById(id).orElseThrow(() -> new FolderNotFoundException(id));
        Integer nbPhotos = photoRepository.countByFolderId(folder.getId());

        return folderMapper.fromFolder(folder, nbPhotos);
    }

    public UUID createFolder(List<MultipartFile> photos, List<String> descriptions) throws GCPStorageException, IOException, PhotosAndDescriptionsSizeException {
        Folder folder = new Folder();
        folderRepository.save(folder);

        if(photos.size() != descriptions.size()) {
            throw new PhotosAndDescriptionsSizeException();
        }

        Iterator<MultipartFile> photoIterator = photos.iterator();
        Iterator<String> descriptionIterator = descriptions.iterator();

        while (photoIterator.hasNext() && descriptionIterator.hasNext()) {
            MultipartFile file = photoIterator.next();
            String description = descriptionIterator.next();

            photoService.createPhoto(folder, description, file);
        }

        return folder.getId();
    }
}