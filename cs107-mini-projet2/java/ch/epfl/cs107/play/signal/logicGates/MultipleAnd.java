package ch.epfl.cs107.play.signal.logicGates;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;

/**
 * Class modelizing the "AND" logic gate for multiple elements
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class MultipleAnd extends LogicSignal {

	//List of logic elements which are to be passed through the MultipleAnd function
	private List<Logic> logics;
	
	/**
	 * Default constructor
	 * @param logics (List<Logic>) : list of logic elements to pass through the MultipleAnd 'gate'
	 */
	public MultipleAnd(Logic...logics) {
		this.logics = new LinkedList<Logic>();
		for (Logic logic : logics) {
			this.logics.add(logic);
		}
	}
	
	//Extends LogicSignal
	
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
