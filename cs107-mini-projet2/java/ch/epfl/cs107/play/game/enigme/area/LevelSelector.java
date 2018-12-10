/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
			
	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public float getCameraScaleFactor() {
		return super.getCameraScaleFactor();
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		if (!super.begin(window, fileSystem)) {
			return false;
		}

		registerActor(new Foreground(this));
		
		return true;
	}
	
	public void addAllActors(List<Actor> actors) {
		DiscreteCoordinates arrivalCoordinates = new DiscreteCoordinates(5, 1);
		Orientation doorsOrientation = Orientation.DOWN;
		
		DiscreteCoordinates door1Position = new DiscreteCoordinates(1, 7);
		actors.add(new SignalDoor(Logic.TRUE, this, "Level1", arrivalCoordinates, doorsOrientation, door1Position, door1Position));
		
		DiscreteCoordinates door2Position = new DiscreteCoordinates(2, 7);
		actors.add(new SignalDoor(Logic.TRUE, this, "Level2", arrivalCoordinates, doorsOrientation, door2Position, door2Position));
		
		DiscreteCoordinates door3Position = new DiscreteCoordinates(3, 7);
		// arrival coordinates are 5, 2 so that the follower of the EnigmePlayer can stand behind him
		actors.add(new SignalDoor(Logic.TRUE, this, "Level3", new DiscreteCoordinates(5, 2), doorsOrientation, door3Position, door3Position));
		
		DiscreteCoordinates door4Position = new DiscreteCoordinates(4, 7);
		actors.add(new SignalDoor(Logic.TRUE, this, "Enigme2", new DiscreteCoordinates(7, 1), doorsOrientation, door4Position, door4Position));
		
		DiscreteCoordinates door5Position = new DiscreteCoordinates(5, 7);
		actors.add(new SignalDoor(Logic.TRUE, this, "Enigme0", new DiscreteCoordinates(9, 28), doorsOrientation, door5Position, door5Position));
		
		for (int i = 6; i <= 8; ++i) {
			DiscreteCoordinates doorPosition = new DiscreteCoordinates(i, 7);
			actors.add(new SignalDoor(Logic.FALSE, this, "LevelSelector", new DiscreteCoordinates(5, 5), doorsOrientation, doorPosition, doorPosition));
		}
	}
}