package com.example.assignment.placesservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Place(String id, String label, String location, @JsonAlias("opening_hours") OpeningHours openingHours) {
}
