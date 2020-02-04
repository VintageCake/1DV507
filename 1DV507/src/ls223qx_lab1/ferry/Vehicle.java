package ls223qx_lab1.ferry;

import java.util.ArrayList;

public abstract class Vehicle { // abstract class defining characteristics of a vehicle
	private ArrayList<Passenger> passengers = new ArrayList<>(); // passenger 'holder'
	private int spaceRequired;

	public Vehicle(int pass, int space, int max) { // passed variables for current passenger number, max passenger number and vehicle space required
		validPassengerNumber(pass, max);
		generatePassenger(pass);
		spaceRequired = space;
	}

	public int getNumberPassengers() {
		return passengers.size();
	}

	public int getSpace() {
		return spaceRequired;
	}

	public String toString() { // Return type of vehicle + N passengers
		return "This is a " + this.getClass().getSimpleName() + " with " + getNumberPassengers() + " passengers";
	}

	private void generatePassenger(int p) { // generate passenger objects to fill list
		for (int i = 0; i < p; i++) {
			passengers.add(new Passenger());
		}
	}

	private void validPassengerNumber(int pass, int max) { // verify that passengers in vehicle does not exceed vehicle
															// maximum
		if (pass > max) {
			throw new RuntimeException("Too many passangers!");
		}
	}

}
