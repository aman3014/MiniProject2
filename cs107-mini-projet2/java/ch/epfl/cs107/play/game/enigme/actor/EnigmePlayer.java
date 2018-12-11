package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Egg;
import ch.epfl.cs107.play.game.enigme.actor.collectable.FastShoes;
import ch.epfl.cs107.play.game.enigme.actor.switcher.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

/**
 * Class EnigmePlayer representing an enigme player in the game
 * extends MoveableAreaEntity; implements Interactor
 * @author Aman Bansal, Julian Blackwell
 */
public class EnigmePlayer extends MovableAreaEntity implements Interactor {
	// Useful for interaction with doors
	private boolean isPassingDoor;
	private Door lastDoor;
	
	// Useful for interaction with Pressure Switches
	private DiscreteCoordinates lastMainCellCoordinates;

	// Useful for animation of the sprite
	private Animation animationGround;
	private Animation animationSky;
	final static int ANIMATION_DELAY = 4;
	final static int ANIMATION_DURATION = 8;
	private int animationDelay;
	private int animationDuration;
	
	// Follower as an attribute is useful for teleportation
	private Follower follower;
	
	// Keyboard and keys
	private Keyboard keyboard;
	private Button leftArrow;
	private Button rightArrow;
	private Button upArrow;
	private Button downArrow;
	private Button keyW;
	private Button keyF;
	
	// Follower
	private Orientation lastOrientation;
	
	// Handler
	private final EnigmePlayerHandler handler;

	// Useful for interaction with fastShoes
	private boolean hasFastShoes;
	private boolean fastShoesOn;
	
	//Useful for flying and interaction with an egg
	private boolean hasEgg;
	private boolean flying;
	
	/**
	 * Constructor for an enigme player
	 * @param area (Area) : the area to which the enigme player belongs
	 * @param orientation (Orientation) : the initial orientation of the enigme player
	 * @param position (DiscreteCoordinates) : the initial position of the enigme player in the area
	 */
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		
		isPassingDoor = false;
		lastDoor = null;
		
		lastMainCellCoordinates = getCurrentMainCellCoordinates();
		
		handler = new EnigmePlayerHandler();
		
		animationDelay = ANIMATION_DELAY;
		animationGround = new Animation(this, new Vector(0.25f, 0.32f).div(2f), 1.5f, animationDelay, "max.new.3");
		animationSky = new Animation(Float.MAX_VALUE, this, new Vector(0.25f, 0.32f).div(2f), 1.5f, ANIMATION_DELAY, "bird.2");
		animationDuration = ANIMATION_DURATION;
		hasFastShoes = false;
		fastShoesOn = false;
		
		hasEgg = false;
		flying = false;
		
		lastOrientation = orientation;
		
