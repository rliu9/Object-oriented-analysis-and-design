package coms362.cards.Slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.RulesDispatchBase;
import coms362.cards.abstractcomp.Table;
import coms362.cards.events.inbound.Event;
import coms362.cards.events.inbound.InitGameEvent;
import coms362.cards.events.inbound.SetQuorumEvent;
import coms362.cards.fiftytwo.P52InitCmd;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.fiftytwo.SetQuorumCmd;
import coms362.cards.model.Quorum;

public class P52SlapjackPickupRules extends RulesDispatchBase implements Rules {

    @Override
    public Move apply(InitGameEvent e, Table table, Player player) {
        return new P52InitCmd(table.getPlayerMap(), "COMS362 Slapjack Game", table);
    }

    @Override
    public Move apply(SetQuorumEvent e, Table table, Player player) {
        return new SetQuorumCmd(new Quorum(1, 1));
    }

	@Override
	public Move eval(Event nextE, Table table, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}