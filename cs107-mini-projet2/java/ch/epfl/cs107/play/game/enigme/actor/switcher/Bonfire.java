/*
 *	Author:		Julian Blackwell
 *	Date:		10 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Animation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Bonfire extends Switcher implements Logic {

	private Logic signal;
	private Animation animationOn;
	private Sprite imageOff;
	
	public Bonfire(Area area, Orientation orientation, DiscreteCoordinates position, boolean isOn, Logic signal) {
		super(area, orientation, position, isOn);
		this.signal = signal;
		this.imageOff = new Sprite("fire.off", 0.5f * 2f, 0.65625f * 2f, this);
		this.animationOn = new Animation(this, Vector.ZERO, 2f, 10, "fire.on.1", "fire.on.2");
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
	public void draw(Canvas canvas) {
		if(signal.isOn()) {
			animationOn.updateAnimationCounter();
			animationOn.draw(canvas, getOrientation());
		} else {
			imageOff.draw(canvas);
		}
	}

	@Override
	public boolean isOn() {
		return signal.isOn();
	}
}
