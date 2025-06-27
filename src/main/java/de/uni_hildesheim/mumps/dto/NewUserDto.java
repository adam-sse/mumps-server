package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.Course;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.util.List;

public record NewUserDto(@NotBlank String userID, String eMail) {

}
