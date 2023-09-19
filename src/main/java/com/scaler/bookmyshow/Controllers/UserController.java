package com.scaler.bookmyshow.Controllers;

import com.scaler.bookmyshow.Repositories.UserRepository;
import com.scaler.bookmyshow.models.User;
import dto.SignUpRequestDto;
import dto.SignUpResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Getter
@Setter
@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto ){

        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());



    }

}
