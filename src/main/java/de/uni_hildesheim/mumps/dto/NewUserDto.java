package de.uni_hildesheim.mumps.dto;

import jakarta.validation.constraints.NotBlank;

public record NewUserDto(@NotBlank String userID, String eMail) {

}
