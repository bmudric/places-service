package com.example.assignment.placesservice.repository;

import com.example.assignment.placesservice.model.Place;
import com.example.assignment.placesservice.model.Places;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class PlacesRepository {

    static final Logger log = LoggerFactory.getLogger(PlacesRepository.class);

    private ConcurrentMap<String, Place> placesDB;

    @Autowired
    ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws IOException {
        var placesStream = new ClassPathResource("places_example.json").getInputStream();
        var places = objectMapper.readValue(placesStream, Places.class);
        placesDB = places.places().stream()
                .collect(Collectors.toConcurrentMap(Place::id, Function.identity()));
        log.info("Loaded dummy DB: " + placesDB);
    }

    public Places getAllPlaces() {
        return new Places(placesDB.values().stream().toList());
    }

    public Place getPlaceById(String id) {
        placesDB.computeIfAbsent(id, key -> {
            throw new PlaceNotFoundException("No Place found for id: " + id);
        });
        return placesDB.get(id);
    }
}
