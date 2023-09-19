package com.scaler.bookmyshow.Services;

import com.scaler.bookmyshow.Repositories.ShowSeatTypeRepository;
import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.ShowSeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculateBookingPrice(List<ShowSeat> showSeats, Show show){
        int amount = 0;

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show.getId());

        for(ShowSeat showSeat:showSeats){
             for(ShowSeatType showSeatType:showSeatTypes)
             {
                 if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                     amount += showSeatType.getPrice();
                 }
             }
            //Inner for loop can be removed if we use a hashmap of showSeatType and Price

        }

        return amount;




    }
}
