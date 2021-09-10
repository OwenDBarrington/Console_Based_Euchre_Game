package ca.sheridancollege.project;

/**
 * A subclass of Score representing the score of a Euchre round (5 tricks)
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

public class RoundScore extends Score {

    //Max 5 tricks
    private final int MAX_SCORE = 5;

    public RoundScore() {
        setScoreLimit(MAX_SCORE);
    }

    public void addTrick(EuchrePlayer winningPlayer) {
        if (winningPlayer.getTeam() == 1) {
            addScoreTeam1(1);
        }
        else {
             addScoreTeam2(1);
        }
    }

    //debug only
    public String toString() {
        String out = String.format("Round Score:\nTeam 1 - %s\nTeam 2 - %s\n", getTeam1Score(), getTeam2Score());
        return out;

    }
}
