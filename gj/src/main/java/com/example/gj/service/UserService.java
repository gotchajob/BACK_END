package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.User;
import com.example.gj.repository.UserRepository;
import com.example.gj.util.JwtUtil;
import com.example.gj.util.Util;
import com.example.gj.validator.EmailValidator;
import com.example.gj.validator.PasswordValidator;
import com.example.gj.viewmodel.user.AuthenticationResponse;
import com.example.gj.viewmodel.user.UserCreate;
import com.example.gj.viewmodel.user.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwt;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<User> get() {
        return userRepository.findAll();
    }

    public AuthenticationResponse login(UserLogin userLogin) throws Exception{
        if(userLogin == null || userLogin.getEmail() == null || userLogin.getPassword() == null){
            throw new Exception("LOGIN_FAIL");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin.getEmail());
        String token = jwt.generateToken(userDetails.getUsername());
        String roleId = extractRoleId(userDetails.getAuthorities());
        return new AuthenticationResponse(token, roleId);
    }
    public String extractRoleId(Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            if (authority instanceof SimpleGrantedAuthority) {
                return authority.getAuthority();
            }
        }
        return null;
    }

    public User createUser(UserCreate userCreate) throws Exception {
        String message = checkUserCreate(userCreate);
        if (message != null) {
            throw new Exception(message);
        }

        User _user = userRepository.findByEmail(userCreate.getEmail());
        if (_user != null && _user.getPassword() != null) {
            throw new Exception(Message.EMAIL_EXISTED);
        }

        if (_user != null && _user.getPassword() == null) {
            //TODO: update account create with account google
        }

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setEmail(userCreate.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userCreate.getPassword()));
        user.setFirstName(userCreate.getFirstName());
        user.setLastName(userCreate.getLastName());

        user.setRoleId(2);
        user.setStatus(1);
        user.setCreatedAt(new Date());

        return userRepository.save(user);
    }

    public String checkUserCreate(UserCreate userCreate) {
        if (userCreate == null || userCreate.getEmail() == null || userCreate.getPassword() == null) {
            return Message.NULL_INPUT;
        }

        if (!EmailValidator.isValidEmail(userCreate.getEmail())) {
            return Message.INVALID_EMAIL;
        }

        if (userCreate.getPassword().length() < 8) {
            return Message.PASSWORD_SHORT;
        }

        if (PasswordValidator.isValidatePassword(userCreate.getPassword())) {
            return Message.INVALID_PASSWORD;
        }


        return null;
    }
}