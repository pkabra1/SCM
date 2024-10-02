package com.scm.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.repositories.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public SecurityCustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // load our own username
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with this email id"));
    }

}
