package de.uni_hildesheim.mumps.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni_hildesheim.mumps.data.Course;
import de.uni_hildesheim.mumps.data.CourseRepository;
import de.uni_hildesheim.mumps.data.Event;
import de.uni_hildesheim.mumps.data.EventRepository;
import de.uni_hildesheim.mumps.dto.CourseDto;
import de.uni_hildesheim.mumps.dto.NewCourseDto;
import de.uni_hildesheim.mumps.dto.NewEventDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CourseController {

    private static final Logger LOG = Logger.getLogger(CourseController.class.getName());
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @GetMapping("/course")
    public List<CourseDto> getAllCourses() {
        LOG.info("Retrieving all courses");
        return courseRepository.findAll().stream()
                .map(CourseDto::new)
                .toList();
    }
    
    @GetMapping("/course/{courseId}")
    public CourseDto getCourse(@PathVariable long courseId) {
        LOG.info(() -> "Retrieving course " + courseId);
        return courseRepository.findById(courseId)
                .map(CourseDto::new)
                .orElse(null);
    }
    
    @PostMapping(path = "/course", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto createNewCourse(@RequestBody @Valid NewCourseDto dto) {
        LOG.info(() -> "Creating new course " + dto);
        Course course = new Course(dto.name(), dto.owner());
        course.setRewardPerEvent(dto.rewardPerEvent());
        course = courseRepository.saveAndFlush(course);
        return new CourseDto(course);
    }
    
    @PostMapping(path = "/course/{courseId}/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto createNewEvent(@PathVariable long courseId, @RequestBody @Valid NewEventDto dto) {
        LOG.info(() -> "Creating new event " + dto + " in course " + courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow();
        course.addEvent(eventRepository.save(new Event(dto.startTime())));
        course = courseRepository.saveAndFlush(course);
        return new CourseDto(course);
    }
    
}
