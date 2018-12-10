/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logicGates;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

public class LogicNumber extends LogicSignal {

	private float nb;
	private List<Logic> e;
	
	public LogicNumber(float nb, Logic...logics) {
		
		this.nb = nb;
		e = new LinkedList<Logic>();
		
		for (Logic logic : logics) {
			this.e.add(logic);
		}
	}
	
	@Override
	public boolean isOn() {
		if (e.size() > 12 || nb < 0 || nb > Math.pow(2, e.size())) {
			return false;
		} else {
			float signalNumber = 0.0f;
			for (int i = 0; i < e.size(); ++i) {
				signalNumber += Math.pow(2, i) * e.get(i).getIntensity();
			}
			if (signalNumber == this.nb) {
				return true;
			} else {
				return false;
			}
		}
	}
}