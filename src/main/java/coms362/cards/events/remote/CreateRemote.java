package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class CreateRemote implements Marshalls{
	Card c;

	public CreateRemote(Card c) {
		this.c = c;
	}

	public String marshall(){
    	return String.format(
    		"card1 = new cards.Card(\'%s\',\'%d', cards.options.table);\n"
			+ "allCards[%d] = card1;\n",
			c.getSuit(), c.getNumber(),
			c.getId()
		);

	}

	public String stringify() {
		return "CreateRemote Card id="+c.getId();
	}
}
