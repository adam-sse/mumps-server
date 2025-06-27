package de.uni_hildesheim.mumps.dto;

import java.util.List;

import de.uni_hildesheim.mumps.data.Course;
import de.uni_hildesheim.mumps.data.User;

public record CourseDto(long id, String name, User owner, int rewardPerEvent, List<EventDto> events) {
    
    public CourseDto(Course course) {
        this(course.getId(), course.getName(), course.getOwner(), course.getRewardPerEvent(), course.getEvents().stream()
                .map(EventDto::new)
                .toList());
    }

}
