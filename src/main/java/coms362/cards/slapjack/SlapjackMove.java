package coms362.cards.slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;

public class SlapjackMove implements Move{

	private Card c;
	private Player p;
	private Pile fromPile;
	private Pile toPile;
	
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
	}

	@Override
	public void apply(ViewFacade views) {
		// TODO Auto-generated method stub
		
	}

}
