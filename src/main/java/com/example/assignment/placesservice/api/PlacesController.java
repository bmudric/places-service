package com.example.assignment.placesservice.api;

import com.example.assignment.placesservice.model.Place;
import com.example.assignment.placesservice.model.Places;
import com.example.assignment.placesservice.repository.PlacesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class PlacesController {

    @Autowired
    private PlacesRepository placesRepository;

    @Operation(summary = "List all stored places, no pagination")
    @GetMapping
    public Places getAllPlaces() {
        return placesRepository.getAllPlaces();
    }

    @Operation(summary = "Retrieve one specific place by its id")
    @ApiResponse(responseCode = "404", description = "Space not found")
    @GetMapping("/{id}")
    public Place getPlaceById(@PathVariable @NotNull String id) {
        return placesRepository.getPlaceById(id);
    }
}
