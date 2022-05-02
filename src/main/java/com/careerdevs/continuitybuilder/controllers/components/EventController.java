package com.careerdevs.continuitybuilder.controllers.components;

import com.careerdevs.continuitybuilder.models.Owner;
import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.models.components.Event;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import com.careerdevs.continuitybuilder.repositories.components.EventRepository;
import com.careerdevs.continuitybuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository repository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody
    List<Event> getEvent() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent){
        User user = userService.getCurrentUser();
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Owner> currentOwner = ownerRepository.findByUser_id(user.getId());
        if(currentOwner.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //gets the created actor for the logged in Owner
        newEvent.setOwner(currentOwner.get());
        //Adds the new Actor to the current owner
        currentOwner.get().getEvents().add(newEvent);
        //Saves Actor to current owners repository
        ownerRepository.save(currentOwner.get());

        return new ResponseEntity<>(repository.save(newEvent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Event getSingleEvent(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Event updateEvent(@PathVariable Long id, @RequestBody Event updates){
        Event event = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) event.setName(updates.getName());
        if (updates.getOwner() != null) event.setOwner(updates.getOwner());
        if (updates.getActors() != null) event.setActors(updates.getActors());
        if (updates.getLocations() != null) event.setLocations(updates.getLocations());
        return repository.save(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEvent(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("Event has been Removed", HttpStatus.OK);
    }
}
