/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */
public abstract class Card {
    //default modifier for child classes
    public enum Suit {
        HEARTS, DIAMONDS, SPADES, CLUBS
    }
    public enum Value {
        NINE, TEN, JACK, QUEEN, KING, ACE
    }
    
    protected Value value;
    protected Suit suit;

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
    
    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */  
    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.suit != other.suit) {
            return false;
        }
        return true;
    }
    
    

}
