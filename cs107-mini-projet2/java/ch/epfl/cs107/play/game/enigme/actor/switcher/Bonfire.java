package ch.epfl.cs107.play.game.enigme.actor.switcher;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Animation;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Bonfire representing a bonfire
 * extends Switcher
 * @author Julian Blackwell, Aman Bansal
 */
public class Bonfire extends Switcher {
	
	private Animation animationOn;
	private Sprite imageOff;
	
	/**
	 * Constructor of a bonfire
	 * @param area (Area) : the area to which the bonfire belongs
	 * @param position (DiscreteCoordinates) : the position of the bonfire in the area
	 * @param isOn (boolean) : the initial state of the bonfire
	 */
	public Bonfire(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, position, isOn);
		this.imageOff = new Sprite("fire.off", 0.5f * 2f, 0.65625f * 2f, this);
		this.animationOn = new Animation(this, Vector.ZERO, 2f, 10, "fire.on.1", "fire.on.2");
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
