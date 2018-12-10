/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.actor.collectable;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable {

	private Sprite image;
	
	public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, new Dialog("Press L to eat the apple.", "dialog.1", area));
		image = new Sprite("apple.1", 0.75f, 0.75f, this, null, new Vector(0.125f, 0.125f));
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (!isCollected()) {
			image.draw(canvas);
		}
	}
}