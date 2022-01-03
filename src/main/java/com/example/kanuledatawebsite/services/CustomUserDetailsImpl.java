package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dataaccesslayer.UserRepository;
import com.example.kanuledatawebsite.entities.User;
import com.example.kanuledatawebsite.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}