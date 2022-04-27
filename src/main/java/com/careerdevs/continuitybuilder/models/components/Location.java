package com.careerdevs.continuitybuilder.models.components;

import com.careerdevs.continuitybuilder.models.Owner;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    public Location() {
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
