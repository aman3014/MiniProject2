package ch.epfl.cs107.play.game.demo1.actor;

import java.awt.Color;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class representing a MovingRock extending GraphicsEntity
 * @author Aman Bansal, Julian Blackwell
 */
public class MovingRock extends GraphicsEntity {
	private final TextGraphics text;

	/**
	 * Constructor for a MovingRock - initializing it at the position and with the text given in the parameters
	 * @param position (Vector) : initial position of the moving rock
	 * @param text (String) : text attached to the moving rock
	 */
	public MovingRock(Vector position, String text) {
		super(position, new	ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f, 0.1f, null, Vector.ZERO , 1.0f, -Float.MAX_VALUE));
		this.text = new TextGraphics(text, 0.04f, Color.GREEN);
		this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f, 0.1f));
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		text.draw(canvas);
	}
	
	@Override
	public void update(float deltaTime) {
		setCurrentPosition(getPosition().sub(0.5f * deltaTime, 0.5f * deltaTime));
	}
}