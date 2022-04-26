package com.careerdevs.continuitybuilder.services;

import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.repositories.UserRepository;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        Optional<User> currentUser = this.userRepository.findById(userDetails.getId());
        return currentUser.isEmpty() ? null : (User)currentUser.get();
    }
}
