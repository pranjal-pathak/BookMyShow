package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Region extends BaseModel{ //city

    private String name;

    //private List<Theatre> theaters;
    // depends on data access pattern
    //here, instead we are adding region class to theatre class, single entity is better than list



}
