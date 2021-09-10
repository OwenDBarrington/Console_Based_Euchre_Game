package ca.sheridancollege.project;

/**
 * A superclass for Euchre scores
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

public class Score {

    private int team1Score;
    private int team2Score;
    private int scoreLimit;

    public int getTeam1Score() {
        return this.team1Score;
    }

    public void setTeam1Score(int score) {
        this.team1Score = score;
    }

    public int getTeam2Score() {
        return this.team2Score;
    }

    public void setTeam2Score(int score) throws IllegalArgumentException {
        if (score > scoreLimit) {
            System.out.println("Input score cannot exceed max score limit");
            throw new IllegalArgumentException();
        }

        this.team2Score = score;
    }

    public int getScoreLimit() {
        return this.scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public void addScoreTeam1(int amount) {
        team1Score += amount;
    }

    public void addScoreTeam2(int amount) {
        team2Score += amount;
    }

}
