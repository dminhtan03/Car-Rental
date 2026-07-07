package com.HN25_CPL_PJB_01_G4.com.car_rental.converter;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Car;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class FuelTypeConverter implements AttributeConverter<Car.FuelType, String> {

    @Override
    public String convertToDatabaseColumn(Car.FuelType attribute) {
        return attribute == null ? null : attribute.name(); // Ensures uppercase is stored
    }

    @Override
    public Car.FuelType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return Car.FuelType.valueOf(dbData.toUpperCase());
    }
}
