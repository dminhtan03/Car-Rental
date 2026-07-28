package com.project.rentalcar.repository;

import com.project.rentalcar.common.enums.NotificationStatus;
import com.project.rentalcar.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    long countByUser_IdAndStatus(String userId, NotificationStatus status);
}