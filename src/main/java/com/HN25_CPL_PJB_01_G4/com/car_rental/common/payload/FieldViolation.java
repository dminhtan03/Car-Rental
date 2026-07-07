package com.HN25_CPL_PJB_01_G4.com.car_rental.common.payload;

import com.dslplatform.json.CompiledJson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

@Value
public class FieldViolation {
    String field;
    String description;
    @JsonIgnore
    Throwable throwable;

    @CompiledJson
    public FieldViolation(String field, String description) {
        this(field, description, null);
    }

    public FieldViolation(String field, String description, Throwable throwable) {
        this.field = field;
        this.description = description;
        this.throwable = throwable;
    }
}
