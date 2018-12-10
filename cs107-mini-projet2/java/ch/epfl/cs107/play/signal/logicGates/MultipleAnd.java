/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logicGates;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

public class MultipleAnd extends LogicSignal {

	private List<Logic> logics;
	
	public MultipleAnd(Logic...logics) {
		this.logics = new LinkedList<Logic>();
		for (Logic logic : logics) {
			this.logics.add(logic);
		}
	}
	
	@Override
	public boolean isOn() {
		for (Logic logic : logics) {
			if (logic == null || !logic.isOn()) {
				return false;
			}
		}
		
		return true;
	}
}
