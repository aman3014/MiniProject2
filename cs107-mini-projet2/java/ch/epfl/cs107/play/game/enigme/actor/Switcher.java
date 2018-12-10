package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Abstract class Switcher representing an entity whose state can be change by an interactor and who can act as a logic signal
 * extends AreaEntity; implements Logic and Talker
 * @author Aman Bansal, Julian Blackwell
 */
public abstract class Switcher extends AreaEntity implements Logic, Talker {

	private boolean isOn;
	private Dialog dialog;
	
	/**
	 * Constructor of a switcher without a dialog 
	 * @param area (Area) : the area to which the switcher belongs
	 * @param position (DiscreteCoordinates) : the position of the switcher in its area
	 * @param isOn (boolean) : the initial state of the switcher
	 */
	public Switcher(Area area, DiscreteCoordinates position, boolean isOn) {
		super(area, Orientation.DOWN, position);
		this.isOn = isOn;
		dialog = null;
	}

	/**
	 * Constructor of a switcher without a dialog 
	 * @param area (Area) : the area to which the switcher belongs
	 * @param position (DiscreteCoordinates) : the position of the switcher in its area
	 * @param isOn (boolean) : the initial state of the switcher
	 * @param dialog (Dialog) : the dialog which will appear when an interactor interacts with this switcher
	 */
	public Switcher(Area area, DiscreteCoordinates position, boolean isOn, Dialog dialog) {
		super(area, Orientation.DOWN, position);
		this.isOn = isOn;
		this.dialog = dialog;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(new DiscreteCoordinates((int)getPosition().getX(), (int)getPosition().getY()));
	}

	@Override
	abstract public boolean takeCellSpace();

	@Override
	abstract public boolean isViewInteractable();

	@Override
	abstract public boolean isCellInteractable();

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	abstract public void draw(Canvas canvas);

	@Override
	public boolean isOn() {
		return isOn;
	}
	
	/**
	 * Method which switches the state of the switcher
	 */
	public void change() {
		this.isOn = !this.isOn;
	}
	
	@Override
	public Dialog getDialog() {
		return dialog;
	}
}