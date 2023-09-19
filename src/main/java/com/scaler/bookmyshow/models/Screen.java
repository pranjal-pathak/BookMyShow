package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class Screen extends BaseModel{

    private String screenNumber;
    @OneToMany
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}

/*

1                  m
Screen          Seat    =>1:m
1                   1


1                   m
Screen          Features > m:m  but feature is an enum
m                   1

 */
