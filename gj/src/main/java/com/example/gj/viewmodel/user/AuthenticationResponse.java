package com.example.gj.viewmodel.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationResponse {
    private String token;
    private UserResponse user;
}
