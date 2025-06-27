package de.uni_hildesheim.mumps.dto;

import java.time.ZonedDateTime;
import java.util.List;

import de.uni_hildesheim.mumps.data.Event;

public record EventDto(long id, ZonedDateTime startTime, List<UserDto> visitors) {

    public EventDto(Event event) {
        this(event.getId(), event.getStartTime(), event.getVisitors().stream()
                .map(UserDto::new)
                .toList());
    }
    
}
