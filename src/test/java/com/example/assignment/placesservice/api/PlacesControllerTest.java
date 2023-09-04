package com.example.assignment.placesservice.api;

import com.example.assignment.placesservice.model.Place;
import com.example.assignment.placesservice.model.Places;
import com.example.assignment.placesservice.repository.PlaceNotFoundException;
import com.example.assignment.placesservice.repository.PlacesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlacesController.class)
class PlacesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlacesRepository placesRepository;

    @Test
    public void getAllPlaces_should_return_OK() throws Exception {
        given(placesRepository.getAllPlaces()).willReturn(new Places(List.of(new Place("111", "a place", null, null))));
        mockMvc.perform(get("/places"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.places", hasSize(1)))
                .andExpect(jsonPath("$.places[0].id", equalTo("111")))
                .andExpect(jsonPath("$.places[0].label", equalTo("a place")))
                .andExpect(jsonPath("$.places[0].location").doesNotExist());
        verify(placesRepository, times(1)).getAllPlaces();
        verify(placesRepository, times(0)).getPlaceById(anyString());
    }

    @Test
    public void getPlaceById_should404_when_not_found() throws Exception {
        given(placesRepository.getPlaceById(anyString())).willThrow(new PlaceNotFoundException("no place found"));
        mockMvc.perform(get("/places/dummy"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", equalTo("no place found")));
        verify(placesRepository, times(0)).getAllPlaces();
        verify(placesRepository, times(1)).getPlaceById("dummy");
    }

}