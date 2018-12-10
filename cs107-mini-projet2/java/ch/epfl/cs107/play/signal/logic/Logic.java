package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
	
	//Logical TRUE defined as a Logic
	Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};

	//Logical FALSE defined as a Logic
	Logic FALSE = new Logic() {
		@Override
		public boolean isOn() {
			return false;
		}
	};
	
	/**
	 * Method describing if the signal is ON or OFF
	 * Note : needs to be overridden
	 * @return (boolean) : true if signal is on, false otherwise
	 */
	abstract public boolean isOn();
	
	/**
	 * Default method describing intensity of the signal
	 * @return (float) : intensity of the signal (between 0f and 1f)
	 */
	public default float getIntensity() {
		return isOn() ? 1.0f : 0.0f;
	}
	
	//Extends Signal
	
	@Override
	default public float getIntensity(float t) {
		return getIntensity();
	}	
}