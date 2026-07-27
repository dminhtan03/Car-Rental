package com.project.rentalcar.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//bảng này giúp người dùng bật/tắt noti từng loại
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification_setting")
public class NotificationSetting {

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "BOOKING_ENABLED")
    private Boolean bookingEnabled = true;

    @Column(name = "PAYMENT_ENABLED")
    private Boolean paymentEnabled = true;

    @Column(name = "PROMOTION_ENABLED")
    private Boolean promotionEnabled = true;

    @Column(name = "SYSTEM_ENABLED")
    private Boolean systemEnabled = true;

    @Column(name = "EMAIL_ENABLED")
    private Boolean emailEnabled = true;

    @Column(name = "WEBSOCKET_ENABLED")
    private Boolean websocketEnabled = true;

}