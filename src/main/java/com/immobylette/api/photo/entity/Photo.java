package com.immobylette.api.photo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "photos")
@Getter
public class Photo {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "fk_folder")
    @ManyToOne
    private Folder folder;
}
