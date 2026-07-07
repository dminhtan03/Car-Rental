package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "Car", indexes = {
        @Index(name = "idx_car_status", columnList = "status"),
        @Index(name = "idx_car_owner", columnList = "owner_id"),
        @Index(name = "idx_car_license", columnList = "license_plate_number")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private CarBrand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private CarColor color;
    private Integer prodYear;


    private Integer seats;

    @Enumerated(EnumType.STRING)

    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    private FuelType fuel;

    @Column(name = "license_plate_number", unique = true)
    private String licensePlateNumber;


    private String carDescription;


    private Integer mileage;


    @Column(name = "fuel_consumption")
    private Double fuelConsumption;


    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;


    private BigDecimal deposit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private CarStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    // Additional features as per screen description
    @Column(name = "has_bluetooth")
    private Boolean hasBluetooth;

    @Column(name = "has_gps")
    private Boolean hasGPS;

    @Column(name = "has_camera")
    private Boolean hasCamera;

    @Column(name = "has_sun_roof")
    private Boolean hasSunRoof;

    @Column(name = "has_child_lock")
    private Boolean hasChildLock;

    @Column(name = "has_child_seat")
    private Boolean hasChildSeat;

    @Column(name = "has_dvd")
    private Boolean hasDVD;

    @Column(name = "has_usb")
    private Boolean hasUSB;

    // Terms of use as columns
    @Column(name = "no_smoking")
    private Boolean noSmoking;

    @Column(name = "no_pet")
    private Boolean noPet;

    @Column(name = "no_food")
    private Boolean noFood;

    @Column(name = "other_term")
    private Boolean otherTerm;


    @Column(name = "other_term_description")
    private String otherTermDescription;

    // Documents with specific types
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarDocument> documents;

    // Images with specific requirements
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> images;

    public enum Transmission {
        MANUAL, AUTOMATIC
    }

    public enum FuelType {
        GASOLINE, DIESEL
    }

    public enum CarStatus {
        AVAILABLE, BOOKING, STOPPING
    }
}