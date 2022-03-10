package com.careerdevs.continuitybuilder.controllers;

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
    List<Patient> getPatient() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient newPatient){
        return new ResponseEntity<>(repository.save(newPatient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Patient getSinglePatient(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Patient updatePatient(@PathVariable Long id, @RequestBody Patient updates){
        Patient patient = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) patient.setName(updates.getName());
        if (updates.getEmail() != null) patient.setEmail(updates.getEmail());
        if (updates.getLanguages() != null) patient.setLanguages(updates.getLanguages());

        return repository.save(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePatient(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
