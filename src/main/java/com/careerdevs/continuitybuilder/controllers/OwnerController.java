package com.careerdevs.continuitybuilder.controllers;

import com.careerdevs.continuitybuilder.models.Owner;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerRepository repository;

    @GetMapping
    public @ResponseBody
    List<Owner> getPatient() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner newOwner){
        return new ResponseEntity<>(repository.save(newOwner), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Owner getSingleOwner(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Owner updateOwner(@PathVariable Long id, @RequestBody Owner updates){
        Owner owner = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) owner.setName(updates.getName());

        return repository.save(owner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePatient(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
