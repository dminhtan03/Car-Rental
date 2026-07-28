package com.project.rentalcar.repository;

import com.project.rentalcar.common.enums.BookingStatus;
import com.project.rentalcar.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    long countByCustomer_Id(String customerId);

    long countByOwner_Id(String ownerId);

    List<Booking> findByCustomer_IdOrderByCreatedAtDesc(String customerId);

    List<Booking> findByOwner_IdOrderByCreatedAtDesc(String ownerId);

    long countByOwner_IdAndStatus(String ownerId, BookingStatus status);

    long countByCustomer_IdAndStatus(String customerId, BookingStatus status);
}