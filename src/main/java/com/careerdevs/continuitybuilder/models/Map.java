package com.careerdevs.continuitybuilder.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Map {

    @Id
    @GeneratedValue
    private Long id;
}
