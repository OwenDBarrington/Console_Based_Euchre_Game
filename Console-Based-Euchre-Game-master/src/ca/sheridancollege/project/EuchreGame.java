package ca.sheridancollege.project;

/**
 * A class representing a single game of Euchre
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */
public class EuchreGame extends Game {

    private GameScore gameScore;

    public EuchreGame(int currentGameNumber) {
        super("Euchre Game #", currentGameNumber);
        this.gameScore = new GameScore();
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    @Override
    public void play() {

    }

    public String welcomeMessage() {
        return ("Welcome to " + this.getName() + "!");
    }

    @Override
    public void declareWinner() {
        if (this.getGameScore().getTeam1Score() >= 10) {
            System.out.println("\n------------------------------------------------------------");
            System.out.println("Congratulations Team 1, you win!");
            System.out.println("------------------------------------------------------------\n");
        } else if (this.getGameScore().getTeam2Score() >= 10) {
            System.out.println("\n------------------------------------------------------------");
            System.out.println("Congratulations Team 2, you win!");
            System.out.println("------------------------------------------------------------\n");
        }
    }
}
