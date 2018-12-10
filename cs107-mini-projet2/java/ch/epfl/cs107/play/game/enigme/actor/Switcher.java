/*
 *	Author:		Julian Blackwell
 *	Date:		5 Dec 2018
 */

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

public abstract class Switcher extends AreaEntity implements Logic, Talker {

	private boolean isOn;
	private Dialog dialog;
	
	public Switcher(Area area, Orientation orientation, DiscreteCoordinates position, boolean isOn) {
		super(area, orientation, position);
		this.isOn = isOn;
		dialog = null;
	}

	public Switcher(Area area, Orientation orientation, DiscreteCoordinates position, boolean isOn, Dialog dialog) {
		super(area, orientation, position);
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
	
	public void change() {
		this.isOn = !this.isOn;
	}
	
	public Dialog getDialog() {
		return dialog;
	}
}