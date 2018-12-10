package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.Sage;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.game.enigme.actor.collectable.FastShoes;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Key;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Lever;
import ch.epfl.cs107.play.game.enigme.actor.switcher.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.switcher.Torch;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logicGates.And;
import ch.epfl.cs107.play.signal.logicGates.MultipleAnd;
import ch.epfl.cs107.play.signal.logicGates.Not;
import ch.epfl.cs107.play.signal.logicGates.Or;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme 2 "Room/Level" (extends EnigmeArea)
 * @author Julian Blackwell, Aman Bansal
 *
 */
public class Enigme2 extends EnigmeArea {
	
	//Actors "living/playing" in the area
	
	private Actor doorToLevelSelector;
	private Actor fastShoes;
	private Actor key;
	private Actor teleporter1;
	private Actor teleporter2;
	private Actor pressurePlate;
	private Actor lever;
	private Actor torch;
	
	private Actor sage1;
	private Actor sage2;
	private Actor sage3;
	
	private List<Actor> signalRocks;
	private List<Actor> pressureSwitches;
	
	@Override
	public String getTitle() {
		return "Enigme2";
	}

	@Override
	public float getCameraScaleFactor() {
		return super.getCameraScaleFactor();
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
	
		//Initialize actor positions and signals
		
		key = new Key(this, new DiscreteCoordinates(6, 10));
		
		DiscreteCoordinates position = new DiscreteCoordinates(7, 0);
		doorToLevelSelector = new SignalDoor((Logic) key, this, "LevelSelector", new DiscreteCoordinates(4, 6), position, position);
		
		fastShoes = new FastShoes(this, new DiscreteCoordinates(8, 11));
		
		teleporter1 = new Teleporter(this, new DiscreteCoordinates(13, 8), new DiscreteCoordinates(8, 10), Orientation.UP);
		teleporter2 = new Teleporter(this, new DiscreteCoordinates(10, 11), new DiscreteCoordinates(13, 7), Orientation.DOWN);
		
		pressurePlate = new PressurePlate(this, new DiscreteCoordinates(13, 4), 1.9f);
		
		lever = new Lever(this, new DiscreteCoordinates(2, 10));
		
		torch = new Torch(this, new DiscreteCoordinates(6, 11));
		
		sage1 = new Sage(this, Orientation.RIGHT, new DiscreteCoordinates(9, 7), "I have heard of some magic shoes which help you move faster !");
		sage2 = new Sage(this, Orientation.LEFT, new DiscreteCoordinates(5, 7), "It's time to test what you learnt in your logic class !");
		sage3 = new Sage(this, Orientation.DOWN, new DiscreteCoordinates(11, 8), "Press W to wear/take off your magic shoes. I should warn you though, you may lose your child if you wear magic shoes while teleporting !");
		
		initializePressureSwitches();
		initializeSignalRocks();
		
		return super.begin(window, fileSystem);
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(doorToLevelSelector);
		actors.add(key);
		actors.add(fastShoes);
		actors.add(teleporter1);
		actors.add(teleporter2);
		actors.add(pressurePlate);
		actors.add(lever);
		actors.add(torch);
		actors.add(sage1);
		actors.add(sage2);
		actors.add(sage3);
		
		for (Actor rock : signalRocks) {
			actors.add(rock);
		}
		
		for (Actor pressureSwitch : pressureSwitches) {
			actors.add(pressureSwitch);
		}
	}
	
	/**
	 * Initialize SignalRock positions and signals
	 */
	private void initializeSignalRocks() {
		signalRocks = new LinkedList<Actor>();
		signalRocks.add(new SignalRock(new And((Logic) lever, (Logic) torch), this, new DiscreteCoordinates(6, 3)));
		signalRocks.add(new SignalRock((Logic) pressurePlate, this, new DiscreteCoordinates(6, 4)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(6, 5)));

		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(9, 10)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(10, 9)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(10, 10)));
		
		Logic oneZeroThing1 = new MultipleAnd((Logic) pressureSwitches.get(1), (Logic) pressureSwitches.get(4), (Logic) pressureSwitches.get(7), (Logic) pressureSwitches.get(10), 
				new Not((Logic) pressureSwitches.get(0)), new Not((Logic) pressureSwitches.get(2)), new Not((Logic) pressureSwitches.get(3)), new Not((Logic) pressureSwitches.get(5)),
				new Not((Logic) pressureSwitches.get(6)), new Not((Logic) pressureSwitches.get(8)), new Not((Logic) pressureSwitches.get(9)), new Not((Logic) pressureSwitches.get(11)));
		
		Logic oneZeroThing2 = new MultipleAnd((Logic) pressureSwitches.get(0), (Logic) pressureSwitches.get(3), (Logic) pressureSwitches.get(6), (Logic) pressureSwitches.get(9), 
				new Not((Logic) pressureSwitches.get(1)), new Not((Logic) pressureSwitches.get(2)), new Not((Logic) pressureSwitches.get(4)), new Not((Logic) pressureSwitches.get(5)),
				new Not((Logic) pressureSwitches.get(7)), new Not((Logic) pressureSwitches.get(8)), new Not((Logic) pressureSwitches.get(10)), new Not((Logic) pressureSwitches.get(11)));
				
		Logic oneZeroThing3 = new MultipleAnd((Logic) pressureSwitches.get(2), (Logic) pressureSwitches.get(5), (Logic) pressureSwitches.get(8), (Logic) pressureSwitches.get(11), 
				new Not((Logic) pressureSwitches.get(0)), new Not((Logic) pressureSwitches.get(1)), new Not((Logic) pressureSwitches.get(3)), new Not((Logic) pressureSwitches.get(4)),
				new Not((Logic) pressureSwitches.get(6)), new Not((Logic) pressureSwitches.get(7)), new Not((Logic) pressureSwitches.get(9)), new Not((Logic) pressureSwitches.get(10)));
		
		Logic oneZeroThing = new Or(oneZeroThing1, new Or(oneZeroThing2, oneZeroThing3));
		
		signalRocks.add(new SignalRock(oneZeroThing, this, new DiscreteCoordinates(5, 10)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(5, 11)));
		
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(6, 9)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(7, 9)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(7, 10)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(7, 11)));

		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(5, 11)));
		signalRocks.add(new SignalRock(Logic.FALSE, this, new DiscreteCoordinates(5, 11)));
		
	}
	
	/**
	 * Initialize PressureSwitch positions and states
	 */
	private void initializePressureSwitches() {
		pressureSwitches = new LinkedList<Actor>();
		
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(1, 3), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(2, 3), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(3, 3), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(1, 4), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(2, 4), false));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(3, 4), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(1, 5), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(2, 5), false));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(3, 5), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(1, 6), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(2, 6), true));
		pressureSwitches.add(new PressureSwitch(this, new DiscreteCoordinates(3, 6), true));
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
}