package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.Course;
import de.uni_hildesheim.mumps.data.User;

import java.util.Set;

public record UserDto(String userID, String eMail, boolean isLecturer, int points, Set<Course> enlistedCourses) {

    public UserDto(User user) {
        this(user.getUserID(), user.getEMail(), user.isLecturer(), user.getPoints(), user.getEnlistedCourses());
    }

}
