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
@Table(name = "tbl_bank_account")
public class BankAccount {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    @Column(name = "BRANCH")
    private String branch;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault = false;

    @Column(name = "IS_VERIFIED")
    private Boolean verified = false;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}