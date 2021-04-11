package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Rules;
import coms362.cards.fiftytwo.P52MPGameFactory;

public class P52SlapjackGameFactory extends P52MPGameFactory {

	@Override
	public Rules createRules() {
		return new P52SlapjackPickupRules();
	}

}