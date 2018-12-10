package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

    // Context objects
	private	Window window ;
	private	FileSystem fileSystem ;
	
	// List of Actors inside the area
	private	List <Actor > actors;
	
	// List of the Interactors inside the area
	private List<Interactor> interactors;
	
	//Waiting Lists for the actors
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	
	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate;
	
	// effective center of the view
	private Vector viewCenter;
	
	// The behavior map
	private AreaBehavior areaBehavior;
	
	// First time or not
	private boolean played = false;
	
	// Saving the interactables that need to be entered and removed from their cells
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;
	
	// Attributes used for the pausing mechanism
	private boolean paused;
	private Button space;
	private TextGraphics unpause;
	
	public boolean isPlayed() {
		return played;
	}

    // TODO implements me #PROJECT #TUTO
	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();
    	
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {    	
    	boolean errorOccured = !actors.add(a);
    	
    	if (a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.add((Interactor) a);
    	}
    	
    	if (a instanceof Interactable) {
    		errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
  		}
    	
    	if (errorOccured && !forced) {
    		System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
    		System.out.println("Position where this actor was being added : " + a.getPosition());
    		removeActor(a, true);
    	}
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced) {
    	boolean errorOccured = !actors.remove(a);
    	
    	if (a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.remove((Interactor) a);
    	}
    	
    	if (a instanceof Interactable) {
    		errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
  		}
    	
    	if (errorOccured && !forced) {
    		System.out.println("Actor " + a + " cannot be completely removed, so add it to where it was");
    		addActor(a, true);
    	}
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
    	return registeredActors.add(a);
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
    	return unregisteredActors.add(a);
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        return areaBehavior.getHeight();
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        return window.getKeyboard();
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
   
    	actors = new LinkedList<>();
    	registeredActors = new LinkedList<>();
    	unregisteredActors = new LinkedList<>();
    	
    	interactors = new LinkedList<>();
    	
    	this.window = window;
		this.fileSystem = fileSystem;
		
		//Initialize the Camera attributes
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		
		//Initialize maps
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();
		
		played = true;
		paused = false;
		
		unpause = new TextGraphics("Game PAUSED -> Press Space to unpause", 0.7f, Color.BLACK, null, 0.2f, true, false, Vector.ZERO, null, null, 1, 1000);
		
		return true;
    }
    
    public final void setViewCandidate(Actor a) {
    	viewCandidate = a;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }
    
    private final void purgeRegistration() {
    	for (Actor a : registeredActors) {
    		addActor(a, false);
    	}
    	
    	for (Actor a : unregisteredActors) {
    		removeActor(a, false);
    	}
    	
    	for (Entry<Interactable, List<DiscreteCoordinates>> e : interactablesToEnter.entrySet()) {
    		areaBehavior.enter(e.getKey(), e.getValue());
    	}
    	
    	for (Entry<Interactable, List<DiscreteCoordinates>> e : interactablesToLeave.entrySet()) {
    		areaBehavior.leave(e.getKey(), e.getValue());
    	}
    	
    	registeredActors.clear();
    	unregisteredActors.clear();
    	interactablesToEnter.clear();
    	interactablesToLeave.clear();
    }

    @Override
    public void update(float deltaTime) {    	
    	
    	space = this.getKeyboard().get(Keyboard.SPACE);
    	
    	if (space.isReleased()) {
    		paused = !paused;
    	}
    	
    	if (!paused) {
    		purgeRegistration();
        	
        	for (int i = 0; i < actors.size(); ++i) {
        		actors.get(i).update(deltaTime);
        	}
        	
        	for (Interactor interactor : interactors) { 
        		if (interactor.wantsCellInteraction()) { 
        			areaBehavior.cellInteractionOf(interactor);
        		}
        		if (interactor.wantsViewInteraction()) { 
        			areaBehavior.viewInteractionOf(interactor);
        		}
        	}
        	
        	updateCamera();

    	} else {
    		unpause.setAnchor(viewCandidate.getPosition().add(new Vector(-6f, -1f)));
    		unpause.draw(window);
    	}
    	
    	for (int i = 0; i < actors.size(); ++i) {
   			actors.get(i).draw(window);
    	}
    }
   
    private void updateCamera() {
        
    	if (!(viewCandidate == null)) {
        	viewCenter = viewCandidate.getPosition();
        }
        
        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
    	purgeRegistration();
    }
    
    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }
    
    protected final void setBehavior(AreaBehavior ab) {
    	areaBehavior = ab;
    }
    
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	boolean canLeave = areaBehavior.canLeave(entity, coordinates);
    	if (canLeave) {
    		interactablesToLeave.put(entity, coordinates);
    	}
    	return canLeave;
    }
    
    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	boolean canEnter = areaBehavior.canEnter(entity, coordinates);
    	if (canEnter) {
    		interactablesToEnter.put(entity, coordinates);
    	}
    	return canEnter;
    }
    
    public boolean isInGrid(DiscreteCoordinates coordinates) {
    	return areaBehavior.isInGrid(coordinates);
    }
    
    public Window getWindow() {
    	return window;
    }
}