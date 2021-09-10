package ca.sheridancollege.project;

/**
 * A subclass of Score representing the score of a Euchre game
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

public class GameScore extends Score {

    //10 point win limit
    private final int MAX_SCORE = 10;

    public GameScore() {
        setScoreLimit(MAX_SCORE);
    }
    
    public void addTrickScore(EuchreRound round) {
        if (round.getRoundScore().getTeam1Score() >= 3 && round.getMakerTeamId() != 1) {
            addScoreTeam1(2);
        }
        else if (round.getRoundScore().getTeam2Score() >= 3 && round.getMakerTeamId() != 2) {
            addScoreTeam2(2);
        }
        else if (round.getRoundScore().getTeam1Score() == 5){
            addScoreTeam1(2);
        }
        else if (round.getRoundScore().getTeam2Score() == 5){
            addScoreTeam2(2);
        }
        else if (round.getRoundScore().getTeam1Score() >= 3 && round.getMakerTeamId() == 1){
            addScoreTeam1(1);
        }
        else if (round.getRoundScore().getTeam2Score() >= 3 && round.getMakerTeamId() == 2){
            addScoreTeam2(1);
        }
    }

    //debug only
    public String toString() {
        String out = String.format("Game Score:\nTeam 1 - %s\nTeam 2 - %s\n", this.getTeam1Score(), this.getTeam2Score());
        return out;

    }

}
