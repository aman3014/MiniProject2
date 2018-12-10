package ch.epfl.cs107.play.signal.logic;

/**
 * Abstract class modelizing a logic signal
 * @author Julian Blackwell
 * @author Aman Bansal
 *
 */
public abstract class LogicSignal implements Logic {
	
	//Implements Logic
	
	@Override
	public final float getIntensity() {
		return Logic.super.getIntensity();
	}
	
	@Override
	public final float getIntensity(float t) {
		return Logic.super.getIntensity(t);
	}
}