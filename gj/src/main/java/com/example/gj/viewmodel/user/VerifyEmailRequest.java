package com.example.gj.viewmodel.user;

import lombok.Data;

@Data
public class VerifyEmailRequest {
    private String email;
    private String code;
}
