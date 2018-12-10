package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.Enigme;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * Abstract class describing an instance of an Area specific to an Enigme game
 * @author Julian Blackwell, Aman Bansal
 *
 */
public abstract class EnigmeArea extends Area {

	//Actors "playing" in area
	private List<Actor> actors;
	//The area's behavior
	private AreaBehavior areaBehavior;
	//The area's window
	private Window window;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			this.window = window;
			areaBehavior = new EnigmeBehavior(window, getTitle());
			setBehavior(areaBehavior);
			registerActor(new Background(this));
			
			actors = new LinkedList<>();
			addAllActors(actors);
			for (Actor actor : actors) {
				this.registerActor(actor);
				this.enterAreaCells((Interactable) actor, ((Interactable)actor).getCurrentCells());
			}
			return true; 
		}
		return false;
	}
    
	@Override
    public float getCameraScaleFactor() {
    	return Enigme.enigmeCameraScaleFactor;
    }
    
	/**
	 * Add all actors to the area
	 * Note: needs to be overridden
	 * @param actors (List<Actors>) : list of actors to which all actors are to be added
	 */
    abstract protected void addAllActors(List<Actor> actors);
}