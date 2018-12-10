package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Demo2Player;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/*
 *	Author:      Aman Bansal
 *	Date:        23 nov. 2018
 */

public class Demo2 extends AreaGame {	

	public final static float demo2CameraScaleFactor = 22;
	private final DiscreteCoordinates LEVEL_SELECTOR_POSITION = new DiscreteCoordinates(5, 5);
	private final DiscreteCoordinates LEVEL1_POSITION = new DiscreteCoordinates(5, 2);
	
	private Actor actor;

	@Override
	public int getFrameRate() {
		return 24;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}
	
	 @Override
	 public boolean begin(Window window, FileSystem fileSystem) {   	    			
		if (!(super.begin(window, fileSystem))) {
			return false;
		}
		
		addArea(new Room0());
		addArea(new Room1());
		
		setCurrentArea("LevelSelector", false);
		
		actor = new Demo2Player(getCurrentArea(), LEVEL_SELECTOR_POSITION);
		((Demo2Player)actor).enterArea(getCurrentArea(), LEVEL_SELECTOR_POSITION);
		
		getCurrentArea().setViewCandidate(actor);

		return true;
	 }
	 
	 public void update(float deltaTime) {
		 super.update(deltaTime);
		 if (((Demo2Player)actor).isPassingDoor()) {
			 ((Demo2Player)actor).setPassingDoor(false);
			 changeCurrentArea();
		 }
	 }

	 private void changeCurrentArea() {
		 if (getCurrentArea().getTitle().equals("LevelSelector")) {
			 ((Demo2Player)actor).leaveArea(getCurrentArea(), ((Demo2Player)actor).getCurrentCells().get(0));
				
			try {
		    		setCurrentArea("Level1", false);
		   	} catch (NullPointerException e){
		    		System.out.println(e.getMessage());
		   	}
				
			 ((Demo2Player)actor).enterArea(getCurrentArea(), LEVEL1_POSITION);
		 } else {
			 ((Demo2Player)actor).leaveArea(getCurrentArea(), ((Demo2Player)actor).getCurrentCells().get(0));
			
			try {
		    		setCurrentArea("LevelSelector", false);
		   	} catch (NullPointerException e){
		    		System.out.println(e.getMessage());
		   	}
			
			((Demo2Player)actor).enterArea(getCurrentArea(), LEVEL_SELECTOR_POSITION);
		 }
		 getCurrentArea().setViewCandidate(actor);
	 }
}