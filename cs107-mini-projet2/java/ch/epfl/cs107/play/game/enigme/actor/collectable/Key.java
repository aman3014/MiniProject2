/*
 *	Author:		Julian Blackwell
 *	Date:		5 Dec 2018
 */

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

public class Key extends Collectable implements Logic {

	private Sprite image;
	
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, new Dialog("Press L to collect the key and unlock the door", "dialog.1", area));
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