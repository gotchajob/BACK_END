package com.example.gj.viewmodel.dash_board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetUserDashBoardResponse {
    private List<Long> accessTime;
    private long newUser;
    private long newAdvise;
}
