package com.example.gj.viewmodel.dash_board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetOrderDashBoardResponse {
    List<Long> timeOrder;
    long totalOrder;
    long revenue;
}
