package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Renter", indexes = {
        @Index(name = "idx_renter_national_id", columnList = "national_id"),
        @Index(name = "idx_renter_phone", columnList = "phone_number"),
        @Index(name = "idx_renter_address", columnList = "address_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "renter_id")
    private Long renterId;

    @Version
    private Long version;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    @Pattern(regexp = "^[\\p{L}\\s.'-]+$", message = "Full name contains invalid characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "National ID is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "National ID must be 12 digits")
    @Column(name = "national_id", nullable = false)
    private String nationalId;

    @NotNull(message = "Driving license is required")
    @Size(max = 5242880, message = "Driving license image must not exceed 5MB")
    @Column(name = "driving_license", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] drivingLicense;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+84|0)[0-9]{9}$", message = "Invalid phone number format")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @NotNull(message = "Address is required")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required")
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false)
    private String email;
}
