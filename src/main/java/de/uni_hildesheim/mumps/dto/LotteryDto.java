package de.uni_hildesheim.mumps.dto;

import de.uni_hildesheim.mumps.data.Lottery;

import java.time.LocalDateTime;

public record LotteryDto(String winnerID, LocalDateTime dateTimeOfDraw, boolean isDrawn) {
    public LotteryDto(Lottery lottery) {
        this(lottery.getWinnerID(), lottery.getDateTimeOfDraw(), lottery.isDrawn());
    }
}
