package com.HN25_CPL_PJB_01_G4.com.car_rental.dto;

import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Booking;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class BookingWithImagesDTO {
    @Setter
    private Booking booking;
    private String carBrand;
    private String carModel;
    private List<byte[]> carImages; // Lưu danh sách ảnh dạng byte[]

    public BookingWithImagesDTO(Booking booking, String carBrand, String carModel, List<byte[]> carImages) {
        this.booking = booking;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carImages = carImages;
    }


}
