package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Key representing a key which acts as a signal to open doors(or other things)
 * extends Collectable; implements Logic
 * @author Julian Blackwell, Aman Bansal
 */
public class Key extends Collectable implements Logic {

	private Sprite image;

	/**
	 * Constructor for a Key
	 * @param area (Area) : the area to which the Key belongs
	 * @param position (DiscreteCoordinates) : the position of the Key in the area
	 */
	public Key(Area area, DiscreteCoordinates position) {
		super(area, position, new Dialog("Press L to collect the key and unlock the door", "dialog.1", area));
		this.image = new Sprite("key.1", 0.8f, 0.8f, this, null, new Vector(0.1f, 0));
	}

	@Override
	public void draw(Canvas canvas) {
		if (!isCollected()) {
			image.draw(canvas);
		}
	}

	@Override
	public boolean isOn() {
		return isCollected();
	}
}