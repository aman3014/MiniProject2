package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Animation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends Switcher {

	// Useful for the animation of the sprite
	private Sprite imageOff;
	private Animation animationOn;
	
	/**
	 * Constructor of a torch
	 * @param area (Area) : the area to which the torch belongs
	 * @param position (DiscreteCoordinates) : the position of the torch in the area
	 * @param isOn (boolean) : the initial state of the torch
	 */
	public Torch(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, position, isOn, new Dialog("Press L to turn me on/off", "dialog.1", area));
		animationOn = new Animation(this, Vector.ZERO, 2f, 10, "torch.ground.on.1", "torch.ground.on.2");
		this.imageOff = new Sprite("torch.ground.off", 0.5f * 2f, 0.65625f * 2f, this);
	}
	
	/**
	 * Constructor of a torch with default initial state off
	 * @param area (Area) : the area to which the torch belongs
	 * @param position (DiscreteCoordinates) : the position of the torch in the area
	 */
	public Torch(Area area, DiscreteCoordinates position) {
		super(area, position, false, new Dialog("Press L to turn me on/off", "dialog.1", area));
		animationOn = new Animation(this, Vector.ZERO, 2f, 10, "torch.ground.on.1", "torch.ground.on.2");
		this.imageOff = new Sprite("torch.ground.off", 0.5f * 2f, 0.65625f * 2f, this);
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
			animationOn.updateAnimationCounter();
			animationOn.draw(canvas, getOrientation());
		} else {
			imageOff.draw(canvas);
		}
	}
}