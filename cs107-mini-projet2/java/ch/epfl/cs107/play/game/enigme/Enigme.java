package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Follower;
import ch.epfl.cs107.play.game.enigme.area.levels.Enigme0;
import ch.epfl.cs107.play.game.enigme.area.levels.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.levels.Level1;
import ch.epfl.cs107.play.game.enigme.area.levels.Level2;
import ch.epfl.cs107.play.game.enigme.area.levels.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

/// The player is a concept of RPG games

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	public final static float enigmeCameraScaleFactor = 22;
	
	private EnigmePlayer player;
	private Follower follower;

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public int getFrameRate() {
        return 24;
    }
    
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
		if (!(super.begin(window, fileSystem))) {
			return false;
		}
		
		addArea(new LevelSelector());
		addArea(new Level1());
		addArea(new Level2());
		addArea(new Level3());
		addArea(new Enigme2());
		addArea(new Enigme0());

		setCurrentArea("LevelSelector", false);
		
		player = new EnigmePlayer(getCurrentArea(), new DiscreteCoordinates(5, 5));
		player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
		
		follower = new Follower(player);
		follower.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 6));
		
		player.setFollower(follower);

		getCurrentArea().setViewCandidate(player);
		
		return true;
    }

    @Override
    public void update(float deltaTime) {
    	super.update(deltaTime);
    	if (player.getIsPassingDoor()) {
    		changeCurrentArea();
    		player.setIsPassingDoor(null);
    	}
    }
    
    private void changeCurrentArea() {
    	// Player and his follower leave the current area
    	player.leaveArea(getCurrentArea(), player.getCurrentCells().get(0));
    	follower.leaveArea(getCurrentArea(), follower.getCurrentCells().get(0));
    	
    	// The current area is suspended so that purgeRegistration() is carried out 
    	// and all the players successfully leave the area
    	getCurrentArea().suspend();
    	
    	// The current area of the Enigme is changed to the one specified by the door the player entered
    	try {
    		setCurrentArea(player.getLastDoor().getDestAreaName(), false);
    	} catch (NullPointerException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	// The player and his follower enter the new area
    	player.enterArea(getCurrentArea(), player.getLastDoor().getDestCoordinates());
    	Vector newCoordinates = player.getPosition().sub(player.getOrientation().toVector());
		follower.enterArea(getCurrentArea(), new DiscreteCoordinates((int) newCoordinates.x, (int) newCoordinates.y));
    	
		// The new area's view is centered on the player
    	getCurrentArea().setViewCandidate(player);
    }
}