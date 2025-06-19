package com.example.UberAuthService.services;

import com.example.UberAuthService.dtos.PassengerDto;
import com.example.UberAuthService.dtos.PassengerSignupRequestDto;
import com.example.UberAuthService.models.Passenger;
import com.example.UberAuthService.repositories.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signup(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger = Passenger.builder()
                        .name(passengerSignupRequestDto.getName())
                        .email(passengerSignupRequestDto.getEmail())
                        .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword()))
                        .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                        .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        // From this Object we will create PassengerDto
        return PassengerDto.from(newPassenger);
    }
}