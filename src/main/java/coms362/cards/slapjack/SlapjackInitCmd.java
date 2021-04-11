package coms362.cards.slapjack;

import java.util.Map;
import java.util.Random;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.CreateButtonRemote;
import coms362.cards.events.remote.CreatePileRemote;
import coms362.cards.events.remote.SetBottomPlayerTextRemote;
import coms362.cards.events.remote.SetGameTitleRemote;
import coms362.cards.events.remote.SetupTable;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.model.Card;
import coms362.cards.model.Location;
import coms362.cards.model.Pile;

public class SlapjackInitCmd implements Move{

	public Map<Integer, Player> players;
	Table table;
	Pile p1;
	Pile p2;
	String title_name = "COMS362 Slapjack Game";
	
	public SlapjackInitCmd(Map<Integer, Player> players, Table table) {
		this.players = players;
		this.p1 = new Pile("player1", new Location(300, 500));
		this.p2 = new Pile("player2", new Location(300, 100));
		this.table = table;
	}
	@Override
	public void apply(Table table) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void apply(ViewFacade views) {
		// TODO Auto-generated method stub
		
	}
	
}
