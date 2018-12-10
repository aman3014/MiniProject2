/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logicGates;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

public class Not extends LogicSignal {

	private Logic s;
	
	public Not(Logic s) {
		this.s = s;
	}
	
	@Override
	public boolean isOn() {
		if (s != null && !s.isOn()) {
			return true;
		} else {
			return false;
		}
	}
}
