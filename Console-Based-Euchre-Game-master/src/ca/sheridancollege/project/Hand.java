package ca.sheridancollege.project;

/**
 * A subclass of GroupOfCards representing a Euchre player's hand of cards
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */

import java.util.Iterator;

public class Hand extends GroupOfCards {

    public Hand() {
        super(5);
    }
    
    public void cardPlayed(int handIndex) {
        cards.remove(handIndex);
    }  
    
    @Override
    public String toString() {
        Iterator<Card> itCards = cards.iterator();
        String display = "";
        int iteration = 1;
        while(itCards.hasNext()) {
            
            Card card = itCards.next();
            display += "[" + iteration + "] " + card.toString();
            iteration++;
        }
        return display;
    }
}