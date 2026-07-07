package com.HN25_CPL_PJB_01_G4.com.car_rental.Repository;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
