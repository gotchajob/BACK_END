package com.example.gj.service;

import com.example.gj.config.response.Message;
import com.example.gj.model.User;
import com.example.gj.repository.UserRepository;
import com.example.gj.util.JwtUtil;
import com.example.gj.validator.EmailValidator;
import com.example.gj.validator.PasswordValidator;
import com.example.gj.viewmodel.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final static int BAN_USER_STATUS = 0;
    private final static int USER_ROLE = 2;

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



    public GetUserResponse get(int page, int limit, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageRequest = PageRequest.of(page - 1, limit, direction, sortBy);

        List<User> userList = userRepository.findAllByRoleId(USER_ROLE, pageRequest);
        if (userList == null) {
            userList = new ArrayList<>();
        }
        int total = userRepository.countByRoleId(USER_ROLE);

        return new GetUserResponse(userList, total);
    }

    public AuthenticationResponse login(UserLogin userLogin) throws Exception{
        if(userLogin == null || userLogin.getEmail() == null || userLogin.getPassword() == null){
            throw new Exception("LOGIN_FAIL");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

        User user = userRepository.findByEmail(userLogin.getEmail());

        String token = jwt.generateToken(user.getEmail());
        return new AuthenticationResponse(token, new UserResponse(user));
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
        user.setStatus(2);
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

        if (!PasswordValidator.isValidPassword(userCreate.getPassword())) {
            return Message.INVALID_PASSWORD;
        }


        return null;
    }

    public UserResponse getCurrentUser() throws Exception {
      String email = getCurrentUsername();
      if (email == null) {
          throw new Exception(Message.NO_AUTH);
      }

      User user = userRepository.findByEmail(email);
      if (user == null || user.getStatus() == 0) {
          throw new Exception(Message.USER_NOT_FOUND);
      }

      return new UserResponse(user);
    };



    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return authentication.getName();
    }

    public boolean banUser(List<String> userIdList) throws Exception {
        if (userIdList == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        try {
            for (String id : userIdList) {
                Optional<User> optionalUser = userRepository.findById(id);
                User user = optionalUser.isEmpty() ? null : optionalUser.get();

                if (user != null && user.getStatus() != BAN_USER_STATUS && user.getRoleId() != 1) {
                    user.setStatus(BAN_USER_STATUS);
                    userRepository.save(user);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error when ban user");
        }

        return true;
    }

    public User getAUser(String id) throws Exception {
        if (id == null) {
            throw new Exception(Message.NULL_INPUT);
        }

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception(Message.USER_NOT_FOUND);
        }

        return user.get();
    }


}
