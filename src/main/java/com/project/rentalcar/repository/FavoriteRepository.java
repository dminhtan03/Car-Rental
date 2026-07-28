package com.project.rentalcar.repository;

import com.project.rentalcar.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, String> {
    long countByCustomer_Id(String customerId);

    Optional<Favorite> findByCustomer_IdAndCar_Id(String customerId, String carId);
}