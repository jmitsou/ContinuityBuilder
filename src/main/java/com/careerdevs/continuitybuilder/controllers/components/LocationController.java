package com.careerdevs.continuitybuilder.controllers.components;

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

        return repository.save(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeLocation(@PathVariable Long id){
        repository.deleteById(id);
        return new ResponseEntity<>("User has been Removed", HttpStatus.OK);
    }
}
