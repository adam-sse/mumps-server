package de.uni_hildesheim.mumps.data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Lottery {
    private List<UserLotteryEntry> userLotteryEntries;
    private String winnerID;
    private LocalDateTime dateTimeOfDraw;
    private boolean isDrawn;
    final int lotteryPrice = 9;

    public Lottery() {
        userLotteryEntries = new LinkedList<>();
    }

    public List<UserLotteryEntry> getUserLotteryEntries() {
        return userLotteryEntries;
    }

    public void setUserLotteryEntries(List<UserLotteryEntry> userLotteryEntries) {
        this.userLotteryEntries = userLotteryEntries;
    }

    public int getLotteryPrice() {
        return lotteryPrice;
    }

    public String getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(String winnerID) {
        this.winnerID = winnerID;
    }

    public LocalDateTime getDateTimeOfDraw() {
        return dateTimeOfDraw;
    }

    public void setDateTimeOfDraw(LocalDateTime dateTimeOfDraw) {
        this.dateTimeOfDraw = dateTimeOfDraw;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean isDrawn) {
        this.isDrawn = isDrawn;
    }
}
