package com.example.gj.viewmodel.user;

import lombok.Data;

@Data
public class CheckCodeForgetPasswordRequest {
    private String email;
    private String code;
}
