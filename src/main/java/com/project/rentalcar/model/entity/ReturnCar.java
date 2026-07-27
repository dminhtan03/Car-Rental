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
@Table(name = "tbl_return")
public class ReturnCar {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Column(name = "RETURN_TIME")
    private LocalDateTime returnTime;

    @Column(name = "RETURN_NOTE", columnDefinition = "TEXT")
    private String returnNote;

    @Column(name = "DAMAGE_FEE", precision = 15, scale = 2)
    private BigDecimal damageFee;

    @Column(name = "FUEL_FEE", precision = 15, scale = 2)
    private BigDecimal fuelFee;

    @Column(name = "LATE_FEE", precision = 15, scale = 2)
    private BigDecimal lateFee;

    @Column(name = "RETURN_IMAGES", columnDefinition = "TEXT")
    private String returnImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONFIRMED_BY")
    private User confirmedBy;

}