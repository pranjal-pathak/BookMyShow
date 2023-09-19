package dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {

    private Long bookingId;

    private int amount;

    private ResponseStatus responseStatus;


}
