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
    public ResponseEntity<Actor> createActor(@RequestBody Actor newActor){
        return new ResponseEntity<>(repository.save(newActor), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Actor getSingleActor(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Actor updateActor(@PathVariable Long id, @RequestBody Actor updates){
        Actor actor = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) actor.setName(updates.getName());


        return repository.save(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeActor(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
