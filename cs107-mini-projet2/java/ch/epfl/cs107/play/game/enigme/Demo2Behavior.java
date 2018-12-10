/*
 *	Author:      Aman Bansal
 *	Date:        23 nov. 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	public enum Demo2CellType {
		NULL(0),
		WALL(-16777216),		// RGB code of black
		DOOR(-65536),			// RGB code of red
		WATER(-16776961),		// RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
		
		final int type;
		
		Demo2CellType(int type) {
			this.type = type;
		}
		
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

	public class Demo2Cell extends Cell {
		private Demo2CellType nature;

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
		
		protected boolean isDoor() {
			return nature.equals(Demo2CellType.DOOR);
		}
	}
	
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
	}
	
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