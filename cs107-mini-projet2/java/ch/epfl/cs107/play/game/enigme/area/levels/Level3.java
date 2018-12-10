/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Key;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Lever;
import ch.epfl.cs107.play.game.enigme.actor.switcher.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Torch;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logicGates.MultipleAnd;
import ch.epfl.cs107.play.signal.logicGates.Not;
import ch.epfl.cs107.play.signal.logicGates.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
	
	private Actor key, torch, pressurePlate, signalDoor;
	private List<Actor> pressureSwitches;
	private List<Actor> levers;
	private List<Actor> signalRocks;
	
	@Override
	public String getTitle() {
		return "Level3";
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		
		key = new Key(this, new DiscreteCoordinates(1, 3));
		
		torch = new Torch(this, new DiscreteCoordinates(7, 5), false);
		
		pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9, 8));
		
		DiscreteCoordinates signalDoorPosition = new DiscreteCoordinates(5, 9);
		signalDoor = new SignalDoor(((Logic)key), this, "LevelSelector", new DiscreteCoordinates(3, 6),	signalDoorPosition, signalDoorPosition);
		
		pressureSwitches = new LinkedList<Actor>();
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(4, 4)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(5, 4)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(6, 4)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(5, 5)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(4, 6)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(5, 6)));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(6, 6)));

		levers = new LinkedList<Actor>();
		levers.add(new Lever(this, new DiscreteCoordinates(10, 5)));
		levers.add(new Lever(this, new DiscreteCoordinates(9, 5)));
		levers.add(new Lever(this, new DiscreteCoordinates(8, 5)));
		
		signalRocks = new LinkedList<Actor>();
		signalRocks.add(new SignalRock(((Logic) pressurePlate), this, new DiscreteCoordinates(6, 8)));
		
		Logic allPressureButtons = new MultipleAnd((Logic)pressureSwitches.get(0), (Logic)pressureSwitches.get(1),
				(Logic)pressureSwitches.get(2), (Logic)pressureSwitches.get(3), (Logic)pressureSwitches.get(4), 
				(Logic)pressureSwitches.get(5), (Logic)pressureSwitches.get(6));
		signalRocks.add(new SignalRock(allPressureButtons, this, new DiscreteCoordinates(5, 8)));
		
		Logic leversMakeFiveOrTorch = new Or(new MultipleAnd((Logic)levers.get(0), new Not((Logic)levers.get(1)), (Logic)levers.get(2)), (Logic) torch);
		signalRocks.add(new SignalRock(leversMakeFiveOrTorch, this, new DiscreteCoordinates(4, 8)));
		
		return super.begin(window, fileSystem);
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(key);
		actors.add(torch);
		actors.add(pressurePlate);
		actors.add(signalDoor);
		for (Actor pressureSwitch : pressureSwitches) {
			actors.add(pressureSwitch);
		}
		
		for (Actor lever : levers) {
			actors.add(lever);
		}
		
		for (Actor signalRock : signalRocks) {
			actors.add(signalRock);
		}
	}
}