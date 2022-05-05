package com.careerdevs.continuitybuilder.controllers;

import com.careerdevs.continuitybuilder.models.Owner;
import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import com.careerdevs.continuitybuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public @ResponseBody
    List<Owner> getOwner() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        User user = userService.getCurrentUser();
        if (user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        owner.setUser(user);
        return new ResponseEntity<>(repository.save(owner), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Owner getSingleOwner(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Owner updateOwner(@PathVariable Long id, @RequestBody Owner updates){
        Owner owner = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //Checks to see if a user was provided and then sets to current user
        if (updates.getName() != null) owner.setName(updates.getName());
        //Checks password of current user in not empty
        if (updates.getUser().getPassword() != null){
            //Hashes provided password
            String newPass = passwordEncoder.encode(updates.getUser().getPassword());
            //Sets new password
            owner.getUser().setPassword(newPass);
        };
        //Saves provided updates to Current Owner
        return repository.save(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePatient(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
