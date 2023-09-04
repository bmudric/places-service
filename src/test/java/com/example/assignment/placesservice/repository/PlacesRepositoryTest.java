package com.example.assignment.placesservice.repository;

import com.example.assignment.placesservice.model.Place;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PlacesRepositoryTest {

    @Autowired
    private PlacesRepository placesRepository;

    @Test
    public void getAllPlaces_should_return_all() {
        var places = placesRepository.getAllPlaces().places();
        assertEquals(2, places.size());
        var labels = places.stream().map(Place::label).toList();
        assertThat(labels, hasItems("Casa Ferlin", "Le Café du Marché"));
        var ids = places.stream().map(Place::id).toList();
        assertThat(ids, hasItems("123", "456"));
    }

    @Test
    public void getPlaceById_should_return_found_place() {
        var place = placesRepository.getPlaceById("123");
        assertEquals("123", place.id());
        assertEquals("Le Café du Marché", place.label());
    }

    @Test
    public void getPlaceById_should_throw_when_not_found() {
        var exception = assertThrows(PlaceNotFoundException.class, () -> placesRepository.getPlaceById("dummy"));
        assertEquals("No Place found for id: dummy", exception.getMessage());
    }
}