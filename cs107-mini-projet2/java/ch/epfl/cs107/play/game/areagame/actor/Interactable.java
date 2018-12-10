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
    
	public List<DiscreteCoordinates> getCurrentCells();
	
	public boolean takeCellSpace();
	
	public boolean isViewInteractable();
	
	public boolean isCellInteractable();
	
	public void acceptInteraction(AreaInteractionVisitor v);
}
