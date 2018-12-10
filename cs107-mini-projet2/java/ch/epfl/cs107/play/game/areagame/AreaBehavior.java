package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.io.ResourcePath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{
	 /**
	  * Each game will have its own Cell extension.
	  */
	public abstract class Cell implements Interactable {
		private DiscreteCoordinates coordinates;
		private Set<Interactable> interactables;
		
		public Cell (int x, int y) {
			this.coordinates = new DiscreteCoordinates(x, y);
			this.interactables = new HashSet<>();
		}
		
		public List<DiscreteCoordinates> getCurrentCells() {
			return Arrays.asList(coordinates);
		}
		
		public boolean takeCellSpace() {
			for (Interactable interactable : interactables) {
				if (interactable.takeCellSpace()) {
					return true;
				}
			}
			return false;
		}

		abstract protected boolean canEnter(Interactable entity);
		
		abstract protected boolean canLeave(Interactable entity);
		
		private void enter(Interactable i) {
			interactables.add(i);
		}
		
		private void leave(Interactable i) {
			interactables.remove(i);
		}
		
		private void cellInteractionOf(Interactor interactor) {
			for	(Interactable interactable : interactables) {
				if (interactable.isCellInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
		
		private void viewInteractionOf(Interactor interactor) {
			for	(Interactable interactable : interactables) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}

		@Override
		public boolean isViewInteractable() {
			for (Interactable interactable : interactables) {
				if (interactable.isViewInteractable()) {
					return true;
				}
			}
			
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			for (Interactable interactable : interactables) {
				if (interactable.isCellInteractable()) {
					return true;
				}
			} 
			
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			for (Interactable interactable : interactables) {
				v.interactWith(interactable);
			}
		}
	}
	
	/// The behavior is an Image of size height x width
    private final Image behaviorMap;
    private final int width, height;
    
    // We will convert the image into an array of cells
    private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
        this.behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        this.width = behaviorMap.getWidth();
        this.height = behaviorMap.getHeight();
        this.cells = this.initializeCells(behaviorMap);
    }
    
    /**
	 * Function to initialize cells to avoid using getters (for behaviorMap)
	 * @param behaviorMap (Image): Can be used in overrides
	 * @return Returns a 2D table of Cells
	 */
    protected abstract Cell[][] initializeCells(Image behaviorMap);

    /**
     * Getter for the area behavior width
     * @return (int) : the width in number of columns
     */
    public int getWidth() {
    	return width;
    }
    
    /**
     * Getter for the area behavior height
     * @return (int) : the height in number of rows
     */
    public int getHeight() {
    	return height;
    }
    
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		if (!(cells[coord.x][coord.y].canLeave(entity))) {
    			return false;
    		}	
    	}
    	return true;
    }

    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		if (!isInGrid(coord)) {
    			return false;
    		}
    		if (!(cells[coord.x][coord.y].canEnter(entity))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		cells[coord.x][coord.y].leave(entity);
    	}
    }
    
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		cells[coord.x][coord.y].enter(entity);
    	}
    }
    
    public void cellInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
    		cells[coord.x][coord.y].cellInteractionOf(interactor);
    	}
    }
    
    public void viewInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
    		cells[coord.x][coord.y].viewInteractionOf(interactor);
    	}
    }
    
    protected Cell getCell(DiscreteCoordinates coordinates) {    	
    	return cells[coordinates.x][coordinates.y];
    }
    
    public boolean isInGrid(DiscreteCoordinates coordinates) {
    	if (coordinates.x < cells.length && coordinates.x >= 0 && coordinates.y < cells[coordinates.x].length && coordinates.y >= 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
}