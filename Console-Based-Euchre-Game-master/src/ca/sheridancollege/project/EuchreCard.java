package ca.sheridancollege.project;

/**
 *
 * @author Kyle Welfare, April 2021
 * @author Jacob Bullock, April 2021
 * @author Owen Barrington, April 2021
 * @author Chris Yeung, April 2021
 */
public class EuchreCard extends Card{

    private int weightedValue;
    
    public EuchreCard(Value value, Suit suit, int weightedValue) {
        this.value = value;
        this.suit = suit;
        this.weightedValue = weightedValue;
    }

    public int getWeightedValue() {
        return weightedValue;
    }

    public void setWeightedValue(int weightedValue) {
        this.weightedValue += weightedValue;
    }
            
    @Override
    public String toString() {
        return getValue() + " of " + getSuit() + "\n";
    }


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
        final EuchreCard other = (EuchreCard) obj;
        if (this.value != other.value) {
            return false;
        }
        if (this.suit != other.suit) {
            return false;
        }
        return true;
    }
    
}
