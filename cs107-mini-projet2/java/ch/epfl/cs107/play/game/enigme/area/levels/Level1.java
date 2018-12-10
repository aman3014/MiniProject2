/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

/**
 * Level 1 "Room/Level" (instance of EnigmeArea)
 * @author Julian Blackwell, Aman Bansal
 *
 */
public class Level1 extends EnigmeArea {
	
	//actor in area
	private Actor doorToLevelSelector;
	
	@Override
	public String getTitle() {
		return "Level1";
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		DiscreteCoordinates position = new DiscreteCoordinates(5, 0);
		doorToLevelSelector = new SignalDoor(Logic.TRUE, this, "LevelSelector", new DiscreteCoordinates(1, 6), position, position);
		return super.begin(window, fileSystem);
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(doorToLevelSelector);
	}
}