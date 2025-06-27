package de.uni_hildesheim.mumps.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    private int rewardPerEvent;
    
    private List<Event> events;
    
    // for JPA
    protected Course() {
    }
    
    public Course(String name) {
        this.name = name;
        this.rewardPerEvent = 1;
        this.events = new LinkedList<>();
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getRewardPerEvent() {
        return rewardPerEvent;
    }
    
    public void setRewardPerEvent(int rewardPerEvent) {
        this.rewardPerEvent = rewardPerEvent;
    }
    
    public void addEvent(Event event) {
        events.add(event);
    }
    
    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }
    
    // for JPA?
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
}
