package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.WalletTransactionStatus;
import com.project.rentalcar.common.enums.WalletTransactionType;
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
@Table(name = "tbl_wallet_transaction")
public class WalletTransaction {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;

    @Column(name = "AMOUNT", precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private WalletTransactionType type;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private WalletTransactionStatus status;

    /**
     * Có thể là BookingId, PaymentId,
     * RefundId,...
     */
    @Column(name = "REFERENCE_ID")
    private String referenceId;

    @Column(name = "REFERENCE_TYPE")
    private String referenceType;

    @Column(name = "BALANCE_BEFORE", precision = 15, scale = 2)
    private BigDecimal balanceBefore;

    @Column(name = "BALANCE_AFTER", precision = 15, scale = 2)
    private BigDecimal balanceAfter;

    @Column(name = "TRANSACTION_NO")
    private String transactionNo;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}