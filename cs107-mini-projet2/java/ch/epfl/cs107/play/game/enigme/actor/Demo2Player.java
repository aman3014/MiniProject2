package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Class Demo2Player, extends MoveableAreaEntity
 * @author Aman Bansal, Julian Blackwell
 */
public class Demo2Player extends MovableAreaEntity {

	private boolean passingDoor;
	private Sprite image;

	// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;
	
	/**
	 * Constructor for a Demo2Player
	 * @param area (Area) : the initial area to which the player belongs
	 * @param orientation (Orientation) : the initial orientation of the player
	 * @param position (DiscreteCoordinates) : the initial position of the player in the area
	 */
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		image = new Sprite("ghost.1", 1, 1.f, this);
		passingDoor = ((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates());
	}
	
	/**
	 * Constructor for a Demo2Player (the default orientation is set to DOWN)
	 * @param area (Area) : the initial area to which the player belongs
	 * @param position (DiscreteCoordinates) : the initial position of the player in the area
	 */
	public Demo2Player(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		image = new Sprite("ghost.1", 1, 1.f, this);
		passingDoor = ((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates());
	}
	
	/**
	 * Method indicating if the Demo2Player is passing a Door
	 * @return boolean : true if the Demo2Player is passing a Door
	 */
	public boolean isPassingDoor() {
		return passingDoor;
	}
	
	@Override
	public void update(float deltaTime) {
		if (((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates())) {
			passingDoor = true;
		} else {
			passingDoor = false;
		}
		
		Keyboard keyboard = getOwnerArea().getKeyboard();
		
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		if (isMoving()) {
			super.update(deltaTime);
		} else if (leftArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.LEFT)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.LEFT);
			}
		} else if (rightArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.RIGHT)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.RIGHT);
			}
		} else if (upArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.UP)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.UP);
			}
		} else if (downArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.DOWN)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.DOWN);
			}
		}
	}

	/**
	 * Method used to set if the Demo2Player is passing a door
	 * @param bool (boolean) : the value to which passingDoor should be set to
	 */
	public void setPassingDoor(boolean bool) {
		this.passingDoor = bool;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
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
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		image.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// The interactions were not implemented at this stage of the project
		// Empty method inserted to avoid compilation errors
	}
}