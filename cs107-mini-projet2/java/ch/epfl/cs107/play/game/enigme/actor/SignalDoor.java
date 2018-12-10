package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class SignalDoor representing a door which depends on a Logic; extends Door
 * @author Julian Blackwell, Aman Bansal
 */
public class SignalDoor extends Door {

	private Logic signal;
	private final Sprite close = new Sprite("door.close.1", 1, 1.f, this);
	
	/**
	 * Constructor of a Signal Door
	 * @param signal (Logic) : the logic signal on which the signal door depends
	 * @param area (Area) : the area to which the signal door belongs
	 * @param destAreaName (String) : the name of the destination area of the signal door
	 * @param destCoord (DiscreteCoordinates) : the coordinates of an interactor in the destination area when it interacts through the signal door
	 * @param position (DiscreteCoordinates) : the coordinates of the signal door in the area
	 * @param occupiedCells (DiscreteCoordinates...) : the coordinates of the cells occupied by the signal door
	 */
	public SignalDoor(Logic signal, Area area, String destAreaName, DiscreteCoordinates destCoord, DiscreteCoordinates position, DiscreteCoordinates... occupiedCells) {
		super(area, destAreaName, destCoord, position, occupiedCells);
		this.signal = signal;
	}
	
	@Override
	public boolean isCellInteractable() {
		return signal.isOn();
	}
	
	@Override
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