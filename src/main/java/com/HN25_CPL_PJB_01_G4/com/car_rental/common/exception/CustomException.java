package com.HN25_CPL_PJB_01_G4.com.car_rental.common.exception;

import com.HN25_CPL_PJB_01_G4.com.car_rental.common.payload.ResponseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;

    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

}
