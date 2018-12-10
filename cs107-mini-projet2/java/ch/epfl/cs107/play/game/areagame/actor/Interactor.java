package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

	public abstract List<DiscreteCoordinates> getCurrentCells();
	
	public abstract List<DiscreteCoordinates> getFieldOfViewCells();
	
	public abstract boolean wantsCellInteraction();
	
	public abstract boolean wantsViewInteraction();
	
	public abstract void interactWith(Interactable other);
}