package com.project.rentalcar.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//Lưu log của noti khi dùng rabitmq
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification_log")
public class NotificationLog {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTIFICATION_ID")
    private Notification notification;

    @Column(name = "CHANNEL")
    private String channel;

    /**
     * SUCCESS
     * FAILED
     */
    @Column(name = "STATUS")
    private String status;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

}