package ch.epfl.cs107.play.game.enigme.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class door representing a door; extends AreaEntity
 * @author Julian Blackwell, Aman Bansal
 */
public class Door extends AreaEntity {
	
	private String destAreaName;
	private DiscreteCoordinates destCoord;
	private List<DiscreteCoordinates> occupiedCells;
	
	private final Sprite open;
	
	/**
	 * Constructor for a door
	 * @param area (Area) : the area to which the door belongs
	 * @param destAreaName (String) : the name of the destination area of the door
	 * @param destCoord (DiscreteCoordinates) : the coordinates of an interactor in the destination area when it interacts through the door
	 * @param position (DiscreteCoordinates) : the position of the door in its area
	 * @param occupiedCells (DiscreteCoordinates...) : the cells occupied by the door
	 */
	public Door(Area area, String destAreaName, DiscreteCoordinates destCoord, DiscreteCoordinates position, DiscreteCoordinates...occupiedCells) {
		super(area, Orientation.DOWN, position);
		this.destAreaName = destAreaName;
		this.destCoord = new DiscreteCoordinates(destCoord.x, destCoord.y);
		this.occupiedCells = new LinkedList<DiscreteCoordinates>();
		if (occupiedCells != null) {
			for (DiscreteCoordinates coordinates : occupiedCells) {
				this.occupiedCells.add(coordinates);
			}
		}
		open = new Sprite("door.open.1", 1, 1.f, this);
	}
	
	/**
	 * Method returning the name of the destination area
	 * @return String : the name of the destination area
	 */
	public String getDestAreaName() {
		return destAreaName;
	}
	
	/**
	 * Method returning the coordinates of the cell to which the door sends an interactor
	 * @return (DiscreteCoordinates) : arrival coordinates
	 */
	public DiscreteCoordinates getDestCoordinates() {
		return destCoord;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return occupiedCells;
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
	public void draw(Canvas canvas) {		
		open.draw(canvas);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
}