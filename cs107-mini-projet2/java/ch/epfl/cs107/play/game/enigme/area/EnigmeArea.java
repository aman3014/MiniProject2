/*
 *	Author:      Aman Bansal
 *	Date:        2 déc. 2018
 */

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

public abstract class EnigmeArea extends Area {

	private List<Actor> actors;
	private AreaBehavior areaBehavior;
	private Window window;
	
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
    
    public float getCameraScaleFactor() {
    	return Enigme.enigmeCameraScaleFactor;
    }
    
    abstract protected void addAllActors(List<Actor> actors);
}