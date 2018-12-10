package ch.epfl.cs107.play.game.areagame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
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
		
		/**
		 * Cell Constructor which initializes the cell with 
		 * its coordinates and an empty list of interactables it contains
		 * @param x
		 * @param y
		 */
		public Cell (int x, int y) {
			this.coordinates = new DiscreteCoordinates(x, y);
			this.interactables = new HashSet<>();
		}
		
		@Override
		public List<DiscreteCoordinates> getCurrentCells() {
			return Arrays.asList(coordinates);
		}
		
		@Override
		public boolean takeCellSpace() {
			for (Interactable interactable : interactables) {
				if (interactable.takeCellSpace()) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Method determining if an entity can enter this cell
		 * Note : Needs to be redefined
		 * @param entity (Interactable) : interactable which wants to enter this cell
		 * @return boolean : true if the entity can enter this cell
		 */
		abstract protected boolean canEnter(Interactable entity);
		
		/**
		 * Method determining if an entity can leave this cell
		 * Note : Needs to be redefined
		 * @param entity (Interactable) : interactable which wants to enter this cell
		 * @return boolean : true if the entity can leave this cell
		 */
		abstract protected boolean canLeave(Interactable entity);
		
		/**
		 * Method used for the addition of an interactable to this cell
		 * @param interactable (Interactable) : interactable which needs to be added to this cell
		 */
		private void enter(Interactable interactable) {
			interactables.add(interactable);
		}
		
		/**
		 * Method used for the removal of an interactable from this cell
		 * @param interactable (Interactable) : interactable which needs to be removed from this cell
		 */
		private void leave(Interactable interactable) {
			interactables.remove(interactable);
		}
		
		/**
		 * Method which calls the interaction function of the interactor for each of the cell-interactable interactables in this cell
		 * @param interactor
		 */
		private void cellInteractionOf(Interactor interactor) {
			for	(Interactable interactable : interactables) {
				if (interactable.isCellInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
		
		/**
		 * Method which calls the interaction function of the interactor for each of the view-interactable interactables in this cell
		 * @param interactor
		 */
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
	 * Function to initialize a 2D table of cells
	 * Note : Needs to be redefined
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
    
    /**
     * Method indicating if an entity can leave the cell at the coordinates (in the singleton list)
     * @param entity (Interactable) : the entity which wants to leave the cell at the coordinates
     * @param coordinates (List<DiscreteCoordinates>) : singleton list containing the coordinates of the cell which needs to be left
     * @return boolean : true if the entity can leave the cell specified by the coordinates in the list
     */
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		if (!(cells[coord.x][coord.y].canLeave(entity))) {
    			return false;
    		}	
    	}
    	return true;
    }

    /**
     * Method indicating if an entity can enter the cell at the coordinates (in the singleton list)
     * @param entity (Interactable) : the entity which wants to enter the cell at the coordinates
     * @param coordinates (List<DiscreteCoordinates>) : singleton list containing the coordinates of the cell which needs to be entered
     * @return boolean : true if the entity can enter the cell specified by the coordinates in the list
     */
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
    
    /**
     * Method making the entity leave the cell at the coordinates in the list
     * @param entity (Interactable) : the entity which wants to leave the cell
     * @param coordinates (List<DiscreteCoordinates) : singleton list containing the coordinates of the cell which needs to be left
     */
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		cells[coord.x][coord.y].leave(entity);
    	}
    }
    
    /**
     * Method making the entity enter the cell at the coordinates in the list
     * @param entity (Interactable) : the entity which wants to enter the cell
     * @param coordinates (List<DiscreteCoordinates) : singleton list containing the coordinates of the cell which needs to be entered
     */
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord : coordinates) {
    		cells[coord.x][coord.y].enter(entity);
    	}
    }

    /**
     * Method calling the cellInteractionOf method of the interactor's current cell
     * @param interactor (Interactor) : the interactor who wants to interact with the cell-interactable interactables in his cell
     */
    public void cellInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
    		cells[coord.x][coord.y].cellInteractionOf(interactor);
    	}
    }

    /**
     * Method calling the viewInteractionOf method of the cell in the interactor's field of view
     * @param interactor (Interactor) : the interactor who wants to interact with the cell-interactable interactables in his cell
     */
    public void viewInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
    		cells[coord.x][coord.y].viewInteractionOf(interactor);
    	}
    }
    
    /**
     * Method returning the cell specified by the coordinates in the parameter
     * @param coordinates (DiscreteCoordinates) : the coordinates
     * @return cell (Cell) : the cell specified by the coordinates
     */
    protected Cell getCell(DiscreteCoordinates coordinates) {    	
    	return cells[coordinates.x][coordinates.y];
    }
    
    /**
     * Method checking if there exists a cell at the coordinates sent in the parameter
     * @param coordinates (DiscreteCoordinates) : the coordinates
     * @return boolean : true if there exists a cell at the coordinates specified by the coordinates
     */
    public boolean isInGrid(DiscreteCoordinates coordinates) {
    	if (coordinates.x < cells.length && coordinates.x >= 0 && coordinates.y < cells[coordinates.x].length && coordinates.y >= 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
}