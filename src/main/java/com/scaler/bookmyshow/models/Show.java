package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name = "shows") // SHOW IS A RESERVED KEYWORD IN MYSQL
public class Show extends BaseModel{


    @ManyToOne
    private Movie movie;
    private Date date;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Screen screen;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection          //because it is a list of features, if it was a single feature no need of @elementCollection
    private List<Feature> features;
}
//      1       1
//    Show -> Movie   => m:1
//      m       1


//      1       1
//    Show -> Screen   => m:1
//      m       1

//      m       1
//    Show -> Feature   => m:m
//      1       m

