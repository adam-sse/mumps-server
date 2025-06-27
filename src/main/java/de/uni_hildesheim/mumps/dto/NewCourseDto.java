package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.User;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;

public record NewCourseDto(@NotBlank String name, @NotBlank User owner, @Range(min = 1) int rewardPerEvent) {

}
