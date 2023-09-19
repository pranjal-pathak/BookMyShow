package dto;

import com.scaler.bookmyshow.models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpResponseDto {

    private Long UserId;

    private ResponseStatus responseStatus;
}
