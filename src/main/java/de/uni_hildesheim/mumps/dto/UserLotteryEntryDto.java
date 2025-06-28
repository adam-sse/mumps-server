package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.UserLotteryEntry;

public record UserLotteryEntryDto(String userID, int lotteryTickets) {
    public UserLotteryEntryDto(UserLotteryEntry userLotteryEntry) {
        this(userLotteryEntry.getEnteredUserID(), userLotteryEntry.getLotteryTickets());
    }
}
