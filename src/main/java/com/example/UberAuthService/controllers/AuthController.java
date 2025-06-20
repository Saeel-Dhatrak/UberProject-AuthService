package com.example.UberAuthService.controllers;

import com.example.UberAuthService.dtos.AuthRequestDto;
import com.example.UberAuthService.dtos.PassengerDto;
import com.example.UberAuthService.dtos.PassengerSignupRequestDto;
import com.example.UberAuthService.services.AuthService;
import com.example.UberAuthService.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response = authService.signup(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signin(@RequestBody AuthRequestDto authRequestDto){
        System.out.println("The AuthRequestDto values are : " + authRequestDto.getEmail() + "   " + authRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            Map<String, Object> payload = new HashMap<>();
            payload.put("email", authRequestDto.getEmail());
            String jwtToken = this.jwtService.createToken(payload, authentication.getPrincipal().toString());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }
    }
}
