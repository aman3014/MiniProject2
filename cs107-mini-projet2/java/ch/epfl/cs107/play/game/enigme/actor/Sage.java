/*
 *	Author:      Aman Bansal
 *	Date:        9 déc. 2018
 */

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

public class Sage extends AreaEntity implements Talker {

	private Animation animation;
	private Dialog dialog;
	private Dialog wisdom;
	
	public Sage(Area area, Orientation orientation, DiscreteCoordinates position, String wisdom) {
		super(area, orientation, position);
		animation = new Animation(this, Vector.ZERO, 1.5f, 1, "old.man.1");
		this.wisdom = new Dialog(wisdom, "dialog.1", area);
		this.dialog = new Dialog("Hold the S key to obtain the Sage's wisdom", "dialog.1", area);
		animation.updateAnimationCounter();
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
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas, getOrientation());
	}
	
	public Dialog getDialog() {
		return dialog;
	}
	
	public Dialog getWisdom() {
		return wisdom;
	}
	
	public Orientation getOrientation() {
		return super.getOrientation();
	}
	
	public void setOrientation(Orientation orientation) {
		super.setOrientation(orientation);
	}
}