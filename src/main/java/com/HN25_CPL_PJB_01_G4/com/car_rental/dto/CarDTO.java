package com.HN25_CPL_PJB_01_G4.com.car_rental.dto;
import com.HN25_CPL_PJB_01_G4.com.car_rental.Entity.Car;
import lombok.Data;


import java.math.BigDecimal;


@Data
public class CarDTO {
    private Long owner;
    private String brand;
    private String model;
    private String color;
    private Integer prodYear;
    private Integer seats;
    private Car.Transmission transmission;
    private String fuel;
    private String licensePlateNumber;
    private String carDescription;
    private Integer mileage;
    private Double fuelConsumption;
    private BigDecimal pricePerDay;
    private BigDecimal deposit;
    private String status ="AVAILABLE";
    private Long addressId;

    // Terms of Use
    private Boolean noSmoking;
    private Boolean noPet;
    private Boolean noFoodInCar;
    private Boolean otherTerm;
    private String otherTermDescription;

    // Features
    private Boolean hasBluetooth = false;
    private Boolean hasGPS = false;
    private Boolean hasCamera = false;
    private Boolean hasSunRoof = false;
    private Boolean hasChildLock = false;
    private Boolean hasChildSeat = false;
    private Boolean hasDVD = false;
    private Boolean hasUSB = false;

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getProdYear() {
        return prodYear;
    }

    public void setProdYear(Integer prodYear) {
        this.prodYear = prodYear;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Car.Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Car.Transmission transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Boolean getNoSmoking() {
        return noSmoking;
    }

    public void setNoSmoking(Boolean noSmoking) {
        this.noSmoking = noSmoking;
    }

    public Boolean getNoPet() {
        return noPet;
    }

    public void setNoPet(Boolean noPet) {
        this.noPet = noPet;
    }

    public Boolean getNoFoodInCar() {
        return noFoodInCar;
    }

    public void setNoFoodInCar(Boolean noFoodInCar) {
        this.noFoodInCar = noFoodInCar;
    }

    public Boolean getOtherTerm() {
        return otherTerm;
    }

    public void setOtherTerm(Boolean otherTerm) {
        this.otherTerm = otherTerm;
    }

    public String getOtherTermDescription() {
        return otherTermDescription;
    }

    public void setOtherTermDescription(String otherTermDescription) {
        this.otherTermDescription = otherTermDescription;
    }

    public Boolean getHasBluetooth() {
        return hasBluetooth;
    }

    public void setHasBluetooth(Boolean hasBluetooth) {
        this.hasBluetooth = hasBluetooth;
    }

    public Boolean getHasGPS() {
        return hasGPS;
    }

    public void setHasGPS(Boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    public Boolean getHasCamera() {
        return hasCamera;
    }

    public void setHasCamera(Boolean hasCamera) {
        this.hasCamera = hasCamera;
    }

    public Boolean getHasSunRoof() {
        return hasSunRoof;
    }

    public void setHasSunRoof(Boolean hasSunRoof) {
        this.hasSunRoof = hasSunRoof;
    }

    public Boolean getHasChildLock() {
        return hasChildLock;
    }

    public void setHasChildLock(Boolean hasChildLock) {
        this.hasChildLock = hasChildLock;
    }

    public Boolean getHasChildSeat() {
        return hasChildSeat;
    }

    public void setHasChildSeat(Boolean hasChildSeat) {
        this.hasChildSeat = hasChildSeat;
    }

    public Boolean getHasDVD() {
        return hasDVD;
    }

    public void setHasDVD(Boolean hasDVD) {
        this.hasDVD = hasDVD;
    }

    public Boolean getHasUSB() {
        return hasUSB;
    }

    public void setHasUSB(Boolean hasUSB) {
        this.hasUSB = hasUSB;
    }

    // Optionally, you can include lists for document/image details if you wish to store extra info
// private List<CarDocumentDTO> documents;
// private List<CarImageDTO> images;
}
