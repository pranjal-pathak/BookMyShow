package com.scaler.bookmyshow.Services;

import com.scaler.bookmyshow.Exceptions.ShowNotFoundException;
import com.scaler.bookmyshow.Exceptions.ShowSeatNotAvailableException;
import com.scaler.bookmyshow.Exceptions.UserNotFoundException;
import com.scaler.bookmyshow.Repositories.BookingRepository;
import com.scaler.bookmyshow.Repositories.ShowRepository;
import com.scaler.bookmyshow.Repositories.ShowSeatRepository;
import com.scaler.bookmyshow.Repositories.UserRepository;
import com.scaler.bookmyshow.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService { //
    /*
    services do not return dto, they can directly return the object

    2 reasons for the same:
    1) services are internal to the system and are not going to directly interact with the customer, so no need of DTO
    2) we should not tightly couple the services as it can be used by multiple controllers
    */

    private UserRepository userRepository;

    private ShowRepository showRepository;

    private ShowSeatRepository showSeatRepository;

    private BookingRepository bookingRepository;

    private PriceCalculatorService priceCalculatorService;
    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie( Long userId, Long showId, List<Long> showSeatIds){

         /*
         Steps:(we are option for approach #1 from notes as it is easier to implement)

         -------------------Take a LOCK-------------------------------------------

         1) get the user from userId
         2) get the show from showId
         3) get the list of seats from showSeatIds
         4) check if all the showSeats are available
         5) if all the showSeats are not available, throw an exception
         6) if all are available, the change the status to be LOCKED.
         7) change the status is DB as well
         8) create a booking object and store it in DB
         9) return the booking object

         --------------------Release the LOCK----------------------------------------
          */

        //1) get the user from userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("Invalid userId");
        }

        User bookedBy = optionalUser.get();     // in case the userId is not invalid otherwise exception is already taken care of


        //2) get the show from showId
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Invalid showId");
        }

        Show show = optionalShow.get();
        // 3) get the list of seats from showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        //4) check if all the showSeats are available
        for(ShowSeat showSeat: showSeats)
        {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                //5) if all the showSeats are not available, throw an exception
                throw new ShowSeatNotAvailableException("ShowSeat with id: "+ showSeat.getId()+" isn't available.");
            }
        }
        List<ShowSeat> bookedShowSeats = new ArrayList<>();
        //6) if all are available, the change the status to be LOCKED.
        for(ShowSeat showSeat:showSeats)
        {
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);

            //7) change the status is DB as well
            bookedShowSeats.add(showSeatRepository.save(showSeat));

        }

        //8) create a booking object and store it in DB
        Booking booking = new Booking();
        booking.setUser(bookedBy);     //name of user above is bookedBy
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setPayments(new ArrayList<>());
        booking.setShowSeats(bookedShowSeats);
        booking.setLastModifiedAt(new Date());
        booking.setAmount(priceCalculatorService.calculateBookingPrice(bookedShowSeats,show));

        Booking savedBooking = bookingRepository.save(booking);

        // 9) return the booking object
        return savedBooking;

//---------------Lock will be released-----------------------


    }
}
