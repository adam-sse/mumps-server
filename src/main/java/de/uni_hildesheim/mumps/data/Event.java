package de.uni_hildesheim.mumps.data;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue
    private long id;
    
    private ZonedDateTime startTime;

    private List<String> visitors;
    // for JPA
    protected Event() {
        visitors = new LinkedList<>();
    }
    
    public long getId() {
        return id;
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

    public List<String> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<String> visitors) {
        this.visitors = visitors;
    }
}
