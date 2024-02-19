package com.example.gj.viewmodel.user;

import com.example.gj.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GetUserResponse {
    private List<UserResponse> userList;
    private int total;

    public GetUserResponse(List<User> list, int total) {
        if (userList == null) {
            userList = new ArrayList<>();
        }

        for (User user : list) {
            userList.add(new UserResponse(user));
        }

        this.total = total;
    }
}
