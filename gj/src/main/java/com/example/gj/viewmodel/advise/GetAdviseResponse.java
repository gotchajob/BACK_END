package com.example.gj.viewmodel.advise;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAdviseResponse {
    private String id;
    private String fullName;
    private String phone;
    private String email;
}
