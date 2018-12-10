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
 * Class Egg represents an egg which gives the flying ability to an interactor who collects it; extends the class Collectable
 * @author Aman Bansal, Julian Blackwell
 */
public class Egg extends Collectable {

	private Sprite image;

	/**
	 * Constructor of an Egg
	 * @param area (Area) : the area to which the egg belongs
	 * @param position (DiscreteCoordinates) : the position of the egg in the area
	 */
	public Egg(Area area, DiscreteCoordinates position) {
		super(area, position, new Dialog("Press L to collect the egg", "dialog.1", area));
		this.image = new Sprite("egg.1", 0.75f, 0.75f, this, null, new Vector(0.125f, 0.125f));
	}

	@Override
	public void draw(Canvas canvas) {
		if(!isCollected()) {
			image.draw(canvas);
		}	
	}
}
