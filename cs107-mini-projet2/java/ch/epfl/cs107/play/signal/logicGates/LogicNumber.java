package ch.epfl.cs107.play.signal.logicGates;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;


/**
 * Class modelizing an interpretation of a set of signals as a power of 2
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class LogicNumber extends LogicSignal {

	//number compared to signalNumber
	private float nb;
	
	// Set/List of signals
	private List<Logic> e;
	
	/**
	 * Default constructor
	 * @param nb (float) : number used for comparisons in the computation of LogicNumber and its boolean state
	 * @param logics (List<Logic>) : set/list of signals
	 */
	public LogicNumber(float nb, Logic...logics) {
		
		this.nb = nb;
		e = new LinkedList<Logic>();
		
		for (Logic logic : logics) {
			this.e.add(logic);
		}
	}
	
	// Extends LogicSignal
	
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