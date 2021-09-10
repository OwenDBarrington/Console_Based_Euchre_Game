package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import ca.sheridancollege.project.Card.Value;
import java.util.Scanner;

/**
 * Class containing main method to run program for games of Euchre
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */
public class EuchrePlayGame {

    public static void main(String[] args) {
        boolean isPlaying = true;
        int currentGameNumber = 1;
        EuchreDeck deck = EuchreDeck.getInstance();

        Value[] cardValues = Value.values();
        Suit[] cardSuits = Suit.values();

        System.out.println("SYST17796 Group 13's Euchre Game Project");
        System.out.println("Authors: Kyle Welfare, Chris Yeung, Owen Barrington, Jacob Bullock");
        while (isPlaying) {
            EuchreGame game = new EuchreGame(currentGameNumber);
            game.welcomeMessage();

            boolean nameUnique = true;
            for (int i = 1; i <= 4; i++) {
                Scanner input = new Scanner(System.in);
                while (true) {
                    System.out.println("Please enter a name for player " + i + ":");
                    String name = input.nextLine();
                    for (Player player : game.getPlayers()) {
                        if (!name.equalsIgnoreCase(player.getName())) {
                            nameUnique = true;
                        } else {
                            nameUnique = false;
                        }
                    }
                    if (nameUnique) {
                        game.getPlayers().add(new EuchrePlayer(name, i));
                        break;
                    } else {
                        System.out.println("That name is taken, try again.");
                    }
                }
            }

            while (game.getGameScore().getTeam1Score() < game.getGameScore().getScoreLimit() && game.getGameScore().getTeam2Score() < game.getGameScore().getScoreLimit()) {

                EuchreRound round = new EuchreRound((EuchrePlayer) game.getPlayers().get(0));
                deck.resetDeck(cardValues, cardSuits);
                deck.deal(game.getPlayers());
                System.out.println("");
                System.out.println(game.getPlayers().get(0).getName() + " dealt this round.\n");

                System.out.println("------------------------------------------------------------");
                System.out.println("Making the Trump");
                System.out.println("------------------------------------------------------------");

                outerloop:
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    if (i == game.getPlayers().size() - 1) {
                        round.promptUser(game.getPlayers(), 0);
                    } else {
                        round.promptUser(game.getPlayers(), i + 1);
                    }
                    Scanner input = new Scanner(System.in);

                    while (true) {
                        System.out.println("Make your selection by entering the corresponding number:");
                        int option = input.nextInt();
                        if (option == 1) {
                            round.orderUp();
                            round.setMakerTeamId(((EuchrePlayer) game.getPlayers().get(i)).getTeam());
                            break outerloop;
                        } else if (option == 2 && i != game.getPlayers().size() - 1) {
                            break;
                        } //if the last player passes redeal the deck
                        else if (option == 2 && i == game.getPlayers().size() - 1) {
                            round = new EuchreRound((EuchrePlayer) game.getPlayers().get(0));
                            deck.resetDeck(cardValues, cardSuits);
                            deck.deal(game.getPlayers());
                            System.out.println("\n------------------------------------------------------------");
                            System.out.println("Everyone passed so cards are dealt again.");
                            System.out.println("------------------------------------------------------------\n");
                            i = -1;
                            continue outerloop;
                        } else {
                            System.out.println("Invalid selection. Please try again.");
                        }
                    }
                }
                round.setWeightedValues(game.getPlayers());

                //tricks 
                System.out.println("------------------------------------------------------------");
                System.out.println("Play Begins");
                System.out.println("------------------------------------------------------------");

                EuchrePlayer winningPlayer = (EuchrePlayer) game.getPlayers().get(1);

                for (int i = 0; i < 5; i++) {
                    EuchreTrick trick = new EuchreTrick(round);

                    for (int j = game.getPlayers().indexOf(winningPlayer); j < game.getPlayers().size(); j++) {
                        trick.promptUser(game.getPlayers(), j);
                        Scanner input = new Scanner(System.in);

                        outerloop2:
                        while (true) {
                            System.out.println("Make your selection by entering the corresponding number. You must play a card matching the lead suit if you have one.");
                            int option = input.nextInt();

                            if (option > 0 && option <= ((EuchrePlayer) game.getPlayers().get(j)).getHand().getCards().size()) {
                                EuchreCard cardPlayed = (EuchreCard) ((EuchrePlayer) game.getPlayers().get(j)).getHand().getCards().get(option - 1);
                                ((EuchrePlayer) game.getPlayers().get(j)).setTrickCardPlayed(cardPlayed);
                                trick.addCardsPlayed(cardPlayed);
                                if (j == 0) {
                                    trick.setLeadSuit(cardPlayed.getSuit());
                                }
                                ((EuchrePlayer) game.getPlayers().get(j)).getHand().getCards().remove(option - 1);

                                break;
                            } else {
                                System.out.println("Invalid selection. Please try again.");
                            }
                        }
                        if (j == game.getPlayers().size() - 1) {
                            j = -1;
                        }
                        if (j == game.getPlayers().indexOf(winningPlayer) - 1) {
                            break;
                        }
                    }
                    winningPlayer = trick.determineTrickWinner(game.getPlayers());
                    round.getRoundScore().addTrick(winningPlayer);
                    System.out.println("Team 1 Round Score: " + round.getRoundScore().getTeam1Score());
                    System.out.println("Team 2 Round Score: " + round.getRoundScore().getTeam2Score());
                }
                game.getGameScore().addTrickScore(round);
                System.out.println("\nTeam 1 Game Score: " + game.getGameScore().getTeam1Score());
                System.out.println("Team 2 Game Score: " + game.getGameScore().getTeam2Score());

                EuchrePlayer playerTemp = (EuchrePlayer) game.getPlayers().get(0);
                game.getPlayers().remove(0);
                game.getPlayers().add(playerTemp);
            }

            game.declareWinner();

            currentGameNumber++;

            System.out.println("Do you want to play again?");
            System.out.println("[1] Play again");
            System.out.println("[2] Exit");
            System.out.println("Enter the corresponding number to make your selection: ");

            Scanner input = new Scanner(System.in);
            int option = input.nextInt();

            while (true) {
                if (option == 1) {
                    break;
                } else if (option == 2) {
                    isPlaying = false;
                    break;
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            }
        }
    }
}
