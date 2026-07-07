package com.HN25_CPL_PJB_01_G4.com.car_rental.Entity;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }

}
