package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.RefundStatus;
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
@Table(name = "tbl_refund")
public class Refund {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @Column(name = "AMOUNT", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "REASON", columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private RefundStatus status;

    @Column(name = "PROCESSED_AT")
    private LocalDateTime processedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROCESSED_BY")
    private User processedBy;

    @Column(name = "REFUND_TRANSACTION_ID")
    private String refundTransactionId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}