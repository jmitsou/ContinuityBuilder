package com.careerdevs.continuitybuilder.controllers;

import com.careerdevs.continuitybuilder.models.Actor;
import com.careerdevs.continuitybuilder.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private ActorRepository repository;

    @GetMapping
    public @ResponseBody
    List<Actor> getActor() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor newPatient){
        return new ResponseEntity<>(repository.save(newPatient), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getSingleActor(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Actor updateActor(@PathVariable Long id, @RequestBody Actor updates){
        Actor patient = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) patient.setName(updates.getName());
        if (updates.getEmail() != null) patient.setEmail(updates.getEmail());
        if (updates.getLanguages() != null) patient.setLanguages(updates.getLanguages());

        return repository.save(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeActor(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
