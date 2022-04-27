package com.careerdevs.continuitybuilder.services;

import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.repositories.auth.UserRepository;
import com.careerdevs.continuitybuilder.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(){
    }

    public User getCurrentUser(){
        //Authenicates into DB
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Checks user input
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        //Checks user repo for user details provided
        Optional<User> currentUser = this.userRepository.findById(userDetails.getId());
        //Return null if no match is found or the user requested
        return currentUser.isEmpty() ? null : (User)currentUser.get();
    }
}
