package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import java.util.ArrayList;

/**
 * A class representing a single trick of Euchre 
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

public class EuchreTrick {    
    private EuchreRound currRound;
    private Suit leadSuit = null;
    private GroupOfCards cardsPlayed = new GroupOfCards(4);
    
    public EuchreTrick(EuchreRound currRound) {
        this.currRound = currRound;
    }
    
    public GroupOfCards getCardsPlayed() {
        return cardsPlayed;
    }
    
    public void addCardsPlayed(EuchreCard card) {
        this.cardsPlayed.getCards().add(card);
    }

    public void setLeadSuit(Suit leadSuit) {
        this.leadSuit = leadSuit;
    }
    
    
    
    public void promptUser(ArrayList<Player> players, int playerIndex) {
        System.out.println("------------------------------");
        System.out.println(players.get(playerIndex).getName() + "'s turn:");
        System.out.println("------------------------------");
        System.out.println("Trump Suit: " + currRound.getTrumpSuit());
        if (this.leadSuit != null)
            System.out.println("Leading Suit: " + this.leadSuit);
        
        if (this.getCardsPlayed() != null)
            System.out.println("Cards played this trick:\n" + this.getCardsPlayed().toString());
        
        System.out.println("Your Hand: ");
        System.out.println(((EuchrePlayer) players.get(playerIndex)).getHand().toString());

    }
    
    public void setWeightedValues(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Card> cards = ((EuchrePlayer)players.get(i)).getHand().getCards();
            for (int j = 0; j < ((EuchrePlayer)players.get(i)).getHand().getCards().size(); j++) {
                if (cards.get(j).getSuit() == leadSuit && cards.get(j).getSuit() != currRound.getTrumpSuit() && !(cards.get(j).equals(currRound.getRightBower())) && !(cards.get(j).equals(currRound.getLeftBower()))) {
                    ((EuchreCard)cards.get(j)).setWeightedValue(50);
                }                
            }       
        }
    }
    
    public EuchrePlayer determineTrickWinner (ArrayList<Player> players) {
        EuchrePlayer winningPlayer = null;
        int winningValue = 0;
        for (int i = 0; i < cardsPlayed.getCards().size(); i++) {
            if (((EuchrePlayer)players.get(i)).getTrickCardPlayed().getWeightedValue() > winningValue) {
                winningPlayer = ((EuchrePlayer)players.get(i));
                winningValue = ((EuchrePlayer)players.get(i)).getTrickCardPlayed().getWeightedValue();
            }
        }
        return winningPlayer;
    }
}