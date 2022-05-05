package com.careerdevs.continuitybuilder.controllers;

import com.careerdevs.continuitybuilder.models.Journal;
import com.careerdevs.continuitybuilder.repositories.OwnerRepository;
import com.careerdevs.continuitybuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalController {

    @Autowired
    private OwnerRepository repository;

    @Autowired
    private UserService userService;

    //@GetMapping
    //public @ResponseBody
    //List<Journal> getJournal() {return repository.findAll();}

    //@PostMapping
   // public ResponseEntity<Journal> createJournal
}
