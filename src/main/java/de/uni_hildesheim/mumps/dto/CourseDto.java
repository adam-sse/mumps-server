package de.uni_hildesheim.mumps.dto;

import java.util.List;

import de.uni_hildesheim.mumps.data.Course;

public record CourseDto(long id, String name, String owner, int rewardPerEvent, List<EventDto> events) {
    
    public CourseDto(Course course) {
        this(course.getId(), course.getName(), course.getOwner(), course.getRewardPerEvent(), course.getEvents().stream()
                .map(EventDto::new)
                .toList());
    }

}
