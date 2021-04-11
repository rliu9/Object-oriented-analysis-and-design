package coms362.cards.slapjack;

import coms362.cards.abstractcomp.Rules;
import coms362.cards.fiftytwo.P52MPGameFactory;

public class SlapjackGameFactory extends P52MPGameFactory {

	@Override
	public Rules createRules() {
		return new SlapjackRules();
	}

}
