package com.example.assignment.placesservice.repository;

public class PlaceNotFoundException extends RuntimeException{

    public PlaceNotFoundException(String message) {
        super(message);
    }
}
