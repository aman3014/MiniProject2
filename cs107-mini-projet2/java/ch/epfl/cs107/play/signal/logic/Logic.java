/*
 *	Author:      Aman Bansal
 *	Date:        5 déc. 2018
 */

package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
	
	Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};

	Logic FALSE = new Logic() {
		@Override
		public boolean isOn() {
			return false;
		}
	};
	
	abstract public boolean isOn();
	
	public default float getIntensity() {
		return isOn() ? 1.0f : 0.0f;
	}
	
	@Override
	default public float getIntensity(float t) {
		return getIntensity();
	}	
}