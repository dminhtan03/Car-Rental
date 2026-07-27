package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.WithdrawalStatus;
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
@Table(name = "tbl_withdrawal")
public class Withdrawal {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ACCOUNT_ID")
    private BankAccount bankAccount;

    @Column(name = "AMOUNT", precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private WithdrawalStatus status;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "REQUESTED_AT")
    private LocalDateTime requestedAt;

    @Column(name = "APPROVED_AT")
    private LocalDateTime approvedAt;

    @Column(name = "PROCESSED_AT")
    private LocalDateTime processedAt;

}