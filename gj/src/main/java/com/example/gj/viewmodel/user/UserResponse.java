package com.example.gj.viewmodel.user;

import com.example.gj.model.User;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int roleId;
    private int status;

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roleId = user.getRoleId();
        this.status = user.getStatus();
    }

}
