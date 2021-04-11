package coms362.cards.slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.AddToPileBottomRemote;
import coms362.cards.events.remote.CreateCardRemote;
import coms362.cards.events.remote.CreatePileRemote;
import coms362.cards.events.remote.HideButtonRemote;
import coms362.cards.events.remote.UpdateCardRemote;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.model.Card;
import coms362.cards.model.Location;
import coms362.cards.model.Pile;

public class P52SlapjackDeal implements Move{
	Player players;
	Table table;
	public P52SlapjackDeal(Table table, Player players) {
		this.table = table;;
		this.players = players;
	}
	@Override
	public void apply(Table table) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(ViewFacade views) {
		// TODO Auto-generated method stub
		try {
            String remoteId = views.getRemoteId(DealButton.kSelector);
            views.send(new HideButtonRemote(remoteId));
            Pile p1 = table.getPile("player1");
            Pile p2 = table.getPile("player2");
            if (p1 == null || p2 == null) {
                return;
            }
            for (Card c : p1.getCards()) {
                String outVal = "";
                views.send(new CreateCardRemote(c));
                views.send(new UpdateCardRemote(c));
                System.out.println(outVal);
            }
            for (Card c : p2.getCards()) {
                String outVal = "";
                views.send(new CreateCardRemote(c));
                views.send(new UpdateCardRemote(c));
                System.out.println(outVal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
