package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.User;

import java.util.List;

public record UserDto(String userID, String eMail, int points, List<CourseDto> enlistedCourses) {

    public UserDto(User user) {
        this(user.getUserID(), user.getEMail(), user.getPoints(), user.getEnlistedCourses().stream()
                .map(CourseDto::new)
                .toList());
    }

}
