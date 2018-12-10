package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Abstract class Collectable representing a collectable entity in an area
 * implements Talker
 * @author Julian Blackwell, Aman Bansal
 */
public abstract class Collectable extends AreaEntity implements Talker {

	private boolean collected;
	private Dialog dialog;
	
	/**
	 * Constructor for a Collectable
	 * @param area (Area) : the area to which the Collectable belongs
	 * @param position (DiscreteCoordinates) : the position of the Collectable in the area
	 */
	public Collectable(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		collected = false;
		this.dialog = null;
	}
	
	/**
	 * Constructor for a Collectable
	 * @param area (Area) : the area to which the Collectable belongs
	 * @param position (DiscreteCoordinates) : the position of the Collectable in the area
	 * @param dialog (Dialog) : the dialog associated with the Collectable
	 */
	public Collectable(Area area, DiscreteCoordinates position, Dialog dialog) {
		super(area, Orientation.DOWN, position);
		collected = false;
		this.dialog = dialog;
	}
	
	/**
	 * Constructor for a Collectable
	 * @param area (Area) : the area to which the Collectable belongs
	 * @param orientation (Orientation) : the orientation of the Collectable
	 * @param position (DiscreteCoordinates) : the position of the Collectable in the area
	 * @param dialog (Dialog) : the dialog associated with the Collectable
	 */
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position, Dialog dialog) {
		super(area, orientation, position);
		collected = false;
		this.dialog = dialog;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
	public void collect() {
		this.collected = true;
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return !collected;
	}

	@Override
	public boolean isViewInteractable() {
		return !collected;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	public Dialog getDialog() {
		return dialog;
	}
	
	public abstract void draw(Canvas canvas);
}