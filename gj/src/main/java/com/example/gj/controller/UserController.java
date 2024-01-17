package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.model.User;
import com.example.gj.service.UserService;
import com.example.gj.viewmodel.user.AuthenticationResponse;
import com.example.gj.viewmodel.user.UserCreate;
import com.example.gj.viewmodel.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public ResponseEntity<Response<List<User>>> get() {
        try {
            List<User> userList = userService.get();
            if (userList == null || userList.isEmpty()) {
                throw new Exception("Not found");
            }
            return Response.success(userList);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthenticationResponse>> login(@RequestBody UserLogin userLogin) {
        try {
            AuthenticationResponse data = userService.login(userLogin);
            return Response.success(data);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Response<String>> register(@RequestBody UserCreate userCreate) {
        try {
            User user = userService.createUser(userCreate);
            if (user == null) {
                throw new Exception("Created Fail!");
            }
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
}
