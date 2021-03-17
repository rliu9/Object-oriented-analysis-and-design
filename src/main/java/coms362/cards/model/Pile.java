package coms362.cards.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A pile is a slight generalization of the common concept "deck". 
 * Piles are just collections of cards that share a nominal position
 * in the view.
 * 
 * @author Robert Ward
 */
public class Pile extends PresentationObject {

    public String visible;
    public Map<String, Card> cards = new HashMap<String, Card>();
    private Location loc = new Location(0,0);
    
    public Pile (String selector, Location loc){
        super(selector);
        this.loc = loc;
    }

    public Location getLocation() {
        return loc;
    }
    
    public void addCard(Card c) {
        cards.put(c.getRemoteId(), c);
    }

    public Card getCard(String id) {
        return cards.get(id);
    }

}
