package ch.epfl.cs107.play.game.enigme.actor;

/**
 * Interface Talker : used to impose its implementers to redefine the getDialog function used to show a dialog
 * @author Aman Bansal, Julian Blackwell
 *
 */
public interface Talker {
	
	public abstract Dialog getDialog();
}
