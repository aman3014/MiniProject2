package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Animation {
	
	private int animationCounter;
	private int counter;
	private ArrayList<String> spriteNames;
	private ArrayList<Sprite> sprites;
	private Vector anchor;
	private float sizeFactor;
	private Positionable parent;
	private int delay;
	
	private float depthCorrection;
			
	public Animation(Positionable parent, Vector anchor, float sizeFactor, int delay, String...spriteNames) {
		animationCounter = 0;
		counter = 0;
		
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
	 * @param depthCorrection
	 * @param parent
	 * @param anchor
	 * @param sizeFactor
	 * @param delay
	 * @param spriteNames
	 */
	public Animation(float depthCorrection, Positionable parent, Vector anchor, float sizeFactor, int delay, String...spriteNames) {
		animationCounter = 0;
		counter = 0;
		
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
	
	public void updateAnimationCounter() {
		++counter;
		if (counter % delay == 0) {
			counter = 0;
			++this.animationCounter;
		}
	}
	
	public void resetAnimationCounter() {
		this.animationCounter = 0;
	}
	
	public void changeAnimationDelay(int delay) {
		this.delay = delay;
	}
	
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