		this.keyboard = this.getOwnerArea().getKeyboard();
	}
	
	/**
	 * Constructor for an enigme player with default orientation down
	 * @param area (Area) : the area to which the enigme player belongs
	 * @param position (DiscreteCoordinates) : the initial position of the enigme player in the area
	 */
	public EnigmePlayer(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);

		isPassingDoor = false;
		lastDoor = null;
		
		lastMainCellCoordinates = getCurrentMainCellCoordinates();
		
		handler = new EnigmePlayerHandler();
		
		animationDelay = ANIMATION_DELAY;
		animationGround = new Animation(this, new Vector(0.25f, 0.32f).div(2f), 1.5f, animationDelay, "max.new.3");
		animationSky = new Animation(Float.MAX_VALUE, this, new Vector(0.25f, 0.32f).div(2f), 1.5f, ANIMATION_DELAY, "bird.2");
		animationDuration = ANIMATION_DURATION;
		hasFastShoes = false;
		fastShoesOn = false;
		
		hasEgg = false;
		flying = false;
		
		lastOrientation = Orientation.DOWN;
		
		this.keyboard = this.getOwnerArea().getKeyboard();
	}
	
	/**
	 * Method indicating if the enigme player is flying
	 * @return boolean : true if the player is flying
	 */
	public boolean isFlying() {
		return flying;
	}
	
	/**
	 * Method allowing to set the follower of the enigme player
	 * @param follower : the new follower of the enigme player
	 */
	public void setFollower(Follower follower) {
		this.follower = follower;
		follower.setAnimation(animationDelay, animationDuration);
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}
	
	/**
	 * Method indicating if the enigme player is interacting with a door
	 * @return (boolean) : true if the enigme player is interacting with a door
	 */
	public boolean getIsPassingDoor() {
		return isPassingDoor;
	}
	
	/**
	 * Method returning the last door the engime player has passed through
	 * @return (Door) : the last dooor the player passed through
	 */
	public Door getLastDoor() {
		return lastDoor;
	}
	
	@Override
	public void update(float deltaTime) {
		
		lastMainCellCoordinates = getCurrentMainCellCoordinates();
		
		leftArrow = keyboard.get(Keyboard.LEFT);
		rightArrow = keyboard.get(Keyboard.RIGHT);
		upArrow = keyboard.get(Keyboard.UP);
		downArrow = keyboard.get(Keyboard.DOWN);
		keyW = keyboard.get(Keyboard.W);
		keyF = keyboard.get(Keyboard.F);
		
		if (flying) {
			animationDuration = ANIMATION_DURATION;
			follower.setAnimation(animationDuration);
		}
		
		if (hasFastShoes && keyW.isReleased()) {
			fastShoesInteraction();
		}
		
		if (hasEgg && keyF.isReleased()) {
			flying = !flying;
		}
		
		if (isMoving()) {
			super.update(deltaTime);
			animationGround.updateAnimationCounter();
			animationSky.updateAnimationCounter();
			
			if (!lastMainCellCoordinates.equals(getCurrentMainCellCoordinates())) {
				lastOrientation = this.getOrientation();
			}
			
		} else {
			if (leftArrow.isDown()) {
				if (this.getOrientation().equals(Orientation.LEFT)) {
					if (move(animationDuration)) {
						super.update(deltaTime);
					}
				} else {
					this.setOrientation(Orientation.LEFT);
				}
			} else if (rightArrow.isDown()) {
				if (this.getOrientation().equals(Orientation.RIGHT)) {
					if (move(animationDuration)) {
						super.update(deltaTime);
					}
				} else {
					this.setOrientation(Orientation.RIGHT);
				}
			} else if (upArrow.isDown()) {
				if (this.getOrientation().equals(Orientation.UP)) {
					if (move(animationDuration)) {
						super.update(deltaTime);
					}
				} else {
					this.setOrientation(Orientation.UP);
				}
			} else if (downArrow.isDown()) {
				if (this.getOrientation().equals(Orientation.DOWN)) {
					if (move(animationDuration)) {
						super.update(deltaTime);
					}
				} else {
					this.setOrientation(Orientation.DOWN);
				}
			} else {
				animationGround.resetAnimationCounter();
				animationSky.resetAnimationCounter();
			}
		}
	}
	
	/**
	 * Private method used to implement the changes induced by the interaction of the enigme player with fast shoes
	 */
	private void fastShoesInteraction() {
		fastShoesOn = !fastShoesOn;
		if (fastShoesOn && !flying) {
			animationDelay = ANIMATION_DELAY / 2;
			animationDuration = ANIMATION_DURATION / 2;
		} else {
			animationDelay = ANIMATION_DELAY;
			animationDuration = ANIMATION_DURATION;
		}
		animationGround.changeAnimationDelay(animationDelay);
		follower.setAnimation(animationDelay, animationDuration);
	}
	
	/**
	 * Method allowing to set the last passed door of the player
	 * The attribute isPassingDoor is also updated
	 * @param door (Door) : the door the enigme player last passed through (the parameter should be null if the last door attributes needs to be reset
	 */
	public void setIsPassingDoor(Door door) {
		if (door == null) {
			isPassingDoor = false;
		} else {
			isPassingDoor = true;
		}
		lastDoor = door;
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
		if (flying) {
			animationSky.draw(canvas, getOrientation());
		} else {
			animationGround.draw(canvas, this.getOrientation());
		}
	}
	
	@Override
	public void setCurrentPosition(Vector v) {
		super.setCurrentPosition(v);
	}
	
	@Override
	public Area getOwnerArea() {
		return super.getOwnerArea();
	}
	
	/**
	 * Specific interaction handler for an EnigmePlayer
	 */
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {

		private boolean sageDialogShown = false;
		
		@Override
		public void interactWith(MovingRock movingRock) {
			movingRock.getDialog().draw(getOwnerArea().getWindow());
			
			Button keyL = keyboard.get(Keyboard.L);

			if (keyL.isReleased()) {
				movingRock.move(ANIMATION_DURATION, getOrientation());
			}
		}
		
		@Override
		public void interactWith(Sage sage) {
			if (!sageDialogShown) {
				sage.getDialog().draw(getOwnerArea().getWindow());
			}
			
			Button keyS = keyboard.get(Keyboard.S);
			if (keyS.isDown()) {
				
				sageDialogShown= true;
				
				// Make the sage face the player
				switch(getOrientation()) {
				case DOWN : 
					sage.setOrientation(Orientation.UP);
					break;
				case LEFT : 
					sage.setOrientation(Orientation.RIGHT);
					break;
				case UP : sage.setOrientation(Orientation.DOWN);
					break;
				case RIGHT : sage.setOrientation(Orientation.LEFT);
					break;
				}
				
				sage.getWisdom().draw(getOwnerArea().getWindow());
			}
		}
		
		@Override
		public void interactWith(Collectable collectable) {
			collectable.getDialog().draw(getOwnerArea().getWindow());
			Button keyL = keyboard.get(Keyboard.L);

			if (keyL.isReleased()) {
				collectable.collect();
				if (collectable instanceof FastShoes) {
					hasFastShoes = true;
				} else if (collectable instanceof Egg) {
					hasEgg = true;
				}
			}
		}
		
		@Override
		public void interactWith(Teleporter teleporter) {
			teleporter.getDialog().draw(getOwnerArea().getWindow());
			
			Button keyEnter = keyboard.get(Keyboard.ENTER);
			
			if (keyEnter.isReleased()) {
				
				setOrientation(teleporter.getArrivalOrientation());
				leaveAreaCells(getOwnerArea(), getCurrentCells());
				setCurrentPosition(teleporter.getArrivalCoordinatesVector());
				enterAreaCells(getOwnerArea(), getCurrentCells());
				
				// Follower does the same things
				follower.setOrientation(teleporter.getArrivalOrientation());
				follower.leaveAreaCells(getOwnerArea(), getCurrentCells());
				setLastOrientation(teleporter.getArrivalOrientation());
				follower.setCurrentPosition(teleporter.getArrivalCoordinatesVector().sub(getOrientation().toVector()));
				follower.enterAreaCells(getOwnerArea(), getCurrentCells());
			}
		}
		
		@Override
		public void interactWith(PressurePlate pressurePlate) {
			if(!flying) {
				pressurePlate.turnOn();
			}
		}
		
		@Override
		public void interactWith(Switcher switcher) {
			if (switcher instanceof PressureSwitch) {
				if (!(flying || lastMainCellCoordinates.equals(getCurrentMainCellCoordinates()))) {
					switcher.change();
				}
				return;
			}
			
			switcher.getDialog().draw(getOwnerArea().getWindow());
			
			Button keyL = keyboard.get(Keyboard.L);
			
			if (keyL.isReleased()) {
				switcher.change();
			}
		}
		
		@Override
		public void interactWith(Door door) {
			if (!flying) {
				setIsPassingDoor(door);
			}
		}
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}
	
	/**
	 * Private method allowing to set the last orientation of the player
	 * @param (Orientation) : last orientation
	 */
	private void setLastOrientation(Orientation orientation) {
		this.lastOrientation = orientation;
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		// Return the cell this actor is facing in a list
		List<DiscreteCoordinates> fieldOfViewCells = new LinkedList<DiscreteCoordinates>();
		DiscreteCoordinates cellInFront = getCurrentMainCellCoordinates().jump(this.getOrientation().toVector());
		if (getOwnerArea().isInGrid(cellInFront)) {
			fieldOfViewCells.add(cellInFront);
		}
		return fieldOfViewCells;
	}

	@Override
	public boolean wantsCellInteraction() {
			return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return true;
	}
	
	/**
	 * Method which makes the entity leave an area from a given position
	 * @param area (Area) : the area to be left by the entity
	 * @param position (DiscreteCoordinates) : the position in the area to be left
	 */
	private void leaveAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.leaveAreaCells(this, position);
	}
	
	/**
	 * Method which makes the entity enter an area at a given position
	 * @param area (Area) : the area to be entered by the entity
	 * @param position (DiscreteCoordinates) : the position in the area to be entered in
	 */
	private void enterAreaCells(Area area, List<DiscreteCoordinates> position) {
		area.enterAreaCells(this, position);
	}
	
	// These methods were required for the implementation of the follower
	/**
	 * Method returning the last orientation of the enigme player
	 * @return (Orientation) : the last orientation
	 */
	public Orientation getLastOrientation() {
		return lastOrientation;
	}

	@Override
	public Orientation getOrientation() {
		return super.getOrientation();
	}
	
	@Override
	public boolean isMoving() {
		return super.isMoving();
	}
}