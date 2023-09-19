package com.scaler.bookmyshow.Controllers;

import com.scaler.bookmyshow.Services.BookingService;
import com.scaler.bookmyshow.models.Booking;
import com.scaler.bookmyshow.models.ResponseStatus;
import dto.BookMovieRequestDto;
import dto.BookMovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class BookingController { //Waiter = controller => waiter takes input from customer and passes it to chef, chef does the actual work, chef here is controller

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto){
        //client maybe running on
        // android, js etc, so it will only be able to provide ids instead of java objects
        //List<Long> showSeatIds, Long userId, Long showId => so these ids are required but will provide them in form of
        //dto

        BookMovieResponseDto response = new BookMovieResponseDto();

        try{
            Booking booking = bookingService.bookMovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatIds());

            response.setBookingId(booking.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setAmount(booking.getAmount());



        }catch(RuntimeException runtimeException){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
