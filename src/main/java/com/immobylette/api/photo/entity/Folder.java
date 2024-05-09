package com.immobylette.api.photo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "folders")
public class Folder {
    @Id
    @Column(name = "id")
    private UUID id;
}
