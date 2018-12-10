package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * Behavior of a Demo2 Area
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public class Demo2Behavior extends AreaBehavior {

	/**
	 * Enumeration describing the types of cell that may occupy the Demo 2 Area
	 * @author Julian Blackwell
	 * @author Aman Bansal
	 */
	public enum Demo2CellType {
		NULL(0),
		WALL(-16777216),		// RGB code of black
		DOOR(-65536),			// RGB code of red
		WATER(-16776961),		// RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
		
		final int type;
		
		/**
		 * Default constructor
		 * @param type (int) : integer value of Demo2 cell type
		 */
		Demo2CellType(int type) {
			this.type = type;
		}
		
		/**
		 * Convert integer value to Demo2 cell type
		 * @param type (int) : integer value of Demo2 cell type
		 * @return (Demo2Cell) : type of Demo2Cell (e.g. WALL)
		 */
		public static Demo2CellType toType(int type) {	
			switch (type) {
				case -16777216 : return WALL;
				case -65536 : return DOOR;
				case -16776961 : return WATER;
				case -1 : return INDOOR_WALKABLE;
				case -14112955 : return OUTDOOR_WALKABLE;
				default : return NULL;
			}
		}
	}

	/**
	 * Class describing a cell on an Demo2 Area grid
	 * @author Julian Blackwell
	 * @author Aman Bansal
	 *
	 */
	public class Demo2Cell extends Cell {
		
		//nature of cell
		private Demo2CellType nature;

		/**
		 * Default constructor
		 * @param x (int) : x coordinate of cell
		 * @param y (int) : y coordinate of cell
		 * @param type (Demo2CellType) : nature of cell
		 */
		public Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			nature = type;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			if (nature == Demo2CellType.NULL|| nature == Demo2CellType.WALL) {
				return false;
			}
			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}
		
		/**
		 * Describes if cell is a door or not
		 * @return (boolean) : true if nature of cell is DOOR, false otherwise
		 */
		protected boolean isDoor() {
			return nature.equals(Demo2CellType.DOOR);
		}
	}
	
	/**
	 * Default constructor
	 * @param window (Window) : window of the Demo2 area
	 * @param fileName (String) : name of the file containing the Demo2 Area behavior
	 */
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
	}
	
	/**
	 * Describes if coordinates are on a door
	 * @param coordinates (DiscreteCoordinates) : coordinates asking if they are on a door
	 * @return (boolean) : true if coordinates correspond to the position of a door
	 */
	public boolean isOnDoor(DiscreteCoordinates coordinates) {
		return ((Demo2Cell)getCell(coordinates)).isDoor();
	}
	
	@Override
	/**
	 * Returns a 2D table of Demo2Cells initialized with natures depending on their coordinates
	 * @param behaviorMap (Image): this parameter is needed to get the Demo2Cell Type
	 */
	public Cell[][] initializeCells(Image behaviorMap) {
		Cell[][] cells = new Demo2Cell[this.getHeight()][this.getWidth()];
		for (int i = 0; i < cells.length; ++i) {
			for (int j = 0; j < cells[0].length; ++j) {
				cells[i][j] = new Demo2Cell(i, j, Demo2CellType.toType(behaviorMap.getRGB(this.getHeight()-1-j, i)));
			}
		}
		return cells;
	}
}