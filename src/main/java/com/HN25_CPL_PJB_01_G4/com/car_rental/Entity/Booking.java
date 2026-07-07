package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Booking", indexes = {
    @Index(name = "idx_booking_status", columnList = "status"),
    @Index(name = "idx_booking_user", columnList = "user_id"),
    @Index(name = "idx_booking_car", columnList = "car_id"),
    @Index(name = "idx_booking_dates", columnList = "start_date, end_date")
})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @NotNull(message = "Car is required")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id", nullable = false)
    private Renter renter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @NotNull(message = "Price per day is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal pricePerDay;

    @NotNull(message = "Deposit amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deposit must be greater than 0")
    private BigDecimal deposit;

    @NotNull(message = "Total cost is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total cost must be greater than 0")
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Booking status is required")
    private BookingStatus status;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    private Integer rating;

    @Size(max = 500, message = "Comments too long")
    private String comments;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public enum BookingStatus {
        CONFIRMED, PENDING_DEPOSIT, IN_PROGRESS, PENDING_PAYMENT, COMPLETED, CANCELLED, PENDING_REVIEW
    }

    @Column(name = "booking_number", unique = true)
    private String bookingNumber;

    public enum PaymentMethod {
        MY_WALLET, CASH, BANK_TRANSFER
    }

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
    }
}


