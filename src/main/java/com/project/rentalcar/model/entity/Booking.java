package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.rentalcar.common.enums.BookingStatus;
import com.project.rentalcar.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_booking")
public class Booking {

    @Id
    private String id;

    @Column(name = "BOOKING_NO", unique = true)
    private String bookingNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @Column(name = "PICKUP_TIME")
    private LocalDateTime pickupTime;

    @Column(name = "RETURN_TIME")
    private LocalDateTime returnTime;

    @Column(name = "ACTUAL_PICKUP_TIME")
    private LocalDateTime actualPickupTime;

    @Column(name = "ACTUAL_RETURN_TIME")
    private LocalDateTime actualReturnTime;

    @Column(name = "PICKUP_ADDRESS")
    private String pickupAddress;

    @Column(name = "RETURN_ADDRESS")
    private String returnAddress;

    @Column(name = "PICKUP_LATITUDE")
    private BigDecimal pickupLatitude;

    @Column(name = "PICKUP_LONGITUDE")
    private BigDecimal pickupLongitude;

    @Column(name = "RETURN_LATITUDE")
    private BigDecimal returnLatitude;

    @Column(name = "RETURN_LONGITUDE")
    private BigDecimal returnLongitude;

    @Column(name = "PRICE_PER_DAY", precision = 15, scale = 2)
    private BigDecimal pricePerDay;

    @Column(name = "NUMBER_OF_DAYS")
    private Integer numberOfDays;

    @Column(name = "DEPOSIT", precision = 15, scale = 2)
    private BigDecimal deposit;

    @Column(name = "RENTAL_FEE", precision = 15, scale = 2)
    private BigDecimal rentalFee;

    @Column(name = "EXTRA_FEE", precision = 15, scale = 2)
    private BigDecimal extraFee;

    @Column(name = "DISCOUNT", precision = 15, scale = 2)
    private BigDecimal discount;

    @Column(name = "TOTAL_AMOUNT", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "COUPON_CODE")
    private String couponCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS")
    private PaymentStatus paymentStatus;

    @Column(name = "BOOKING_NOTE", columnDefinition = "TEXT")
    private String bookingNote;

    @Column(name = "CANCEL_REASON", columnDefinition = "TEXT")
    private String cancelReason;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingStatusHistory> statusHistories = new ArrayList<>();

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookingExtension> extensions = new ArrayList<>();

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private BookingCancel bookingCancel;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Pickup pickup;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private ReturnCar returnCar;

}