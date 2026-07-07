package com.HN25_CPL_PJB_01_G4.com.car_rental.Repository;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    String findUserEmail = "SELECT u.*\n" +
            "from tbl_user u\n" +
            "join tbl_user_info ui on u.user_info_id = ui.id\n" +
            "where ui.email = :username\n";

    @Query(nativeQuery = true, value = findUserEmail)
    Optional<User> findByEmail(String username);

    int countByEnabled(boolean isEnabled);

    // start+ notification: find all admin users to send service request alerts
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ADMIN' AND u.enabled = true")
    List<User> findAllAdmins();
    // end+ notification
}


