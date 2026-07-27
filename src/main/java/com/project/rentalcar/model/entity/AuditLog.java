package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.AuditAction;
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
@Table(name = "tbl_audit_log")
public class AuditLog {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION")
    private AuditAction action;

    /**
     * BOOKING
     * CAR
     * PAYMENT
     */
    @Column(name = "ENTITY")
    private String entity;

    @Column(name = "ENTITY_ID")
    private String entityId;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "IP")
    private String ip;

    @Column(name = "USER_AGENT", columnDefinition = "TEXT")
    private String userAgent;

    /**
     * SUCCESS / FAILED
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * JSON Request (tùy chọn)
     */
    @Column(name = "REQUEST_BODY", columnDefinition = "LONGTEXT")
    private String requestBody;

    /**
     * JSON Response (tùy chọn)
     */
    @Column(name = "RESPONSE_BODY", columnDefinition = "LONGTEXT")
    private String responseBody;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}