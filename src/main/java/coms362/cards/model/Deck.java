package coms362.cards.model;

public class Deck extends Pile {
	//location
	//member cards
	//orientation
	//id
	//remote varName
	//shuffle
	
	public Deck ( String name, Location loc) {
		super( name, loc);
        for (String suit : Card.suits) {
            for (int i = 1; i <= 13; i++) {
                Card card = new Card();
                card.setSuit(suit);
                card.setNumber(i);
                card.setX(loc.x);
                card.setY(loc.y);
                card.setRotate(90);
                card.setFaceUp(false);
                cards.put(card.getId(), card);
            }
        }
	}
}
