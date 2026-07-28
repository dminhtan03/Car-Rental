package com.project.rentalcar.repository;

import com.project.rentalcar.common.enums.CarStatus;
import com.project.rentalcar.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {
    long countByOwner_Id(String ownerId);

    List<Car> findByOwner_IdAndStatusNot(String ownerId, CarStatus status);

    List<Car> findByOwner_Id(String ownerId);
}