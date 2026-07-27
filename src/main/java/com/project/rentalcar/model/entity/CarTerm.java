package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_car_term")
public class CarTerm {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "NO_SMOKING")
    private Boolean noSmoking = false;

    @Column(name = "NO_PET")
    private Boolean noPet = false;

    @Column(name = "NO_FOOD")
    private Boolean noFood = false;

    @Column(name = "OTHER", columnDefinition = "TEXT")
    private String other;

}