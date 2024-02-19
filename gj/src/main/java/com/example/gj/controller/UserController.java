package com.example.gj.controller;

import com.example.gj.model.Response;
import com.example.gj.model.User;
import com.example.gj.service.UserService;
import com.example.gj.service.VerifyService;
import com.example.gj.util.Role;
import com.example.gj.viewmodel.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    VerifyService verifyService;


    @GetMapping("/")
    @Secured(Role.ADMIN)
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
    @GetMapping("/current")
    @Secured({Role.USER, Role.ADMIN})
    public ResponseEntity<Response<UserResponse>> getCurrentUser() {
        try {
            UserResponse user = userService.getCurrentUser();
            return Response.success(user);
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

    @PostMapping("/create-verify-email")
    public ResponseEntity<Response<String>> createVerifyEmail(@RequestBody CreateVerifyRequest request) {
        try {
            boolean isSuccess = verifyService.createEmailVerify(request.getEmail());
            if (!isSuccess) {
                throw new Exception("Verify Fail!");
            }
            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/verify-email")
    public ResponseEntity<Response<VerifyResponse>> verify(@RequestBody VerifyEmailRequest request) {
        try {
            VerifyResponse response = verifyService.verifyEmail(request.getEmail(), request.getCode());
            return Response.success(response);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/create-forget-password")
    public ResponseEntity<Response<String>> createVerifyPassword(@RequestBody CreateForgetPasswordRequest request) {
        try {
            verifyService.createForgetPassword(request.getEmail());

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/check-code-forget-password")
    public ResponseEntity<Response<String>> checkCodePassword(@RequestBody CheckCodeForgetPasswordRequest request) {
        try {
            verifyService.checkCodeForgetPassword(request.getEmail(), request.getCode());

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }
    @PostMapping("/verify-forget-password")
    public ResponseEntity<Response<String>> verifyForgetPassword(@RequestBody VerifyForgetPasswordRequest request) {
        try {
            verifyService.verifyPassword(request);

            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/change-password")
    @Secured({Role.USER, Role.ADMIN})
    public ResponseEntity<Response<String>> changePassword(@RequestBody ChangePasswordRequest request) {
        try {


            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

    @PostMapping("/update-profile")
    @Secured(Role.USER)
    public ResponseEntity<Response<String>> updateProfile(@RequestBody ChangePasswordRequest request) {
        try {


            return Response.success(null);
        } catch (Exception e) {
            return Response.error(e);
        }
    }

}
