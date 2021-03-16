package coms362.cards.events.remote;

import coms362.cards.abstractcomp.Player;
import coms362.cards.streams.Marshalls;

public class UpdateTextRemote implements Marshalls {

	private Player p; 
	
	public UpdateTextRemote(Player p, int i) {
		this.p = p;
	}

	public String marshall() {
		return "";
	}
	
	public String stringify(){
		return "UpdateTextRemote Player="+ p.getPlayerNum();
	}

}
