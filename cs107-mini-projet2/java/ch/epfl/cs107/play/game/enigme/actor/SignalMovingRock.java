/*
 *	Author:      Aman Bansal
 *	Date:        11 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class SignalMovingRock extends MovingRock {

	private Logic signal;
	
	public SignalMovingRock(Logic signal, Area area, DiscreteCoordinates position) {
		super(area, position);
		this.signal = signal;
	}
	
	@Override
	public boolean isViewInteractable() {
		return signal.isOn();
	}
}