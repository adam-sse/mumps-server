package de.uni_hildesheim.mumps.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;

public record NewCourseDto(@NotBlank String name, @NotBlank String owner, @Range(min = 1) int rewardPerEvent) {

}
