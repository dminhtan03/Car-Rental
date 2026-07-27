package com.project.rentalcar.model.entity;


import com.project.rentalcar.common.enums.PaymentGateway;
import com.project.rentalcar.common.enums.PaymentMethod;
import com.project.rentalcar.common.enums.PaymentStatus;
import com.project.rentalcar.common.enums.PaymentType;
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
@Table(name = "tbl_payment")
public class Payment {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_METHOD")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_GATEWAY")
    private PaymentGateway paymentGateway;

    /**
     * Transaction ID trả về từ VNPay,
     * Stripe,...
     */
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "AMOUNT", precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private PaymentStatus status;

    @Column(name = "PAID_AT")
    private LocalDateTime paidAt;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE")
    private PaymentType paymentType;

    @OneToMany(mappedBy = "payment")
    private List<Refund> refunds = new ArrayList<>();

    @Column(name = "REQUEST_ID")
    private String requestId;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "FAILURE_REASON")
    private String failureReason;

    @Column(name = "CALLBACK_DATA", columnDefinition = "LONGTEXT")
    private String callbackData;

}