package de.uni_hildesheim.mumps.data;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class User {

    @Id
    private String userID;

    private String eMail;

    private boolean isLecturer;

    private int points;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Course> enlistedCourses; //course id's

    protected User() {

    }

    public User(String userID, String eMail, boolean isLecturer, int points, Set<Course> enlistedCourses) {
        this.userID = userID;
        this.eMail = eMail;
        this.isLecturer = isLecturer;
        this.points = points;
        this.enlistedCourses = enlistedCourses;
    }

    public User(String userID) {
        this.userID = userID;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean isLecturer() {
        return isLecturer;
    }

    public void setLecturer(boolean lecturer) {
        isLecturer = lecturer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Set<Course> getEnlistedCourses() {
        return enlistedCourses;
    }

    public void setEnlistedCourses(Set<Course> enlistedCourses) {
        this.enlistedCourses = enlistedCourses;
    }
}
