package com.careerdevs.continuitybuilder.controllers.owner_components;

import com.careerdevs.continuitybuilder.models.Owner;
import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.models.components.Location;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import com.careerdevs.continuitybuilder.repositories.components.LocationRepository;
import com.careerdevs.continuitybuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public @ResponseBody
    List<Location> getLocation() {return repository.findAll();}

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location newLocation){
        User user = userService.getCurrentUser();
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Optional<Owner> currentOwner = ownerRepository.findByUser_id(user.getId());
        if(currentOwner.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //gets the created actor for the logged in Owner
        newLocation.setOwner(currentOwner.get());
        //Adds the new Actor to the current owner
        currentOwner.get().getLocations().add(newLocation);
        //Saves Actor to current owners repository
        ownerRepository.save(currentOwner.get());

        return new ResponseEntity<>(repository.save(newLocation), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody Location getSingleLocation(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody Location updateLocation(@PathVariable Long id, @RequestBody Location updates){
        Location location = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) location.setName(updates.getName());
        if (updates.getDescription() != null) location.setDescription(updates.getDescription());
        if (updates.getOwner() != null) location.setOwner(updates.getOwner());
        if (updates.getEvents() != null) location.setEvents(updates.getEvents());
        if (updates.getActors() != null) location.setActors(updates.getActors());
        return repository.save(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeLocation(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
