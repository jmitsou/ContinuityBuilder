package com.careerdevs.continuitybuilder.models;

import com.careerdevs.continuitybuilder.models.auth.User;
import com.careerdevs.continuitybuilder.models.components.Actor;
import com.careerdevs.continuitybuilder.models.components.Event;
import com.careerdevs.continuitybuilder.models.components.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Owner {

    //Fields
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //Relationships
    //@JsonIgnore
    @OneToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @JsonIgnoreProperties("owner")
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Actor> actors;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Event> events;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Location> locations;

    //Constructors
    public Owner() {
    }

    public Owner(String name) {
        this.name = name;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}


