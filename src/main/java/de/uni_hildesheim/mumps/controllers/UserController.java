package de.uni_hildesheim.mumps.controllers;

import de.uni_hildesheim.mumps.LotteryMethods;
import de.uni_hildesheim.mumps.data.*;
import de.uni_hildesheim.mumps.dto.LotteryDto;
import de.uni_hildesheim.mumps.dto.NewUserDto;
import de.uni_hildesheim.mumps.dto.UserDto;
import de.uni_hildesheim.mumps.dto.UserLotteryEntryDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EventRepository eventRepository;

    private Lottery lottery;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        LOG.info("/users called");

        return userRepository.findAll().stream()
                .map(UserDto::new)
                .toList();
    }

    @GetMapping("/users/{userID}")
    public UserDto getUser(@PathVariable String userID) {
        LOG.info("/user/" + userID + " called");

        return userRepository.findById(userID)
                .map(UserDto::new)
                .orElse(null);
    }

    @GetMapping("/users/leaderboard")
    public List<UserDto> getUserRanking() {
        LOG.info("/leaderboard called");
        List<UserDto> unsortedList = userRepository.findAll().stream()
                .map(UserDto::new)
                .toList();
        List<UserDto> leaderboard = new LinkedList<>(unsortedList);
        leaderboard.sort(Comparator.comparingInt(UserDto::points));

        return new LinkedList<>(leaderboard);
    }

    @GetMapping("/users/lotteryResult")
    public LotteryDto getLotteryResult() {
        LOG.info("lottery result called");
        if(lottery == null) {
            lottery = new Lottery();
        }
        LotteryDto dto = new LotteryDto(lottery.getWinnerID(), lottery.getDateTimeOfDraw(), lottery.isDrawn());
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(lottery.getDateTimeOfDraw()) && !lottery.isDrawn()) {
            lottery.setWinnerID(LotteryMethods.drawLottery(lottery.getUserLotteryEntries()));
            lottery.setDrawn(true);
            dto = new LotteryDto(lottery.getWinnerID(), lottery.getDateTimeOfDraw(), lottery.isDrawn());
        }

        if (lottery.isDrawn() && lottery.getDateTimeOfDraw().isAfter(lottery.getDateTimeOfDraw().plusDays(7))) {
            lottery.setDateTimeOfDraw(now.plusMonths(1));
            lottery.setUserLotteryEntries(new LinkedList<>());
            lottery.setDrawn(false);
            lottery.setWinnerID("noWinner");

            dto = new LotteryDto(lottery.getWinnerID(), lottery.getDateTimeOfDraw(), lottery.isDrawn());
        }

        return dto;
    }

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createNewUser(@RequestBody @Valid NewUserDto dto) {
        LOG.info("New User created");
        User user = new User(dto.userID());
        user.setEMail(dto.eMail());
        user.setPoints(0);
        user.setEnlistedCourses(new LinkedList<>());
        user = userRepository.saveAndFlush(user);

        return new UserDto(user);
    }

    @PutMapping("/users/{userID}/enrolled/{courseID}")
    public UserDto enrollUserToCourse(@PathVariable String userID, @PathVariable long courseID) {
        LOG.info("/users/" + userID + "/enrolled/" + courseID + " called");
        User user = userRepository.findById(userID).get();

        if (!user.getEnlistedCourses().contains(courseID)){
            user.getEnlistedCourses().add(courseID);
            user = userRepository.saveAndFlush(user);
            LOG.info("User enrolled to course");
        } else {
            LOG.info("User already enrolled to course");
        }

        return new UserDto(user);
    }

    @PutMapping("/users/{userID}/enterLottery")
    public UserLotteryEntryDto userEnteredLottery(@PathVariable String userID) {
        LOG.info("lottery entry called");
        User user = userRepository.findById(userID).get();
        UserLotteryEntry enteredUser = null;

        if (!lottery.isDrawn()) {
            for (int i = 0; i < lottery.getUserLotteryEntries().size(); i++) {
                enteredUser = lottery.getUserLotteryEntries().get(i);
                if (enteredUser.getEnteredUserID().equals(userID)) {
                    while (user.getPoints() >= lottery.getLotteryPrice()) {
                        user.setPoints(user.getPoints() - lottery.getLotteryPrice());
                        enteredUser.setLotteryTickets(enteredUser.getLotteryTickets() + 1);
                        LOG.info("User entered lottery");
                    }
                }
            }
        } else {
            enteredUser = new UserLotteryEntry("entries are closed", 0);
        }
        userRepository.saveAndFlush(user);

        if(enteredUser == null) {
            enteredUser = new UserLotteryEntry("user not found", 0);
        }

        return new UserLotteryEntryDto(enteredUser.getEnteredUserID(), enteredUser.getLotteryTickets());
    }

    @PutMapping("/users/{userID}/visited/{courseID}/{eventID}")
    public UserDto userVisitedEvent(@PathVariable String userID, @PathVariable long courseID, @PathVariable long eventID){
        LOG.info("/users/" + userID + "/visited/" + courseID + "/" + eventID + " called");
        User user = userRepository.findById(userID).get();
        Event event = eventRepository.findById(courseID).get();
        if (event.getVisitors() == null) {
            event.setVisitors(new LinkedList<>());
        }
        Course course = courseRepository.findById(eventID).get();

        if (!event.getVisitors().contains(user.getUserID())) {
            event.getVisitors().add(user.getUserID());
            user.setPoints(user.getPoints() + course.getRewardPerEvent());

            event = eventRepository.saveAndFlush(event);
            user = userRepository.saveAndFlush(user);

            LOG.info("User: " + user.getUserID() + " visited event: " + event.getId() + " of course: " + course.getName());
        } else {
            LOG.info("User: " + user.getUserID() + " tried to visit event: " + event.getId() + " of course: " + course.getName() + " multiple times.");
        }

        return new UserDto(user);
    }
}
