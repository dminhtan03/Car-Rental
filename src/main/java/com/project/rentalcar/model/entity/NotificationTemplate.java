package com.project.rentalcar.model.entity;

import com.project.rentalcar.common.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification_template")
public class NotificationTemplate {

    @Id
    private String id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private NotificationType type;

}
