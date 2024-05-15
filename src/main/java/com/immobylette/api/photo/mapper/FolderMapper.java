package com.immobylette.api.photo.mapper;

import com.immobylette.api.photo.dto.FolderDto;
import com.immobylette.api.photo.dto.FolderSummaryDto;
import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.entity.Folder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FolderMapper {

    @Mappings({
            @Mapping(target = "id", source = "folder.id"),
            @Mapping(target = "nbPhotos", source = "nbPhotos")
    })
    FolderSummaryDto fromFolder(Folder folder, Integer nbPhotos);


    @Mappings({
            @Mapping(target = "id", source = "folder.id"),
            @Mapping(target = "photos", source = "photos"),
    })
    FolderDto fromFolder(Folder folder, List<PhotoDto> photos);
}
