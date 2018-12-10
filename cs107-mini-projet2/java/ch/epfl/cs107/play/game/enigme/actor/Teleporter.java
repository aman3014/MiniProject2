package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Teleporter representing an entity which allows an interactor to teleport to a new cell
 * extends AreaEntity; implements Talker
 * @author Julian Blackwell, Aman Bansal
 */
public class Teleporter extends AreaEntity implements Talker {

	private Animation animation;
	
	private Dialog dialog;
	
	private DiscreteCoordinates arrivalCoordinates;
	private Orientation arrivalOrientation;
	
	/**
	 * Constructor for a teleporter
	 * @param area (Area) : the area to which the teleporter belongs
	 * @param position (DiscreteCoordinates) : the position of the teleporter in the area
	 * @param arrivalCoordinates (DiscreteCoordinates) : the coordinates of the cell the teleporter sends an interactor to
	 * @param arrivalOrientation (Orientation) : the orientation of an interactor when it arrives to his destination
	 */
	public Teleporter(Area area, DiscreteCoordinates position, DiscreteCoordinates arrivalCoordinates, Orientation arrivalOrientation) {
		super(area, Orientation.DOWN, position);
		
		this.arrivalCoordinates = arrivalCoordinates;
		this.arrivalOrientation = arrivalOrientation;
		
		animation = new Animation(this, new Vector(0.25f, 0.16f), 1f, 5, "ingot.1", "ingot.2", "ingot.3", "ingot.4", "ingot.5");
		
		dialog = new Dialog("Press Enter to teleport to a mysterious place", "dialog.1", area);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Arrays.asList(this.getCurrentMainCellCoordinates());
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
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		animation.updateAnimationCounter();
		animation.draw(canvas, getOrientation());
	}
	
	/**
	 * Method returning the arrival orientation of an interactor when it interacts with this teleporter
	 * @return (Orientation) : the arrival orientation
	 */
	public Orientation getArrivalOrientation() {
		return arrivalOrientation;
	}
	
	/**
	 * Method returning the destination position of the teleporter
	 * @return (Vector) : the arrivalCoordinatesVector
	 */
	public Vector getArrivalCoordinatesVector() {
		return arrivalCoordinates.toVector();
	}
	
	@Override
	public Dialog getDialog() {
		return dialog;
	}
}