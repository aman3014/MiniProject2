/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logic;

public abstract class LogicSignal implements Logic {
	
	@Override
	public final float getIntensity() {
		return Logic.super.getIntensity();
	}
	
	@Override
	public final float getIntensity(float t) {
		return Logic.super.getIntensity(t);
	}
}