package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Follower represents a character which follows an enigme player in the game Enigme
 * extends MoveableAreaEntity
 * @author Aman Bansal, Julian Blackwell
 */
public class Follower extends MovableAreaEntity {

	private EnigmePlayer parent;
	private Animation animationGround;
	private Animation animationSky;
	
	private int animationDuration;
		
	/**
	 * Constructor for a follower
	 * @param parent : the parent enigme player of the follower
	 */
	public Follower(EnigmePlayer parent) {
		super(parent.getOwnerArea(), parent.getOrientation(), new DiscreteCoordinates((int) parent.getPosition().sub(parent.getOrientation().toVector()).x,
																		(int) parent.getPosition().sub(parent.getOrientation().toVector()).y));
		this.parent = parent;
		
		this.animationGround = new Animation(this, new Vector(0.25f, 0.32f), 1f, EnigmePlayer.ANIMATION_DELAY, "max.ghost");
		this.animationSky = new Animation(Float.MAX_VALUE, this, new Vector(0.25f, 0.32f), 1f, EnigmePlayer.ANIMATION_DELAY, "bird.1");
	}
	
	@Override
	public void update(float deltaTime) {
		if(parent.isMoving()) {
			forceMove(animationDuration);
			super.update(deltaTime);
			animationGround.updateAnimationCounter();	
			animationSky.updateAnimationCounter();
		} else {
			animationGround.resetAnimationCounter();
			animationSky.resetAnimationCounter();
		}
		
		this.setOrientation(parent.getLastOrientation());
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Arrays.asList(getCurrentMainCellCoordinates());
	}
 
	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// Not interactable
	}

	@Override
	public void draw(Canvas canvas) {
		if (parent.isFlying()) {
			animationSky.draw(canvas, getOrientation());
		} else {
			animationGround.draw(canvas, getOrientation());
		}
	}
	
	@Override
	public void setOrientation(Orientation orientation) {
		super.setOrientation(orientation);
	}
	
	@Override
	public void setCurrentPosition(Vector v) {
		super.setCurrentPosition(v);
	}
	
	/**
	 * Method which makes the entity leave an area from a given position
	 * @param area (Area) : the area to be left by the entity
	 * @param position (DiscreteCoordinates) : the position in the area to be left
	 */
	public void leaveAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.leaveAreaCells(this, position);
	}
	
	/**
	 * Method which makes the entity enter an area at a given position
	 * @param area (Area) : the area to be entered by the entity
	 * @param position (DiscreteCoordinates) : the position in the area to be entered in
	 */
	public void enterAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.enterAreaCells(this, position);
	}
	
	/**
	 * Method used to change the animation variables of the follower
	 * @param animationDelay (int) : the delay in his animation speed
	 * @param animationDuration (int) : the number of frames he takes for one discrete motion
	 */
	public void setAnimation(int animationDelay, int animationDuration) {
		animationGround.changeAnimationDelay(animationDelay);
		this.animationDuration = animationDuration;
	}
	
	/**
	 * Method used to change the animation duration of the follower
	 * @param animationDuration (int) : the number of frames he takes for one discrete motion
	 */
	public void setAnimation(int animationDuration) {
		this.animationDuration = animationDuration;
	}
}