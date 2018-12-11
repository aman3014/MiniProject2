package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MovingRock extends MovableAreaEntity implements Talker {

	private Sprite image;
	private Dialog dialog;
	
	/**
	 * Constructor for a Moving Rock
	 * @param signal (Logic) : the logic signal on which the moving rock depends
	 * @param area (Area) : the area to which the signal rock belongs
	 * @param position (DiscreteCoordaintes) : the position of the signal rock in the area
	 */
	public MovingRock(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		this.image = new Sprite("rock.2", 1, 1.f, this);
		this.dialog = new Dialog("This rocks looks like it can move... (Press L)", "dialog.1", getOwnerArea());
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
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
		return false;
	}
		
	@Override
	public void draw(Canvas canvas) {
		image.draw(canvas);
	}
	
	public boolean move(int framesForMove, Orientation orientation) {
		this.setOrientation(orientation);
		return super.move(framesForMove);
	}

	@Override
	public Dialog getDialog() {
		return dialog;
	}
}