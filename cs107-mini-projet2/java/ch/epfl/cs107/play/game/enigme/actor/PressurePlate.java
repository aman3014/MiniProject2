/*
 *	Author:		Julian Blackwell
 *	Date:		5 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends AreaEntity implements Logic {

	private Sprite imageOn, imageOff;
	private boolean isOn;
	private final float activationTime;
	private float timePassed;
	
	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.imageOn = new Sprite("GroundLightOn", 1, 1.f, this);
		this.imageOff = new Sprite("GroundPlateOff", 1, 1.f, this);
		this.isOn = false;
		this.activationTime = 2f;
		this.timePassed = 0.0f;
	}
	
	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position, float activationTime) {
		super(area, orientation, position);
		this.imageOn = new Sprite("GroundLightOn", 1, 1.f, this);
		this.imageOff = new Sprite("GroundPlateOff", 1, 1.f, this);
		this.isOn = false;
		this.activationTime = activationTime;
		this.timePassed = 0.0f;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(new DiscreteCoordinates((int)getPosition().getX(), (int)getPosition().getY()));
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);		
	}

	@Override
	public void draw(Canvas canvas) {
		if(isOn) {
			imageOn.draw(canvas);
		} else {
			imageOff.draw(canvas);
		}
	}

	@Override
	public boolean isOn() {
		return isOn;
	}
	
	public float getActivationTime() {
		return activationTime;
	}
	
	public void turnOn() {
		this.isOn = true;
		this.timePassed = 0.0f;
	}
	
	public void update(float deltaTime) {
		if (isOn) {
			this.timePassed += deltaTime;
		} else {
			this.timePassed = 0.0f;
		}
		
		if (timePassed >= activationTime) {
			this.isOn = false;
		}
	}
}