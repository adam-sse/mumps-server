package de.uni_hildesheim.mumps.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni_hildesheim.mumps.data.Course;
import de.uni_hildesheim.mumps.data.CourseRepository;
import de.uni_hildesheim.mumps.dto.CourseDto;
import de.uni_hildesheim.mumps.dto.NewCourseDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CourseController {

    private static final Logger LOG = Logger.getLogger(CourseController.class.getName());
    
    @Autowired
    private CourseRepository courseRepository;
    
    @GetMapping("/course")
    public List<CourseDto> getAllCourses() {
        LOG.info("/course called");
        return courseRepository.findAll().stream()
                .map(CourseDto::new)
                .toList();
    }
    
    @GetMapping("/course/{id}")
    public CourseDto getCourse(@PathVariable long id) {
        LOG.info("/course/" + id + " called");
        return courseRepository.findById(id)
                .map(CourseDto::new)
                .orElse(null);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto createNewCourse(@Valid NewCourseDto dto) {
        Course course = new Course(dto.name());
        course.setRewardPerEvent(dto.rewardPerEvent());
        course = courseRepository.save(course);
        return new CourseDto(course);
    }
    
}
