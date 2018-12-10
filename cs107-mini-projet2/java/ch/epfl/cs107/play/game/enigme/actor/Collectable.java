/*
 *	Author:      Aman Bansal
 *	Date:        4 déc. 2018
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
import ch.epfl.cs107.play.window.Canvas;

public abstract class Collectable extends AreaEntity {

	private boolean collected;
	private Dialog dialog;
	
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		collected = false;
		dialog = null;
	}
	
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position, Dialog dialog) {
		super(area, orientation, position);
		collected = false;
		this.dialog = dialog;
	}
	
	public boolean isCollected() {
		return collected;
	}
	
	protected void collect() {
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
		return collected ? false : true;
	}

	@Override
	public boolean isViewInteractable() {
		return collected ? false : true;
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