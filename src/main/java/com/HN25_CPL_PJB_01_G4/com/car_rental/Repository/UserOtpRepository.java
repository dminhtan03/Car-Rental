package com.HN25_CPL_PJB_01_G4.com.car_rental.Repository;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserOtpRepository extends JpaRepository<UserOtp, String> {

    Optional<UserOtp> findByOtpCode(String validOtp);

    @Query("""
            SELECT u
            FROM UserOtp u
            WHERE u.user.userInfo.email = :userEmail
             AND u.otpCode = :otpKey
        """)
    Optional<UserOtp> findValidOtp(String userEmail, String otpKey);
}
