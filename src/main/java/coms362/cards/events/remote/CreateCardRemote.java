package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class CreateCardRemote implements Marshalls{
	Card c;

	public CreateCardRemote(Card c) {
		this.c = c;
	}

	public String marshall(){
        /*
        return String.format(
            "card1 = new cards.Card(\'%s\',\'%d', cards.options.table);\n"
            + "allCards[%d] = card1;\n",
            c.getSuit(), c.getRank(),
            c.getId()
        );
        */
        
        return String.format(
                "cards362.createCard(\'%s',\'%s\',\'%s\');\n",
                c.getRemoteId(), c.getSuit(), c.getRank()
        );
	}

	public String stringify() {
		return "CreateRemote Card id="+c.getRemoteId();
	}

}
