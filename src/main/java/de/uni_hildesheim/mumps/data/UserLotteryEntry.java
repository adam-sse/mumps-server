package de.uni_hildesheim.mumps.data;

public class UserLotteryEntry {
    private String enteredUserID;
    private int lotteryTickets;

    public UserLotteryEntry(String userID, int lotteryTickets) {
        enteredUserID = userID;
        this.lotteryTickets = lotteryTickets;
    }

    public int getLotteryTickets() {
        return lotteryTickets;
    }

    public void setLotteryTickets(int lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public String getEnteredUserID() {
        return enteredUserID;
    }

    public void setEnteredUserID(String enteredUserID) {
        this.enteredUserID = enteredUserID;
    }
}
