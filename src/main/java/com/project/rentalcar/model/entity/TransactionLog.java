package com.project.rentalcar.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_transaction_log")
public class TransactionLog {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @Column(name = "REQUEST_DATA", columnDefinition = "LONGTEXT")
    private String requestData;

    @Column(name = "RESPONSE_DATA", columnDefinition = "LONGTEXT")
    private String responseData;

    @Column(name = "STATUS_CODE")
    private String statusCode;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}