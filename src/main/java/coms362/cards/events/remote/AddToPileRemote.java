package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class AddToPileRemote implements Marshalls {
	private String pileName;
	private Card c;

	public AddToPileRemote(String pileName, Card c) {
		this.pileName = pileName;
		this.c = c;
	}

	public String marshall() {
		return "";
	}

	public String stringify() {
		return String.format("AddToPileRemote p=%s, c=%d", pileName, c.getId());
	}

}
