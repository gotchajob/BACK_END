package com.example.gj.viewmodel.subscribe_news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetSubscribeNewsResponse {
    private String id;
    private String email;
}
