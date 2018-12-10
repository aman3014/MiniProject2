/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logicGates;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

/**
 * Class modelizing the "NOT" logic gate
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class Not extends LogicSignal {

	//Logic element to be passed through the "NOT" funciton
	private Logic s;
	
	/**
	 * Default constructor
	 * @param s (Logic) : logic signal to be passed through the NOT function
	 */
	public Not(Logic s) {
		this.s = s;
	}
	
	//Extends LogicSignal
	
	@Override
	public boolean isOn() {
		if (s != null && !s.isOn()) {
			return true;
		} else {
			return false;
		}
	}
}
