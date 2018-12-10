package ch.epfl.cs107.play.signal.logicGates;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

/**
 * Class modelizing the AND logic gate
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class And extends LogicSignal {

	//two signals to be passed through AND gate
	private Logic one, two;
	
	/**
	 * Default constructor
	 * @param one (Logic) : first signal in AND proposition
	 * @param two (Logic) : second signal in AND proposition
	 */
	public And(Logic one, Logic two) {
		this.one = one;
		this.two = two;
	}
	
	// Extends LogicSignal
	
	@Override
	public boolean isOn() {
		if (one != null && two != null && one.isOn() && two.isOn()) {
			return true;
		} else {
			return false;
		}
	}
}
