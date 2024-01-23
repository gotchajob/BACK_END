package com.example.gj.viewmodel.user;

import lombok.Data;

@Data
public class VerifyForgetPasswordRequest {
    private String email;
    private String code;
    private String password;
}
