package de.uni_hildesheim.mumps.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 2022534935738218012L;

    @Id
    private String userID;

    private String eMail;

    private int points;

    private List<Long> enlistedCourses; //course id's

    protected User() {
        enlistedCourses = new LinkedList<>();
    }

    public User(String userID, String eMail, int points, List<Long> enlistedCourses) {
        this.userID = userID;
        this.eMail = eMail;
        this.points = points;
        this.enlistedCourses = enlistedCourses;
    }

    public User(String userID) {
        this.userID = userID;
        enlistedCourses = new LinkedList<>();
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

    public List<Long> getEnlistedCourses() {
        return enlistedCourses;
    }

    public void setEnlistedCourses(List<Long> enlistedCourses) {
        this.enlistedCourses = enlistedCourses;
    }
}
