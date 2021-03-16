package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.AddToPileRemote;
import coms362.cards.events.remote.HideCardRemote;
import coms362.cards.events.remote.RemoveFromPileRemote;
import coms362.cards.events.remote.ShowCardRemote;
import coms362.cards.events.remote.ShowPlayerScore;
import coms362.cards.model.Card;

public class P52Move implements Move {
	
	private Card c;
	private Player p;
	
	public P52Move(Card c, Player p){
		this.c = c;
		this.p = p; 
	}
	
	public void apply(Table table){
		table.removeFromPile("discardPile", c);
		table.addToPile("Tidy", c);
		table.addToScore(p, 1);
	}
	
	public void apply(ViewFacade view){

		view.send(new HideCardRemote(c));
		view.send(new RemoveFromPileRemote("Random", c));
		view.send(new AddToPileRemote("Tidy", c));
		view.send(new ShowCardRemote(c));
		view.send(new ShowPlayerScore(p, null));

	}
	
	
}
