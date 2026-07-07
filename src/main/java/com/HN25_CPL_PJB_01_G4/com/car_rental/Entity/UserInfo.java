package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "tbl_user_info")
public class UserInfo {
    @Id
    @GeneratedValue
    private String id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "NATIONAL_ID")
    private String nationalId;

    @Column(name = "DRIVING_LICENSE")
    private String drivingLicense;

    @Column(name = "DOB")
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private Address address;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;
}
