package com.example.gj.viewmodel.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogin {
    private String email;
    private String password;
}
