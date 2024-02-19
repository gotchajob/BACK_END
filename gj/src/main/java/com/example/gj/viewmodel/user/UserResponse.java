package com.example.gj.viewmodel.user;

import com.example.gj.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
    private String id;
    private String fullName;
    private String email;
    private int roleId;
    private int status;
    private Date Created;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.email = user.getEmail();
        this.roleId = user.getRoleId();
        this.status = user.getStatus();
        this.Created = user.getCreatedAt();
    }

}
