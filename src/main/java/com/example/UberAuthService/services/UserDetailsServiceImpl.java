package com.example.UberAuthService.services;

import com.example.UberAuthService.helpers.AuthPassengerDetails;
import com.example.UberAuthService.models.Passenger;
import com.example.UberAuthService.repositories.PassengerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PassengerRepository passengerRepository;

    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(email); // email is the unique identifier
        if(passenger.isPresent()){
            // we have to convert this passenger in the AuthPassengerDetails form
            return new AuthPassengerDetails(passenger.get());
        }else{
            throw new UsernameNotFoundException("Cannot find the Passenger by the given email");
        }
    }
}
