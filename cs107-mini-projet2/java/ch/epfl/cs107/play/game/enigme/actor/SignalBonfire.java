package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Bonfire;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

/**
 * Class SignalBonfire representing a Bonfire which acts as a logic signal
 * extends Bonfire; implements Logic
 * @author Julian Blackwell, Aman Bansal
 */
public class SignalBonfire extends Bonfire implements Logic {

	private Logic signal;
	
	/**
	 * Constructor of a Signal Bonfire
	 * @param area (Area) : the area to which the signal bonfire belongs
	 * @param position (DiscreteCoordinates) : the position of the signal bonfire in the area
	 * @param isOn (boolean) : the initial state of the signal bonfire
	 * @param signal (Logic) : the logic signal on which the state of the signal bonfire depends
	 */
	public SignalBonfire(Area area, DiscreteCoordinates position, boolean isOn, Logic signal) {
		super(area, position, isOn);
		this.signal = signal;
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isOn() {
		return signal.isOn();
	}
}
