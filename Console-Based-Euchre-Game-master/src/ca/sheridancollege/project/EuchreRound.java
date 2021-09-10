package ca.sheridancollege.project;

import ca.sheridancollege.project.Card.Suit;
import ca.sheridancollege.project.Card.Value;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing a single round of Euchre (5 tricks)
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

public class EuchreRound {

    private EuchrePlayer dealer;
    private Suit trumpSuit;
    private Card rightBower;
    private Card leftBower;
    private RoundScore roundScore;
    private int makerTeamId;

    public EuchreRound(EuchrePlayer dealer) {
        this.dealer = dealer;
        this.roundScore = new RoundScore();
    }

    public RoundScore getRoundScore() {
        return roundScore;
    }

    public Suit getTrumpSuit() {
        return this.trumpSuit;
    }

    public void setTrumpSuit(Suit trumpSuit) {
        this.trumpSuit = trumpSuit;
    }

    public EuchrePlayer getDealer() {
        return this.dealer;
    }

    public void setDealer(EuchrePlayer dealer) {
        this.dealer = dealer;
    }

    public Card getRightBower() {
        return this.rightBower;
    }

    public void setRightBower(Card rightBower) {
        this.rightBower = rightBower;
    }

    public Card getLeftBower() {
        return this.leftBower;
    }

    public void setLeftBower(Card leftBower) {
        this.leftBower = leftBower;
    }

    public int getMakerTeamId() {
        return makerTeamId;
    }

    public void setMakerTeamId(int makerTeamId) {
        this.makerTeamId = makerTeamId;
    }
    
    

    public void promptUser(ArrayList<Player> players, int playerIndex) {
        System.out.println("------------------------------");
        System.out.println(players.get(playerIndex).getName() + "'s turn:");
        System.out.println("------------------------------");
        System.out.println("Your Hand: ");
        System.out.println(((EuchrePlayer) players.get(playerIndex)).getHand().toString());

        System.out.println("Top Card of Deck: ");
        System.out.println(EuchreDeck.getInstance().cards.get(0).toString());

        System.out.println("Choose from the following options: ");
        System.out.println("[1] Order Up (Set trump suit to " + EuchreDeck.getInstance().cards.get(0).getSuit() + " and dealer picks up top card.)");
        System.out.println("[2] Pass.");
    }

    public void orderUp() {        
        System.out.println("------------------------------");
        System.out.println(dealer.getName() + "'s turn:");
        System.out.println("------------------------------");
        System.out.println("Choose a card to remove from your hand: ");
        System.out.println("Your Hand: ");
        System.out.println(dealer.getHand().toString());
        
        Scanner input = new Scanner(System.in);
              
        while (true) {
            System.out.println("Make your selection by entering the corresponding number:");
            int option = input.nextInt();
            
            if (option > 0 && option < 6) {                
                dealer.getHand().getCards().remove(option-1);
                dealer.getHand().getCards().add(EuchreDeck.getInstance().cards.get(0));
                break;
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }
        }
        
        setTrumpSuit(EuchreDeck.getInstance().getCards().get(0).getSuit());
        EuchreDeck.getInstance().getCards().remove(0);
    }
    
    public void setWeightedValues(ArrayList<Player> players) {
        final int JACK_WEIGHTED_VALUE = 11;
        setRightBower(new EuchreCard(Value.JACK, trumpSuit, JACK_WEIGHTED_VALUE));
        
        Suit leftBowerSuit = null;
        if (trumpSuit == Suit.CLUBS) {
            leftBowerSuit = Suit.SPADES;
        }
        else if (trumpSuit == Suit.SPADES) {
            leftBowerSuit = Suit.CLUBS;
        }
        else if (trumpSuit == Suit.HEARTS) {
            leftBowerSuit = Suit.DIAMONDS;
        }
        else if (trumpSuit == Suit.DIAMONDS) {
            leftBowerSuit = Suit.HEARTS;
        }
        setLeftBower(new EuchreCard(Value.JACK, leftBowerSuit, JACK_WEIGHTED_VALUE));
        
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Card> cards = ((EuchrePlayer)players.get(i)).getHand().getCards();
            for (int j = 0; j < ((EuchrePlayer)players.get(i)).getHand().getCards().size(); j++) {
                if (cards.get(j).equals(this.rightBower)) {
                    ((EuchreCard)cards.get(j)).setWeightedValue(500);
                }
                if (((EuchrePlayer)players.get(i)).getHand().getCards().get(j).equals(this.leftBower)) {
                    ((EuchreCard)cards.get(j)).setWeightedValue(300);
                }
                if (((EuchrePlayer)players.get(i)).getHand().getCards().get(j).getSuit() == trumpSuit) {
                    ((EuchreCard)cards.get(j)).setWeightedValue(100);
                }                
            } 
        }
    }  
}

