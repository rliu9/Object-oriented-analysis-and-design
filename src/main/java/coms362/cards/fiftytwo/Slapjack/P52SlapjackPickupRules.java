package coms362.cards.fiftytwo.Slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.events.inbound.InitGameEvent;
import coms362.cards.events.inbound.SetQuorumEvent;
import coms362.cards.fiftytwo.P52InitCmd;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.fiftytwo.SetQuorumCmd;
import coms362.cards.model.Quorum;

public class P52SlapjackPickupRules extends P52Rules {

    @Override
    public Move apply(InitGameEvent e, Table table, Player player) {
        return new P52InitCmd(table.getPlayerMap(), "ComS362 Slapjack Game", table);
    }

    @Override
    public Move apply(SetQuorumEvent e, Table table, Player player) {
        return new SetQuorumCmd(new Quorum(1, 1));
    }

}
