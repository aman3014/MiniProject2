/*
 *	Author:		Julian Blackwell
 *	Date:		5 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends Switcher {

	private Sprite imageOn, imageOff;
	
	public PressureSwitch(Area area, DiscreteCoordinates position) {
		super(area, position, false);
		this.imageOn = new Sprite("GroundLightOn", 1, 1.f, this);
		this.imageOff = new Sprite("GroundLightOff", 1, 1.f, this);
	}
	
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