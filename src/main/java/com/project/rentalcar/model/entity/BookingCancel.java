package com.project.rentalcar.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.rentalcar.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_booking_cancel")
public class BookingCancel {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Column(name = "REASON", columnDefinition = "TEXT")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CANCEL_BY")
    private User cancelBy;

    @Column(name = "REFUND_AMOUNT", precision = 15, scale = 2)
    private BigDecimal refundAmount;

    @Column(name = "CANCEL_TIME")
    private LocalDateTime cancelTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus refundStatus;

    @Column(name = "REFUND_TRANSACTION")
    private String refundTransaction;
}
