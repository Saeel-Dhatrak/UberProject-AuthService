package com.example.UberAuthService.controllers;

import com.example.UberAuthService.dtos.PassengerDto;
import com.example.UberAuthService.dtos.PassengerSignupRequestDto;
import com.example.UberAuthService.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response = authService.signup(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
