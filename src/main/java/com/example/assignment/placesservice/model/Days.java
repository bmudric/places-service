package com.example.assignment.placesservice.model;

import java.util.List;

public record Days(
        List<WorkInterval> monday,
        List<WorkInterval> tuesday,
        List<WorkInterval> wednesday,
        List<WorkInterval> thursday,
        List<WorkInterval> friday,
        List<WorkInterval> saturday,
        List<WorkInterval> sunday
) {
}
