package ch.epfl.cs107.play.game.areagame.actor;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

    //Indicate if the actor is currently moving
	private boolean isMoving;
	//Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	//The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;
	
	/**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }
    
    /**
     * @return boolean describing if the entity is moving or not
     */
    protected boolean isMoving() {
    	return isMoving;
    }
    
    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion() {
        isMoving = false;
        framesForCurrentMove = 0;
        targetMainCellCoordinates = getCurrentMainCellCoordinates();        
    }

    /**
     * 
     * @param framesForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
    protected boolean move(int framesForMove) {
    	if(!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
    		if (getOwnerArea().leaveAreaCells(this, getLeavingCells()) && getOwnerArea().enterAreaCells(this, getEnteringCells())) {
    			framesForCurrentMove = ((framesForMove < 1) ? 1 : framesForMove);
    			Vector orientation = getOrientation().toVector();
        		targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
        		isMoving = true;
        		return true;
    		} else {
    			resetMotion();
    			return false;
    		}
    	} else {
    		return true;
    	}
    }	
    
    /**
     * Function used to initiate the movement of a Follower when its parent is moving
     * No conditions are checked because they were checked while initiating the movement of the parent
     * @param framesForMove (int) : number of frames used for simulating motion
     */
    protected void forceMove (int framesForMove) {
    	framesForCurrentMove = ((framesForMove < 1) ? 1 : framesForMove);
		Vector orientation = getOrientation().toVector();
		targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
		isMoving = true;
    }
    
    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
    	if (isMoving && !getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
    		Vector distance = getOrientation().toVector();
    		distance = distance.mul(1.0f / framesForCurrentMove);
    		setCurrentPosition(getPosition().add(distance));
    	} else {
    		resetMotion();
    	}
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        return getOrientation().toVector().mul(framesForCurrentMove);
    }
    
    /**
     * @return list of coordinates (DiscreteCoordinates) of the cells the entity wants to leave
     */
    protected final List<DiscreteCoordinates> getLeavingCells(){
    	return getCurrentCells();
    }
    
    /**
     * @return list of coordinates (DiscreteCoordinates) of the cells the entity wants to enter
     */
    protected final List<DiscreteCoordinates> getEnteringCells(){
    	DiscreteCoordinates coord = getCurrentMainCellCoordinates();
    	return Arrays.asList(coord.jump(getOrientation().toVector()));
    }
    
	/**
	 * Method used to make the Demo2Player enter an area
	 * @param area (Area) : the area into which the Demo2Player should enter
	 * @param position (DiscreteCoordinates) : the position of the Demo2Player in the new area
	 */
	public void	enterArea(Area area , DiscreteCoordinates position) {
		area.registerActor(this);
		area.enterAreaCells(this, Arrays.asList(position));
		setCurrentPosition(position.toVector());
		this.resetMotion();
		this.setOwnerArea(area);
	}

	/**
	 * Method used to make the Demo2Player leave an area
	 * @param area (Area) : the area which the Demo2Player should leave
	 * @param position (DiscreteCoordinates) : the position from the Demo2Player should be removed from the area
	 */
	public void leaveArea(Area area, DiscreteCoordinates position) {
		area.unregisterActor(this);
		area.leaveAreaCells(this, Arrays.asList(position));
		this.resetMotion();
		this.setOwnerArea(null);
	}
}