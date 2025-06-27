package de.uni_hildesheim.mumps.data;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    private String userID;

    private String eMail;

    private int points;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Course> enlistedCourses; //course id's

    protected User() {
        enlistedCourses = new LinkedList<Course>();
    }

    public User(String userID, String eMail, int points, List<Course> enlistedCourses) {
        this.userID = userID;
        this.eMail = eMail;
        this.points = points;
        this.enlistedCourses = enlistedCourses;
    }

    public User(String userID) {
        this.userID = userID;
        enlistedCourses = new LinkedList<Course>();
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Course> getEnlistedCourses() {
        return enlistedCourses;
    }

    public void setEnlistedCourses(List<Course> enlistedCourses) {
        this.enlistedCourses = enlistedCourses;
    }
}
