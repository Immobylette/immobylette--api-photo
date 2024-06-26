package com.immobylette.api.photo.repository;

import com.immobylette.api.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotoRepository extends JpaRepository<Photo, UUID> {


    List<Photo> findByFolderId(UUID folderId);

    Integer countByFolderId(UUID folderId);
}
