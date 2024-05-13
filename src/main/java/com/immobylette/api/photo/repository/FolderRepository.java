package com.immobylette.api.photo.repository;

import com.immobylette.api.photo.entity.Folder;
import com.immobylette.api.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
}
