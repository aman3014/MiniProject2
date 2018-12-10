package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Animation respresents the animation of an entity which initializes it
 * It can implement the animations of both moveable area entities and non-moveable area entities
 * @author Aman Bansal, Julian Blackwell
 */
public class Animation {

	// Attributes specific to an animation used in the initialization of the sprites and their drawings
	private int animationCounter;
	private int animatinoCounterCounter;
	private ArrayList<String> spriteNames;
	private ArrayList<Sprite> sprites;
	private Vector anchor;
	private float sizeFactor;
	private Positionable parent;
	private int delay;
	private float depthCorrection;
			
	/**
	 * Constructor of an Animation
	 * @param parent (Positionable) : the entity to which the animation is attached
	 * @param anchor (Vector) : used to adjust the position of the animation drawings in a cell
	 * @param sizeFactor (float) : used to adjust the size of the animation drawings
	 * @param delay (int) : used to adjust the frequency of changing the anmiation drawings
	 * @param spriteNames (String) : an ellipse of Strings used to initialize the sprites of an animation
	 */
	public Animation(Positionable parent, Vector anchor, float sizeFactor, int delay, String...spriteNames) {
		animationCounter = 0;
		animatinoCounterCounter = 0;
		
		this.spriteNames = new ArrayList<String>();
		this.sprites = new ArrayList<Sprite>();
		
		if (spriteNames != null) {
			for (String spriteName : spriteNames) {
				this.spriteNames.add(spriteName);
			}
		}
		
		this.anchor = anchor;
		this.sizeFactor = sizeFactor;
		this.parent = parent;
		this.delay = delay;
		depthCorrection = 0;
		
		initializeSprites();
	}
	
	/**
	 * Constructor for Animation with depth correction
	 * @param depthCorrection (float) : used to adjust the depth of the animation drawings in the window
	 * @param parent (Positionable) : the entity to which the animation is attached
	 * @param anchor (Vector) : used to adjust the position of the animation drawings in a cell
	 * @param sizeFactor (float) : used to adjust the size of the animation drawings
	 * @param delay (int) : used to adjust the frequency of changing the anmiation drawings
	 * @param spriteNames (String) : an ellipse of Strings used to initialize the sprites of an animation
	 */
	public Animation(float depthCorrection, Positionable parent, Vector anchor, float sizeFactor, int delay, String...spriteNames) {
		animationCounter = 0;
		animatinoCounterCounter = 0;
		
		this.spriteNames = new ArrayList<String>();
		this.sprites = new ArrayList<Sprite>();
		
		if (spriteNames != null) {
			for (String spriteName : spriteNames) {
				this.spriteNames.add(spriteName);
			}
		}
		
		this.anchor = anchor;
		this.sizeFactor = sizeFactor;
		this.parent = parent;
		this.delay = delay;
		
		this.depthCorrection = depthCorrection;
		initializeSprites();
	}
	
	/**
	 * Method used to update the animationCounter and the animationCounterCounter of an animation
	 * The animationCounterCounter is increased everytime the method is called but the the animationCounter is only increased at each delay'th call of the method
	 */
	public void updateAnimationCounter() {
		++animatinoCounterCounter;
		if (animatinoCounterCounter % delay == 0) {
			animatinoCounterCounter = 0;
			++this.animationCounter;
		}
	}
	
	/**
	 * Method used to reset the animationCounter to zero
	 */
	public void resetAnimationCounter() {
		this.animationCounter = 0;
	}
	
	/**
	 * Method used to change the delay of the animation
	 * @param delay (int) : the new delay
	 */
	public void changeAnimationDelay(int delay) {
		this.delay = delay;
	}
	
	/**
	 * Method used to initialize the sprites of the animation
	 * If the number of sprite names given in the animation constructor is 1 then the corresponding image is cut up and 16 sprites are created for the animation
	 * If the number of sprite names given in the animation constructor is more than 1 then one sprite is created for each corresponding image for the animation
	 */
	private void initializeSprites() {
		if (spriteNames.size() == 1) {
			for (int i = 0; i < 16; ++i) {
				sprites.add(new Sprite(spriteNames.get(0), 0.50f * sizeFactor, 0.65625f * sizeFactor, parent, new RegionOfInterest((i / 4) * 16, (i % 4) * 21, 16, 21), anchor, 1f, depthCorrection));
			}
		} else {
			for (int i = 0; i < spriteNames.size(); ++i) {
				sprites.add(new Sprite(spriteNames.get(i), 0.50f * sizeFactor, 0.65625f * sizeFactor, parent, null, anchor, 1f, depthCorrection));
			}
		}
	}
	
	/**
	 * Method used to draw the sprites of the animation
	 * @param canvas (Canvas) : on which to draw the sprites
	 * @param orientation (Orientation) : to decide which corresponding sprite should be drawn
	 */
	public void draw(Canvas canvas, Orientation orientation) {
		if (spriteNames.size() == 1) {
			switch (orientation) {
				case DOWN:
					sprites.get(0 + (animationCounter % 4)).draw(canvas);
					break;
				case LEFT:
					sprites.get(4 + (animationCounter % 4)).draw(canvas);
					break;
				case UP:
					sprites.get(8 + (animationCounter % 4)).draw(canvas);
					break;
				case RIGHT:
					sprites.get(12 + (animationCounter % 4)).draw(canvas);
					break;
				default:
					break;
			}
		} else {
			sprites.get(animationCounter % sprites.size()).draw(canvas);
		}
		if (animationCounter > 10000) {
			animationCounter = 0;
		}
	}
}