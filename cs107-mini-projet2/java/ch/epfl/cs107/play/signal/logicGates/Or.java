package ch.epfl.cs107.play.signal.logicGates;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

/**
 * Class modelizing the "OR" logic gate for two logic signals
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class Or extends LogicSignal {

	//Two Logic to be processed
	private Logic one, two;
	
	/**
	 * Default constructor
	 * @param one (Logic) : first logic element in the OR function
	 * @param two (Logic) : second logic element in the OR function
	 */
	public Or(Logic one, Logic two) {
		this.one = one;
		this.two = two;
	}
	
	//Extends LogicSignal
	
	@Override
	public boolean isOn() {
		if (one != null && two != null && (one.isOn() || two.isOn())) {
			return true;
		} else {
			return false;
		}
	}
}
