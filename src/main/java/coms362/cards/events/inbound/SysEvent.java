package coms362.cards.events.inbound;

import java.util.List;

import coms362.cards.app.GameController;
import coms362.cards.model.PregameSetup;
import coms362.cards.model.Party;

public interface SysEvent extends Event {
	
	public void accept(GameController handler, PregameSetup game); 
}
