package com.project.rentalcar.repository;

import com.project.rentalcar.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUser_Id(String userId);
}