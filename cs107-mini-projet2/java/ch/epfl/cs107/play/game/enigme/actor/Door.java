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
import ch.epfl.cs107.play.math.Vector;
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
	 * @param destCoord (DiscreteCoordaintes) : The coordinates of an interactor in the destination area when it interacts through the door
	 * @param position
	 * @param occupiedCells
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
	
	public String getDestAreaName() {
		return destAreaName;
	}
	
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