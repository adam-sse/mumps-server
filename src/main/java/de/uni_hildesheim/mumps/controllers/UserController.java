package de.uni_hildesheim.mumps.controllers;

import de.uni_hildesheim.mumps.data.User;
import de.uni_hildesheim.mumps.data.UserRepository;
import de.uni_hildesheim.mumps.dto.NewUserDto;
import de.uni_hildesheim.mumps.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger LOG = Logger.getLogger(CourseController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        LOG.info("/users called");

        return userRepository.findAll().stream()
                .map(UserDto::new)
                .toList();
    }

    @GetMapping("/users/{userID}")
    public UserDto getUser(@PathVariable String userID) {
        LOG.info("/user/" + userID + "called");

        return userRepository.findById(userID)
                .map(UserDto::new)
                .orElse(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createNewUser(@Valid NewUserDto dto) {
        User user = new User(dto.userID());
        user.setEMail(dto.eMail());
        user.setLecturer(dto.isLecturer());
        user.setPoints(dto.points());
        user.setEnlistedCourses(dto.enlistedCourses());
        return new UserDto(user);
    }



}
