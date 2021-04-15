package coms362.cards.slapjack;

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
import coms362.cards.model.Pile;

public class SlapjackMove implements Move{

	private Card c;
	private Player p;
	private Pile fromPile;
	private Pile toPile;
	private String s = "";
	
	public SlapjackMove(Card c, Player p, Pile fromPile, Pile toPile){
		this.c = c;
		this.p = p;
		this.fromPile = fromPile;
		this.toPile = toPile;
	}
	@Override
	public void apply(Table table) {
		// TODO Auto-generated method stub
		Pile center = table.getPile(SlapjackRules.center_Pile);
		if(center.getCard(c.getRemoteId()) != null) {
			for(Card cards : center.getCards()){
				table.removeFromPile(SlapjackRules.center_Pile, cards);
				table.addToPile(s, cards);
				table.addToScore(p, 1);
			}
		}else {
			table.removeFromPile(s, c);
			table.addToPile(SlapjackRules.center_Pile, c);
			table.addToScore(p, -1);
		}
	}

	// from P52Move
	@Override
	public void apply(ViewFacade view) {
		view.send(new HideCardRemote(c));
		view.send(new RemoveFromPileRemote(fromPile, c));
		view.send(new AddToPileRemote(toPile, c));
		view.send(new ShowCardRemote(c));
		view.send(new ShowPlayerScore(p, null));
	}

}
