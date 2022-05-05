package com.careerdevs.continuitybuilder.controllers.owner_components;

import com.careerdevs.continuitybuilder.models.Owner;
import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.models.components.Actor;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import com.careerdevs.continuitybuilder.repositories.components.ActorRepository;
import com.careerdevs.continuitybuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private ActorRepository repository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody
    List<Actor> getActor() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor newActor){
        User user = userService.getCurrentUser();
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Owner> currentOwner = ownerRepository.findByUser_id(user.getId());
        if(currentOwner.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //gets the created actor for the logged in Owner
        newActor.setOwner(currentOwner.get());
        //Adds the new Actor to the current owner
        currentOwner.get().getActors().add(newActor);
        //Saves Actor to current owners repository
        ownerRepository.save(currentOwner.get());

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
        if (updates.getOwner() != null) actor.setOwner(updates.getOwner());
        if (updates.getEvents() != null) actor.setEvents(updates.getEvents());
        if (updates.getLocations() != null) actor.setLocations(updates.getLocations());
        return repository.save(actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeActor(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
