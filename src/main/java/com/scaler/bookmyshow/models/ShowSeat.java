package com.scaler.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;

    private ShowSeatStatus showSeatStatus;
}


/*

1                         1
showSeat        ->      Show ==> m:1
m                           1


1                       1
showSeat      ->        seat =>m:1
m                       1

 */