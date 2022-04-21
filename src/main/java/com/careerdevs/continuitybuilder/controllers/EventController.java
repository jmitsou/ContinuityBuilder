package com.careerdevs.continuitybuilder.controllers;

import com.careerdevs.continuitybuilder.models.Event;
import com.careerdevs.continuitybuilder.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventRepository repository;

    @GetMapping
    public @ResponseBody
    List<Event> getEvent() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent){
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

        return repository.save(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEvent(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("Event has been Removed", HttpStatus.OK);
    }
}
