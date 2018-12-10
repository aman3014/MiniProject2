package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class PressureSwitch which represents a pressure switch
 * extends Switcher;
 * @author Aman Bansal, Julian Blackwell
 */
public class PressureSwitch extends Switcher {

	private Sprite imageOn, imageOff;
	
	/**
	 * Constructor of a pressure switch with default initial state off
	 * @param area (Area) : the area to which the pressure switch belongs
	 * @param position (DiscreteCoordinates) : the position of the pressure switch in the area
	 */
	public PressureSwitch(Area area, DiscreteCoordinates position) {
		super(area, position, false);
		this.imageOn = new Sprite("GroundLightOn", 1, 1.f, this);
		this.imageOff = new Sprite("GroundLightOff", 1, 1.f, this);
	}
	
	/**
	 * Constructor of a pressure switch
	 * @param area (Area) : the area to which the pressure switch belongs
	 * @param position (DiscreteCoordinates) : the position of the pressure switch in the area
	 * @param isOn (boolean) : the initial state of the pressure switch
	 */
	public PressureSwitch(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, position, isOn);
		this.imageOn = new Sprite("GroundLightOn", 1, 1.f, this);
		this.imageOff = new Sprite("GroundLightOff", 1, 1.f, this);
	}
	

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
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