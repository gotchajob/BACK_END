package com.example.gj.viewmodel.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreate {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
