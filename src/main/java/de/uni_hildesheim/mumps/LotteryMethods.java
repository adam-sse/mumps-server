package de.uni_hildesheim.mumps;

import de.uni_hildesheim.mumps.data.UserLotteryEntry;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LotteryMethods {
    public static String drawLottery(List<UserLotteryEntry> userEntries) {
        List<String> listToDraw = new LinkedList<>();

        //Adds UserIDs depending on ticket amount
        for (UserLotteryEntry ule : userEntries) {
            do {
                listToDraw.add(ule.getEnteredUserID());
                ule.setLotteryTickets(ule.getLotteryTickets() - 1);
            } while (ule.getLotteryTickets() > 0);
        }

        //Decide on Index to Pick
        Random random = new Random();
        int winnerIndex = (int) ((Math.random() * (listToDraw.size() - 1)));

        return listToDraw.get(winnerIndex);
    }
}
