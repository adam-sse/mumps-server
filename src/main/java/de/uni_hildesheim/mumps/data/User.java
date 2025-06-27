package de.uni_hildesheim.mumps.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class User {

    @Id
    private String userID;

    private String eMail;

    private byte[] loginHash; //hash result to compare to

    private boolean isLecturer;

    private int points;

    private Set<Long> enlistedCourses; //course id's

    protected User() {

    }

    public User(String userID, String eMail, byte[] loginHash, boolean isLecturer, int points, Set<Long> enlistedCourses) {
        this.userID = userID;
        this.eMail = eMail;
        this.loginHash = loginHash;
        this.isLecturer = isLecturer;
        this.points = points;
        this.enlistedCourses = enlistedCourses;
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

    public byte[] getLoginHash() {
        return loginHash;
    }

    public void setLoginHash(byte[] loginHash) {
        this.loginHash = loginHash;
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

    public Set<Long> getEnlistedCourses() {
        return enlistedCourses;
    }

    public void setEnlistedCourses(Set<Long> enlistedCourses) {
        this.enlistedCourses = enlistedCourses;
    }
}
