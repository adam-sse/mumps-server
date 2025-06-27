package de.uni_hildesheim.mumps.data;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;
    
    private ZonedDateTime startTime;
    
    // for JPA
    protected Event() {
    }

    public Event(ZonedDateTime startTime) {
        this.startTime = startTime;
    }
    
    public ZonedDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }
    
}
