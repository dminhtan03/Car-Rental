package com.HN25_CPL_PJB_01_G4.com.car_rental.Repository;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
