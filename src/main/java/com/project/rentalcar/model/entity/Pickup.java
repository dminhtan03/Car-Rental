package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_pickup")
public class Pickup {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Column(name = "PICKUP_TIME")
    private LocalDateTime pickupTime;

    @Column(name = "PICKUP_NOTE", columnDefinition = "TEXT")
    private String pickupNote;

    @Column(name = "PICKUP_IMAGES", columnDefinition = "TEXT")
    private String pickupImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONFIRMED_BY")
    private User confirmedBy;

    @Column(name = "ODOMETER_START")
    private Double odometerStart;

    @Column(name = "FUEL_LEVEL_START")
    private Integer fuelLevelStart;

    @Column(name = "PICKUP_LATITUDE")
    private BigDecimal pickupLatitude;

    @Column(name = "PICKUP_LONGITUDE")
    private BigDecimal pickupLongitude;

    @Column(name = "CUSTOMER_SIGNATURE")
    private String customerSignature;

    @Column(name = "OWNER_SIGNATURE")
    private String ownerSignature;
}
