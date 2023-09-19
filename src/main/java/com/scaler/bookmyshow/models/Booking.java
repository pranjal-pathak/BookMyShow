package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity

public class Booking extends BaseModel{ //ticket


    @ManyToMany
    private List<ShowSeat> showSeats;
    @ManyToOne
    private User user;

    private int amount;
    @OneToMany
    List<Payment> payments;

    @Enumerated(EnumType.ORDINAL)       //no need of @enumerated collection
    private BookingStatus bookingStatus;

}




/*
    1               m
    Booking   -> ShowSeat   => m:m  in case a booking is cancelled and rebooked, that way 1 showseat can be in multiple bookings
        m           1

        1             1
    Booking         user   => m:1
        m              1


        1                   m
        Booking         Payment   => 1:m  in case of partial on unsuccessful payment
            1                 1
 */
