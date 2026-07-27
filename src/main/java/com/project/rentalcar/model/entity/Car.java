package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.rentalcar.common.enums.CarStatus;
import com.project.rentalcar.common.enums.FuelType;
import com.project.rentalcar.common.enums.TransmissionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_car")
public class Car {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    private User owner;

    @Column(name = "LICENSE_PLATE", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PRODUCTION_YEAR")
    private Integer productionYear;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SEAT")
    private Integer seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSMISSION")
    private TransmissionType transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "FUEL")
    private FuelType fuel;

    @Column(name = "FUEL_CONSUMPTION")
    private Double fuelConsumption;

    @Column(name = "MILEAGE")
    private Double mileage;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "WARD")
    private String ward;

    @Column(name = "LATITUDE", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "PRICE_PER_DAY", precision = 15, scale = 2)
    private BigDecimal pricePerDay;

    @Column(name = "DEPOSIT", precision = 15, scale = 2)
    private BigDecimal deposit;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private CarStatus status;

    @Column(name = "RATING_AVG")
    private Double ratingAvg = 0.0;

    @Column(name = "TOTAL_TRIP")
    private Integer totalTrip = 0;

    @Column(name = "INSTANT_BOOKING")
    private Boolean instantBooking = false;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarDocument> documents = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarFeature> features = new ArrayList<>();

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarTerm term;

    @Column(name = "FAVORITE_COUNT")
    private Integer favoriteCount = 0;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarSchedule> schedules = new ArrayList<>();

}