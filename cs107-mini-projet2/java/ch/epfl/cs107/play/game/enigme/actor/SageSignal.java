/*
 *	Author:		Julian Blackwell
 *	Date:		10 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Sage;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

/**
 * Class SageSignal representing a sage which acts as a logic signal
 * extends Sage; implements Logic
 * @author Aman Bansal, Julian Blackwell
 */
public class SageSignal extends Sage implements Logic {

	private Orientation correctOrientation;
	
	/**
	 * 
	 * @param area
	 * @param orientation
	 * @param position
	 * @param wisdom
	 * @param correctOrientation
	 */
	public SageSignal(Area area, Orientation orientation, DiscreteCoordinates position, String wisdom, Orientation correctOrientation) {
		super(area, orientation, position, wisdom);
		this.correctOrientation = correctOrientation;
	}

	@Override
	public boolean isOn() {
		if(getOrientation().equals(correctOrientation)) {
			return true;
		} else {
			return false;
		}
	}
}