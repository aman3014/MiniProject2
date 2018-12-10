package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    
	/**
	 * Getter for the cells the interactable is currently occupying
	 * @return List<DiscreteCoordinates> : list of coordinates of the cells occupied by the interactable
	 */
	public List<DiscreteCoordinates> getCurrentCells();
	
	/**
	 * Method describing if the interactable takes the cell space it occupies
	 * @return boolean : true if interactable takes cell space, false otherwise
	 */
	public boolean takeCellSpace();
	
	/**
	 * Method describing if the interactable accepts interactions by distance
	 * @return boolean : true if interactable accepts interactions by distance, false otherwise
	 */
	public boolean isViewInteractable();
	
	/**
	 * Method describing if the interactable accepts interactions by contact
	 * @return boolean : true if interactable accepts interactions by contact, false otherwise
	 */
	public boolean isCellInteractable();
	
	/**
	 * Method accepting and putting in action an interaction between v and the interactable
	 * @param v (AreaInteractionVisitor) : entity which handles the interactions of the interactor who wishes to interact with interactable
	 */
	public void acceptInteraction(AreaInteractionVisitor v);
}
