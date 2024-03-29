package com.example.gj.viewmodel.dash_board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetUserDashBoardResponse {
    private List<Long> timeAccess;
    private List<Long> newUser;
    private long totalAccess;
    private long totalUser;
    private long totalAccessBefore;
    private long totalUserBefore;
    private long newAdvise;
}
