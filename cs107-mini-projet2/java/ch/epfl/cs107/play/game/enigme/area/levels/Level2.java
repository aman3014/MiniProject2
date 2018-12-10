/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.levels;

import java.awt.Color;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Apple;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {

	private Actor apple;
	private Actor doorToLevelSelector;
	
	@Override
	public String getTitle() {
		return "Level2";
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		
		apple = new Apple(this, Orientation.DOWN, new DiscreteCoordinates(5, 6));
		DiscreteCoordinates position = new DiscreteCoordinates(5, 0);
		doorToLevelSelector = new SignalDoor(Logic.TRUE, this, "LevelSelector", new DiscreteCoordinates(2, 6), Orientation.DOWN, position, position);
		
		return super.begin(window, fileSystem);
	}
	
	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(apple);
		actors.add(doorToLevelSelector);
	}
	
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
}