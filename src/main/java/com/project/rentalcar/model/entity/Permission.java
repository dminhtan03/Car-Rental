package com.project.rentalcar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_permission")
public class Permission {

    @Id
    private String id;

    @Column(name = "PERMISSION_NAME")
    private String name;
}
