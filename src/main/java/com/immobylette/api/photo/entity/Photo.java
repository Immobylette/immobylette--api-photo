package com.immobylette.api.photo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "photos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "fk_folder")
    @ManyToOne
    private Folder folder;
}
