package com.example.UberAuthService.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {
    String email;
    String password;
    String phoneNumber;
    String name;
}
