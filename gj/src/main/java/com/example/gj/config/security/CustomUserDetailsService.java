package com.example.gj.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
//    final
//    UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
//            User user = userRepository.findByEmail(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("Not Found User");
//            } else {
//                return new CustomUserDetails(user);
//            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
