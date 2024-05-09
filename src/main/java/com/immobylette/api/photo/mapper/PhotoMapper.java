package com.immobylette.api.photo.mapper;

import com.immobylette.api.photo.dto.PhotoDto;
import com.immobylette.api.photo.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.net.URL;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(target = "url", source = "signedUrl", qualifiedByName = "url")
    PhotoDto fromPhoto(Photo photo, URL signedUrl);

    @Named("url")
    static String url(URL signedUrl){
        return signedUrl.toString();
    }
}
