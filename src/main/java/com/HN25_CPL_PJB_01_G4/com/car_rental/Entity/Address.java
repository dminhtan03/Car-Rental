package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Address", indexes = {
    @Index(name = "idx_address_ward", columnList = "ward_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Version
    private Long version;

    @NotBlank(message = "Address number is required")
//    @Size(max = 20, message = "Address number too long")
//    @Pattern(regexp = "^[0-9A-Za-z\\s-]+$", message = "Invalid address number format")
    @Column(name = "address_number", nullable = false)
    private String addressNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ward_id", referencedColumnName = "ward_code", nullable = false)
    @NotNull(message = "Ward is required")
    private Ward ward;

    @Column(name = "ward_code", nullable = false)
    private Integer wardCode;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return addressNumber + ", " + ward.getWard() + ", " + ward.getDistrict() + ", " + ward.getCityProvince();
    }
}
