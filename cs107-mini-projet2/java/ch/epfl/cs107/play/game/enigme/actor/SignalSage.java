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
public class SignalSage extends Sage implements Logic {

	private Orientation correctOrientation;
	
	/**
	 * Constructor of a SignalSage
	 * @param area (Area) : the area to which the signal sage belongs
	 * @param orientation (Orientation) : the initial orientation of the signal sage
	 * @param position (DiscreteCoordinates) : the position of the signal sage in the area
	 * @param wisdom (String) : the wisdom/knowledge the sage can share with an interactor
	 * @param correctOrientation (Orientation) : the orientation of the sage for which the logic signal sage will be turned on
	 */
	public SignalSage(Area area, Orientation orientation, DiscreteCoordinates position, String wisdom, Orientation correctOrientation) {
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