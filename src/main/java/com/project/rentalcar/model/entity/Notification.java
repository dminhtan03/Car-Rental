package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.NotificationPriority;
import com.project.rentalcar.common.enums.NotificationStatus;
import com.project.rentalcar.common.enums.NotificationType;
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
@Table(name = "tbl_notification")
public class Notification {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private NotificationStatus status = NotificationStatus.UNREAD;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY")
    private NotificationPriority priority = NotificationPriority.NORMAL;

    /**
     * Link để FE mở đúng màn hình.
     * Ví dụ:
     * /booking/{id}
     * /wallet
     * /payment/{id}
     */
    @Column(name = "ACTION_URL")
    private String actionUrl;

    /**
     * BookingId, PaymentId...
     */
    @Column(name = "REFERENCE_ID")
    private String referenceId;

    /**
     * BOOKING
     * PAYMENT
     */
    @Column(name = "REFERENCE_TYPE")
    private String referenceType;

    @Column(name = "READ_AT")
    private LocalDateTime readAt;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}