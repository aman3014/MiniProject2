/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {
	
	public enum EnigmeCellType {
		NULL(0),
		WALL(-16777216),		// RGB code of black
		DOOR(-65536),			// RGB code of red
		WATER(-16776961),		// RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
		
		final int type;
		
		EnigmeCellType(int type) {
			this.type = type;
		}
		
		public static EnigmeCellType toType(int type) {	
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

	public class EnigmeCell extends Cell {
		private EnigmeCellType nature;

		public EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x, y);
			nature = type;
		}

		@Override
		public boolean isViewInteractable() {
			return super.isViewInteractable();
		}

		@Override
		public boolean isCellInteractable() {
			return super.isCellInteractable();
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			super.acceptInteraction((EnigmeInteractionVisitor) v);
		}
		
		@Override
		public boolean takeCellSpace() {
			return super.takeCellSpace() || this.nature.equals(EnigmeCellType.WALL) || this.nature.equals(EnigmeCellType.WATER);
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			
			if (nature.equals(EnigmeCellType.NULL)) {
				return false;
			}
			
			// Exceptional case for a EnigmePlayer who is flying
			if (entity instanceof EnigmePlayer) {
				if (((EnigmePlayer)entity).isFlying()) {
					return true;
				}
			}
			
			if (takeCellSpace()) {
				return false;
			} else { 
				return true;
			}
		}
																															
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}
	}
	
	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);
	}
	
	@Override
	/**
	 * Returns a 2D table of EnigmeCells initialized with natures depending on their coordinates
	 * @param behaviorMap (Image): this parameter is needed to get the EnigmeCell Type
	 */
	public Cell[][] initializeCells(Image behaviorMap) {
		Cell[][] cells = new EnigmeCell[this.getHeight()][this.getWidth()];
		for (int i = 0; i < cells.length; ++i) {
			for (int j = 0; j < cells[0].length; ++j) {
				cells[i][j] = new EnigmeCell(i, j, EnigmeCellType.toType(behaviorMap.getRGB(this.getHeight()-1-j, i)));
			}
		}
		return cells;
	}
}