package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class ShowCardRemote implements Marshalls {

	private Card c;

	public ShowCardRemote(Card c) {
		this.c = c;
	}

	public String marshall() {
		return String.format("allCards[%d].showCard();", c.getId());
	}

	public String stringify() {
		return "ShowCardRemote card= " + c.getId();
	}

}
