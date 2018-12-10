package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

	/**
	 * Getter for the cells the interactable is currently occupying
	 * @return List<DiscreteCoordinates> : list of coordinates of the cells occupied by the interactable
	 */
	public abstract List<DiscreteCoordinates> getCurrentCells();
	
	/**
	 * Method listing the cells in the field of view of the interactor
	 * @return List<DiscreteCoordinates> : list of the coordinates of the cells in the field of view of the interactor
	 */
	public abstract List<DiscreteCoordinates> getFieldOfViewCells();
	
	/**
	 * Method describing if the interactor wants an interaction by contact
	 * @return boolean : true if interactor wants interaction by contact
	 */
	public abstract boolean wantsCellInteraction();
	
	/**
	 * Mehtod describing if the interactor wants an interaction by distance
	 * @return boolean : true if interactor wants interaction by distance
	 */
	public abstract boolean wantsViewInteraction();
	
	/**
	 * Method implementing the interaction between the interactor and another interactable
	 * @param other (Interactable) : interactable with which the interactor wishes to interact with
	 */
	public abstract void interactWith(Interactable other);
}