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
import ch.epfl.cs107.play.game.enigme.actor.collectable.Egg;
import ch.epfl.cs107.play.game.enigme.actor.switcher.PressureSwitch;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Collectable
	 * @param collectable (Collectable), not null
	 */
	public default void interactWith(Collectable collectable) {
		
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme PressurePlate
	 * @param pressurePlate (PressurePlate), not null
	 */
	public default void interactWith(PressurePlate pressurePlate) {
		
	}
	
	/**
	 * SImulate an interaction between an enigme Interactor and an enigme Switcher
	 * @param switcher (Switcher), not null
	 */
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
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme EnigmePlayer
	 * @param enigmePlayer (EnigmePlayer), not null
	 */
	public default void interactWith(EnigmePlayer enigmePlayer) {
		
	}

	/**
	 * Simulate an interaction between an enigme Interactor and an enigme PressureSwitch
	 * @param pressureSwitch (PressureSwitch), not null
	 */
	public default void interactWith(PressureSwitch pressureSwitch) {
		
	}

	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Teleporter
	 * @param teleporter (Teleporter), not null
	 */
	public default void interactWith(Teleporter teleporter) {
		
	}

	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Sage
	 * @param sage (Sage), not null
	 */
	public default void interactWith(Sage sage) {
		
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Egg
	 * @param egg (Egg), not null
	 */
	public default void interactWith(Egg egg) {
		
	}
}
