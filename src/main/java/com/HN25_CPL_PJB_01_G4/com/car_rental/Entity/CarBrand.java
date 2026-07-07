package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "car_brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "models")
public class CarBrand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column( nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<CarModel> models;
} 