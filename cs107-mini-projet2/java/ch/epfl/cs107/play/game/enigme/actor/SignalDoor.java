/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {

	private Logic signal;
	private final Sprite close = new Sprite("door.close.1", 1, 1.f, this);
	
	public SignalDoor(Logic signal, Area area, String destAreaName, DiscreteCoordinates destCoord, DiscreteCoordinates position, DiscreteCoordinates... occupiedCells) {
		super(area, destAreaName, destCoord, position, occupiedCells);
		this.signal = signal;
	}
	
	public boolean isCellInteractable() {
		return signal.isOn();
	}
	
	public void draw(Canvas canvas) {
		if (signal.isOn()) {
			super.draw(canvas);
		} else {
			close.draw(canvas);
		}
	}
	
	@Override
	public boolean takeCellSpace() {
		return !signal.isOn();
	}
}