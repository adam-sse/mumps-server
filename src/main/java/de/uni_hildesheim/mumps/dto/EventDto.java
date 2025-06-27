package de.uni_hildesheim.mumps.dto;

import java.time.ZonedDateTime;

import de.uni_hildesheim.mumps.data.Event;

public record EventDto(long id, ZonedDateTime startTime) {

    public EventDto(Event event) {
        this(event.getId(), event.getStartTime());
    }
    
}
