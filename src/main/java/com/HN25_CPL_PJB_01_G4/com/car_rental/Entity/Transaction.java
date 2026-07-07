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
@Table(name = "Transaction", indexes = {
    @Index(name = "idx_transaction_wallet", columnList = "wallet_id"),
    @Index(name = "idx_transaction_booking", columnList = "booking_id"),
    @Index(name = "idx_transaction_type_status", columnList = "transaction_type, status"),
    @Index(name = "idx_transaction_created", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    @NotNull(message = "Wallet is required")
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Transaction type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    @Size(max = 500, message = "Description too long")
    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum TransactionType {
        // Wallet top-up transactions
        TOP_UP,

        // Deposit transactions
        PAY_DEPOSIT,           // Customer pays deposit via wallet
        RECEIVE_DEPOSIT,       // Owner receives deposit

        // Deposit refund transactions
        REFUND_DEPOSIT,        // Owner returns deposit
        RECEIVE_REFUND,        // Customer receives deposit refund

        // Final payment transactions
        PAY_REMAINING,         // Customer pays remaining amount via wallet
        RECEIVE_PAYMENT,       // Owner receives remaining payment

        // Excess refund transactions (when deposit > total)
        REFUND_EXCESS,         // Owner refunds excess amount
        RECEIVE_EXCESS,        // Customer receives excess refund

        // Offline payment transactions (cash/bank transfer)
        OFFLINE_DEPOSIT_PAID,      // Record that customer paid deposit offline
        OFFLINE_DEPOSIT_RECEIVED,  // Record that owner received deposit offline

        // Offline final payment transactions
        OFFLINE_FINAL_PAYMENT,     // Record that customer paid final amount offline
        OFFLINE_PAYMENT_RECEIVED,  // Record that owner received final payment offline

        // Withdrawal transactions
        WITHDRAWAL,                // User withdraws money from wallet

        // Payment offset
        OFFSET_FINAL_PAYMENT,    // Owner offsets final payment with deposit

        // Remainder payment
        PAY_REMAINDER           // Customer pays remainder of total cost
    }

    public enum Status {
        PENDING, COMPLETED, FAILED, REFUNDED
    }
}