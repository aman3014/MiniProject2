package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Lever which represents a lever
 * extends Switcher;
 * @author Aman Bansal, Julian Blackwell
 */
public class Lever extends Switcher {

	private Sprite imageOn, imageOff;
	
	/**
	 * Constructor of a lever with default inital state off
	 * @param area (Area) : the area to which the lever belongs
	 * @param position (DiscreteCoordinates) : the position of the lever in the area
	 */
	public Lever(Area area, DiscreteCoordinates position) {
		super(area, position, false, new Dialog("Press L to turn me on/off", "dialog.1", area));
		this.imageOn = new Sprite("lever.big.left", 1, 1.f, this);
		this.imageOff = new Sprite("lever.big.right", 1, 1.f, this);
	}

	/**
	 * Constructor of a lever
	 * @param area (Area) : the area to which the lever belongs
	 * @param position (DiscreteCoordinates) : the position of the lever in the area
	 * @param isOn (boolean) : the initial state of the lever
	 */
	public Lever(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, position, isOn, new Dialog("Press L to turn me on/off", "dialog.1", area));
		this.imageOn = new Sprite("lever.big.left", 1, 1.f, this);
		this.imageOff = new Sprite("lever.big.right", 1, 1.f, this);
	}
	
	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if(isOn()) {
			imageOn.draw(canvas);
		} else {
			imageOff.draw(canvas);
		}
	}
}