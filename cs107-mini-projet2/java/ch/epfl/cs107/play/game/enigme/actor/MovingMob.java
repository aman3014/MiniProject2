/*
 *	Author:		Julian Blackwell
 *	Date:		25 Dec 2018
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

public class MovingMob extends MovableAreaEntity implements Talker {

	private Animation animation;
	private Dialog dialog;
	
	public MovingMob(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.animation = new Animation(this, new Vector(0.15f, 0.15f), 1.5f, 4, "mob.1");
		this.dialog = new Dialog("GRR", "dialog.1", getOwnerArea());
	}

	@Override
	public void update(float deltaTime) {
		if(isMoving()) {
			super.update(deltaTime);
		} else {
			randomMovement(deltaTime);
		}
	}
	
	public void randomMovement (float deltaTime) {
		int number = (int) (24233214 / Math.random()) ;
		number = number % 140;
		
		if (number > 10 && number < 15) {
			number = number % 4;
			
			switch (number) {
				case 0:
					setOrientation(Orientation.DOWN);
					break;
				case 1:
					setOrientation(Orientation.LEFT);
					break;
				case 2:
					setOrientation(Orientation.UP);
					break;
				case 3:
					setOrientation(Orientation.RIGHT);
					break;
				default:
					break;
			}
		} else if (number < 10) {
			if (move(EnigmePlayer.ANIMATION_DURATION * 2)) {
				super.update(deltaTime);
			}
		}
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Arrays.asList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		dialog.draw(getOwnerArea().getWindow());
	}

	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas, getOrientation());
		if(isMoving()) {
			animation.updateAnimationCounter();
		} else {
			animation.resetAnimationCounter();
		}
	}

	@Override
	public Dialog getDialog() {
		return dialog;
	}
}
