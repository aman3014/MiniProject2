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
 * Class FastShoes represents shoes which gives the ability to the interactor who collects them to move faster; extends the class Collectable
 * @author Aman Bansal, Julian Blackwell
 */
public class FastShoes extends Collectable {

	private Sprite image;

	/**
	 * Constructor of an instance of FastShoes
	 * @param area (Area) : the area to which the FastShoes belong
	 * @param position (DiscreteCoordinates) : 
	 */
	public FastShoes(Area area, DiscreteCoordinates position) {
		super(area, position, new Dialog("Press L to collect the running shoes", "dialog.1", area));
		image = new Sprite("shoes.2", 0.75f, 0.75f, this, null, new Vector(0.125f, 0.125f));
	}

	@Override
	public void draw(Canvas canvas) {
		if (!isCollected()) {
			image.draw(canvas);
		}
	}
	
	@Override
	public void collect() {
		super.collect();
	}
}