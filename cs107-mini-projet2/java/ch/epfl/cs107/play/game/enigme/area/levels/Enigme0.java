/*
 *	Author:      Aman Bansal
 *	Date:        10 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea {
	
private Actor doorToLevelSelector;
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		DiscreteCoordinates position = new DiscreteCoordinates(5, 0);
		doorToLevelSelector = new SignalDoor(Logic.TRUE, this, "LevelSelector", new DiscreteCoordinates(1, 6), Orientation.DOWN, position, position);
		return super.begin(window, fileSystem);
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(doorToLevelSelector);
	}
}