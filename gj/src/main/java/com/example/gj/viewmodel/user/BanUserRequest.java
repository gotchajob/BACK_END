package com.example.gj.viewmodel.user;

import lombok.Data;

import java.util.List;

@Data
public class BanUserRequest {
    private List<String> userIdList;
}
