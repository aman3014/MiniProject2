/*
 *	Author:      Aman Bansal
 *	Date:        3 déc. 2018
 */

package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.Sage;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.game.enigme.actor.switcher.PressureSwitch;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Collectable
	 * @param collectable (Collectable), not null
	 */
	public default void interactWith(Collectable collectable) {
		
	}
	
	public default void interactWith(PressurePlate pressurePlate) {
		
	}
	
	public default void interactWith(Switcher switcher) {
		
	}
	/**
	 * Simulate an interaction between an enigme Interactor and an EnigmeBehavior EnigmeCell
	 * @param cell (EnigmeCell), not null
	 */
	public default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Door
	 * @param door (Door), not null
	 */
	public default void interactWith(Door door) {
		
	}
	
	public default void interactWith(EnigmePlayer enigmePlayer) {
		
	}

	public default void interactWith(PressureSwitch pressureSwitch) {
		
	}

	public default void interactWith(Teleporter teleporter) {
		
	}

	public default void interactWith(Sage sage) {
		
	}
	
//	public void interactWith(PressurePlate other);

//	public void interactWith(Rock other);

}
