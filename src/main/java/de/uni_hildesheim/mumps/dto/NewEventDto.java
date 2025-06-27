package de.uni_hildesheim.mumps.dto;

import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;

public record NewEventDto(@NotNull ZonedDateTime startTime) {

}
