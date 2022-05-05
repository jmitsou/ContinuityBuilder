package com.careerdevs.continuitybuilder.models.components;

import com.careerdevs.continuitybuilder.models.Owner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // notes field and roles field (player, NPC, Monster)
    @JsonIncludeProperties({"id", "name"})
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "actor_event",
            joinColumns = @JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonIgnoreProperties({"actors", "locations"})
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "actor_location",
            joinColumns = @JoinColumn(name="actor_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    @JsonIgnoreProperties({"actors", "events"})
    private Set<Location> locations = new HashSet<>();

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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
