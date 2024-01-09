package com.example.gj.model;

import lombok.Data;

@Data
public class User {

    private String id;
    private String email;
    private String password;
    private String role;
    private int status;
}
