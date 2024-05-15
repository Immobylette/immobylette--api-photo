package com.immobylette.api.photo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
public class FolderDto {
    @NotNull
    @JsonProperty("id")
    private UUID id;

    @NotNull
    @JsonProperty("photos")
    private List<PhotoDto> photos;
}
