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
public class Pile {

	public String name;
	public String visible;
	public Map<Integer, Card> cards = new HashMap<Integer, Card>();
	private Location loc = new Location(0,0);
	
	public Pile (String name, Location loc){
		this.name = name;
		this.loc = loc;
	}

	public Location getLocation() {
		return loc;
	}
	
	public void addCard(Card c){
		cards.put(c.getId(), c);
	}

	public Card getCard(String id) {
		return cards.get(new Integer(id));
	}

	public String getName() {
		return name;
	}

}
