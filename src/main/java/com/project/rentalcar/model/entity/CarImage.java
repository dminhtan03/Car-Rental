package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_car_image")
public class CarImage {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "IS_THUMBNAIL")
    private Boolean thumbnail = false;

    @Column(name = "DISPLAY_ORDER")
    private Integer displayOrder;

}