package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.rentalcar.common.enums.BookingExtensionStatus;
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
@Table(name = "tbl_booking_extension")
public class BookingExtension {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Column(name = "OLD_RETURN_TIME")
    private LocalDateTime oldReturnTime;

    @Column(name = "NEW_RETURN_TIME")
    private LocalDateTime newReturnTime;

    @Column(name = "EXTRA_FEE", precision = 15, scale = 2)
    private BigDecimal extraFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private BookingExtensionStatus status;

    @Column(name = "REQUEST_NOTE")
    private String requestNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUEST_BY")
    private User requestBy;

    @Column(name = "REQUEST_TIME")
    private LocalDateTime requestTime;

    @Column(name = "APPROVED_TIME")
    private LocalDateTime approvedTime;
}