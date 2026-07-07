package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ward", indexes = {
    @Index(name = "idx_ward_code", columnList = "ward_code"),
    @Index(name = "idx_ward_district", columnList = "district_code"),
    @Index(name = "idx_ward_city", columnList = "city_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ward {
    @Id
    @Column(name = "ward_code")
    private Integer wardCode;

    @Version
    private Long version;


    @Size(max = 100, message = "Ward name too long")
    @Column(name = "ward", nullable = false)
    private String ward;

    @Column(name = "district_code", nullable = false)
    private Integer districtCode;

    @NotBlank(message = "District name is required")
    @Size(max = 100, message = "District name too long")
    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city_code", nullable = false)
    private Integer cityCode;

    @NotBlank(message = "City/Province name is required")
    @Size(max = 100, message = "City/Province name too long")
    @Column(name = "city_province", nullable = false)
    private String cityProvince;

    @Override
    public String toString() {
        return ward + ", " + district + ", " + cityProvince;
    }
}
