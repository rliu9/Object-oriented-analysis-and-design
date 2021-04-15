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
import coms362.cards.fiftytwo.DealCommand;
import coms362.cards.fiftytwo.DropEventCmd;
import coms362.cards.fiftytwo.P52InitCmd;
import coms362.cards.fiftytwo.P52Move;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.fiftytwo.SetQuorumCmd;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;
import coms362.cards.model.Quorum;

/*
 * -------------------------------------------not sure
 * --------------------------------------feel free to edit.
 */
public class SlapjackRules extends RulesDispatchBase
implements Rules, RulesDispatch  {
	
    public static final String player1_pile = "player1Pile";
    public static final String player2_pile = "player2Pile";
    public static final String center_Pile = "centerPile";
    public String title_name = "Slapjack Game";
    public boolean p1_turn = true;
    public boolean p2_turn = false;
    
    public SlapjackRules() {
    	registerEvents();
    }
    
	@Override
    public Move apply(InitGameEvent e, Table table, Player player) {
        return new SlapjackInitCmd(table.getPlayerMap(), this.title_name, table);
    }

	@Override
	public Move apply(CardEvent e, Table table, Player player){	
		Pile fromPile = table.getPile(player1_pile);
		Pile toPile = table.getPile(center_Pile);
		Card c = fromPile.getCard(e.getId());
		if (c == null) {
			return new DropEventCmd();
		}
		return new P52Move(c, player, fromPile, toPile);
	}

	
	public Move apply(DealEvent e, Table table, Player player){
		return new DealCommand(table, player);
	}
    
    //  from P52Rules
	public Move apply(ConnectEvent e, Table table, Player player){
		Move rval = new DropEventCmd(); 
		System.out.println("Rules apply ConnectEvent "+e);
		if (! table.getQuorum().exceeds(table.getPlayers().size()+1)){
			if (e.getRole() == PartyRole.player){				
				rval =  new CreatePlayerCmd( e.getPosition(), e.getSocketId());
			}			
		}
		System.out.println("P52SlapjackPickup connectHandler rval = "+rval);
		return rval;
	}
	
    @Override
	public Move apply(SetQuorumEvent e, Table table, Player player){
    	return new SetQuorumCmd(e.getQuorum());	
    }
    
	@Override
	public Move eval(Event nextE, Table table, Player player) {
		// TODO Auto-generated method stub
		return nextE.dispatch(this, table, player);
	}

	private void registerEvents() {
		// TODO Auto-generated method stub
		EventUnmarshallers handlers = EventUnmarshallers.getInstance();
		handlers.registerHandler(InitGameEvent.kId, (Class) InitGameEvent.class); 
		handlers.registerHandler(DealEvent.kId, (Class) DealEvent.class); 
		handlers.registerHandler(CardEvent.kId, (Class) CardEvent.class); 
		handlers.registerHandler(GameRestartEvent.kId, (Class) GameRestartEvent.class); 
		handlers.registerHandler(NewPartyEvent.kId, (Class) NewPartyEvent.class);
	}
}
