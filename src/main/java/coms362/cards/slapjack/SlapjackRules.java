package coms362.cards.slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.RulesDispatchBase;
import coms362.cards.abstractcomp.Table;
import coms362.cards.events.inbound.CardEvent;
import coms362.cards.events.inbound.ConnectEvent;
import coms362.cards.events.inbound.DealEvent;
import coms362.cards.events.inbound.Event;
import coms362.cards.events.inbound.EventUnmarshallers;
import coms362.cards.events.inbound.GameRestartEvent;
import coms362.cards.events.inbound.InitGameEvent;
import coms362.cards.events.inbound.NewPartyEvent;
import coms362.cards.events.inbound.SetQuorumEvent;
import coms362.cards.fiftytwo.CreatePlayerCmd;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.fiftytwo.SetQuorumCmd;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;
import coms362.cards.model.Quorum;

public class SlapjackRules extends RulesDispatchBase
implements Rules, RulesDispatch {
    
    public static final String player1_pile = "player1Pile";
    public static final String player2_pile = "player2Pile";
    public static final String center_pile = "centerPile";
	
	public SlapjackRules() {
		registerEvents();
	}
	
	public Move apply(InitGameEvent e, Table table, Player player){
		return new SlapjackInitCmd(table.getPlayerMap(), "Slapjck", table);
	}
	
	public Move apply(DealEvent e, Table table, Player player){
		return new SlapjackDeal(table, player);
	}
	
	public Move apply(CardEvent e, Table table, Player player){	
		Pile fromPile = table.getPile(player1_pile);
		Pile toPile = table.getPile(center_pile);
		Card c = fromPile.getCard(e.getId());
		if (c == null) {
			return new SlapjackDropEventCmd();
		}
		return new SlapjackMove(c, player, fromPile, toPile);		
	}
	
	public Move apply(NewPartyEvent e, Table table, Player player){
		if (e.getRole() == PartyRole.player){
			return new CreatePlayerCmd( e.getPosition(), e.getSocketId());
		}
		return new SlapjackDropEventCmd();
	}
	
	public Move apply(SetQuorumEvent e, Table table, Player player){
		return new SetQuorumCmd(new Quorum(1, 1));
	}
	
	public Move eval(Event nextE, Table table, Player player) {
		return nextE.dispatch(this, table, player);
	}
	
	public Move apply(ConnectEvent e, Table table, Player player){
		Move rval = new SlapjackDropEventCmd(); 
		System.out.println("Rules apply ConnectEvent "+e);
		if (! table.getQuorum().exceeds(table.getPlayers().size()+1)){
			if (e.getRole() == PartyRole.player){				
				rval =  new CreatePlayerCmd( e.getPosition(), e.getSocketId());
			}			
		}
		System.out.println("SlapjackRules connectHandler rval = "+rval);
		return rval;
	}

	/**
	 * We rely on Rules to register the appropriate input events with
	 * the unmarshaller. This avoids excessive complexity in the 
	 * abstract factory and there is a natural dependency between 
	 * the rules and the game input events.  
	 */
	private void registerEvents() {
		EventUnmarshallers handlers = EventUnmarshallers.getInstance();
		handlers.registerHandler(InitGameEvent.kId, (Class) InitGameEvent.class); 
		handlers.registerHandler(DealEvent.kId, (Class) DealEvent.class); 
		handlers.registerHandler(CardEvent.kId, (Class) CardEvent.class); 
		handlers.registerHandler(GameRestartEvent.kId, (Class) GameRestartEvent.class); 
		handlers.registerHandler(NewPartyEvent.kId, (Class) NewPartyEvent.class);
	}
}