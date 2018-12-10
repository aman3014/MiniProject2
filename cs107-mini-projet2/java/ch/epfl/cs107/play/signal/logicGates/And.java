/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logicGates;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

public class And extends LogicSignal {

	private Logic one, two;
	
	public And(Logic one, Logic two) {
		this.one = one;
		this.two = two;
	}
	
	@Override
	public boolean isOn() {
		if (one != null && two != null && one.isOn() && two.isOn()) {
			return true;
		} else {
			return false;
		}
	}
}
