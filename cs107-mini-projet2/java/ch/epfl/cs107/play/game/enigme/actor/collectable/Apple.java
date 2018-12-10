package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Apple represents an apple and extends the class Collectable
 * @author Aman Bansal, Julian Blackwell
 */
public class Apple extends Collectable {

	private Sprite image;
	
	/**
	 * Constructor for an apple
	 * @param area (Area) : area to which the apple belongs
	 * @param position (DiscreteCoordinates) : the position of the apple in the area
	 */
	public Apple(Area area, DiscreteCoordinates position) {
		super(area, position, new Dialog("Press L to eat the apple.", "dialog.1", area));
		image = new Sprite("apple.1", 0.75f, 0.75f, this, null, new Vector(0.125f, 0.125f));
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (!isCollected()) {
			image.draw(canvas);
		}
	}
}