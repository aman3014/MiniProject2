package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Abstract class Demo2Area representing an area in the Demo2 game; extends Area
 * @author Aman Bansal, Julian Blackwell
 */
public abstract class Demo2Area extends Area {
	
	private AreaBehavior areaBehavior;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			areaBehavior = new Demo2Behavior(window, getTitle());
			setBehavior(areaBehavior);
			registerActor(new Background(this));
			return true; 
		}
		return false;
	}
	
	/**
	 * Method returning if the cell specified by the coordinates in the parameter contains a door
	 * @param coordinates (DiscreteCoordinates) : the coordinates
	 * @return (boolean) : true if the cell contains a door
	 */
    public final boolean isOnDoor(DiscreteCoordinates coordinates) {
    	return ((Demo2Behavior)areaBehavior).isOnDoor(coordinates);
    }
    
    @Override
    public float getCameraScaleFactor() {
    	return Demo2.demo2CameraScaleFactor;
    }
}