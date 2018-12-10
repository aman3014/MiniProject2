/*
 *	Author:      Aman Bansal
 *	Date:        7 déc. 2018
 */

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

public class Follower extends MovableAreaEntity {

	private EnigmePlayer parent;
	private Animation animationGround;
	private Animation animationSky;
	
	private int animationDuration;
		
	public Follower(EnigmePlayer parent) {
		super(parent.getOwnerArea(), parent.getOrientation(), new DiscreteCoordinates((int) parent.getPosition().sub(parent.getOrientation().toVector()).x,
																		(int) parent.getPosition().sub(parent.getOrientation().toVector()).y));
		this.parent = parent;
		
		this.animationGround = new Animation(this, new Vector(0.25f, 0.32f), 1f, EnigmePlayer.ANIMATION_DELAY, "max.ghost");
		this.animationSky = new Animation(Float.MAX_VALUE, this, new Vector(0.25f, 0.32f), 1f, EnigmePlayer.ANIMATION_DELAY, "bird.1");
	}
	
	public void update(float deltaTime) {
		if (parent.isMoving()) {
			if (!move(animationDuration)) {
				forceMove(animationDuration);
			}
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
	
	public void	enterArea(Area area , DiscreteCoordinates position) {
		area.registerActor(this);
		area.enterAreaCells(this, Arrays.asList(position));
		setCurrentPosition(position.toVector());
		this.resetMotion();
		this.setOwnerArea(area);
	}

	public void leaveArea(Area area, DiscreteCoordinates position) {
		area.unregisterActor(this);
		area.leaveAreaCells(this, Arrays.asList(position));
		this.resetMotion();
		this.setOwnerArea(null);
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
	
	public void leaveAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.leaveAreaCells(this, position);
	}
	
	public void enterAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.enterAreaCells(this, position);
	}
	
	public void setAnimation(int animationDelay, int animationDuration) {
		animationGround.changeAnimationDelay(animationDelay);
		this.animationDuration = animationDuration;
	}
}