package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.Course;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record NewUserDto(@NotBlank String userID, String eMail,
                         boolean isLecturer, int points, Set<Course> enlistedCourses) {

}
