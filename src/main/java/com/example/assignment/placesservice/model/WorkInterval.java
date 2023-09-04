package com.example.assignment.placesservice.model;

import java.time.LocalTime;

public record WorkInterval(LocalTime start, LocalTime end, WorkIntervalType type) {
}
