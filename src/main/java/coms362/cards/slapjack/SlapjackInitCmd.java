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
	String title_name = "";
	
	public SlapjackInitCmd(Map<Integer, Player> players, String title, Table table) {
		this.players = players;
		this.p1 = new Pile("player1", new Location(300, 500));
		this.p2 = new Pile("player2", new Location(300, 100));
		this.table = table;
		this.title_name = title;
	}
	@Override
	public void apply(Table table) {
		// TODO Auto-generated method stub
		Random r = table.getRandom();
		try {
            for (String suit : Card.suits) {
                for (int i = 1; i <= 13; i++) {
                    Card card = new Card();
                    card.setSuit(suit);
                    card.setRank(i);
                    card.setRotate(0);
                    card.setFaceUp(false);
                    
                    if(p1.cards.size() <= 26) {
                    	card.setX(p1.getLocation().getX());
                    	card.setY(p1.getLocation().getY());
                    	p1.cards.put(card.getRemoteId(), card);
                    }else {
                    	card.setX(p2.getLocation().getX());
                    	card.setY(p2.getLocation().getY());
                    	p2.cards.put(card.getRemoteId(), card);
                    }
                }
            }
            
            table.addPile(p1);
            table.addPile(p2);
            table.addPile(new Pile(SlapjackRules.center_Pile, new Location(300, 300)));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public void apply(ViewFacade views) {
		// TODO Auto-generated method stub
		views.send(new SetupTable());
		views.send(new SetGameTitleRemote(title_name));

		for (Player p : players.values()){
			String role = (p.getPlayerNum() == 1) ? "Dealer" : "Player "+p.getPlayerNum();
			views.send(new SetBottomPlayerTextRemote(role, p));
		}

		views.send(new CreatePileRemote(table.getPile(SlapjackRules.player1_pile)));
		views.send(new CreatePileRemote(table.getPile(SlapjackRules.player2_pile)));
		views.send(new CreatePileRemote(table.getPile(SlapjackRules.center_Pile)));
		DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
		views.register(dealButton); //so we can find it later. 
		views.send(new CreateButtonRemote(dealButton));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "reset", "RestartGame", "Reset", new Location(500,0)));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
	}
	
}
