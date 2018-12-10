/*
 *	Author:      Aman Bansal
 *	Date:        10 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Egg;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Bonfire;
import ch.epfl.cs107.play.game.enigme.actor.switcher.SignalSage;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logicGates.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea {
	
private Actor doorToLevelSelector;

private Actor egg;
private Actor signalRock;
private Actor bonfire;
private Actor sageNorth, sageEast, sageSouth, sageWest;
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		DiscreteCoordinates position = new DiscreteCoordinates(5, 0);
		doorToLevelSelector = new SignalDoor(Logic.TRUE, this, "LevelSelector", new DiscreteCoordinates(1, 6), Orientation.DOWN, position, position);
		
		sageNorth = new SignalSage(this, Orientation.DOWN, new DiscreteCoordinates(6, 14), "I want to see North", Orientation.UP);
		sageEast = new SignalSage(this, Orientation.LEFT, new DiscreteCoordinates(4, 16), "I want to see the sun rise", Orientation.RIGHT);
		sageSouth = new SignalSage(this, Orientation.UP, new DiscreteCoordinates(6, 18), "I want to see South", Orientation.DOWN);
		sageWest = new SignalSage(this, Orientation.RIGHT, new DiscreteCoordinates(8, 16), "I want to see the night fall", Orientation.LEFT);
		bonfire = new Bonfire(this, Orientation.DOWN, new DiscreteCoordinates(6, 16), false, new MultipleAnd((Logic)sageNorth,(Logic)sageEast, (Logic)sageSouth, (Logic)sageWest));
		
		egg = new Egg(this, Orientation.DOWN, new DiscreteCoordinates(15, 15));
		signalRock = new SignalRock((Logic)bonfire, this, Orientation.DOWN, new DiscreteCoordinates(16, 15));
		
		if (!super.begin(window, fileSystem)) {
			return false;
		}
		
		registerActor(new Foreground(this));
		
		return true;
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(doorToLevelSelector);
		actors.add(egg);
		actors.add(signalRock);
		actors.add(sageNorth);
		actors.add(sageEast);
		actors.add(sageSouth);
		actors.add(sageWest);
		actors.add(bonfire);
	}
}