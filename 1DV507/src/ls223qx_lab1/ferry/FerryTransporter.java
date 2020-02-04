package ls223qx_lab1.ferry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FerryTransporter implements Ferry {
	private ArrayList<Vehicle> contentsVehicle = new ArrayList<>(); // Lists for vehicles and passengers.
	private ArrayList<Passenger> contentsPassengers = new ArrayList<>();

	private int ticketMoney = 0; // Total money earned

	private int carSpace = 40 * 5; // Amount of space for vehicles
	private int passengerSpace = 200; // Amount of space for passengers

	private final int REGULAR_PASSENGER_PRICE = 25; // Hardcoded price list
	private final int BICYCLE_AND_PASSENGER_PRICE = 40;
	private final int CAR_PRICE = 100;
	private final int CAR_PASSENGER_PRICE = 20;
	private final int BUS_PRICE = 200;
	private final int BUS_PASSENGER_PRICE = 15;
	private final int LORRY_PRICE = 300;
	private final int LORRY_PASSENGER_PRICE = 20;

	@Override
	public int countPassengers() { // count total passengers, including in vehicles
		int amountOfPassengers = 0;
		for (int i = 0; i < contentsVehicle.size(); i++) {
			amountOfPassengers += contentsVehicle.get(i).getNumberPassengers();
		}
		amountOfPassengers += contentsPassengers.size();
		return amountOfPassengers;
	}

	@Override
	public int countVehicleSpace() {
		int usedSpace = 0;
		for (int i = 0; i < contentsVehicle.size(); i++) { // get size of all loaded vehicles
			usedSpace += contentsVehicle.get(i).getSpace();
		}
		return Math.round((usedSpace / 5f));
		// It would be more ideal to return a float with 0.2 increments, but that would
		// violate the assignment interface.
	}

	@Override
	public int countMoney() { // return internal money counter
		return ticketMoney;
	}

	@Override
	public Iterator<Vehicle> iterator() { // return iterator object from internal class
		return new FerryIterator(contentsVehicle);
	}

	@Override
	public void embark(Vehicle v) {
		if (this.hasSpaceFor(v)) {
			if (hasRoomFor(v.getNumberPassengers())) {
				contentsVehicle.add(v);
				addMoney(calculateMoney(v));
			}
			else
				throw new RuntimeException("Not enough room for passenger!");
		}
		else
			throw new RuntimeException("Not enough room for vehicle!");

	}

	@Override
	public void embark(Passenger p) { // Embark single passenger
		if (this.hasRoomFor(p)) {
			contentsPassengers.add(p);
			addMoney(calculateMoney(p));
		}

	}

	@Override
	public void disembark() { // Clear contents
		contentsVehicle.clear();
		contentsPassengers.clear();

	}

	@Override
	public boolean hasSpaceFor(Vehicle v) {
		if (v.getSpace() + actualUsedSpace() > carSpace) { // Check car space
			return false;
		}
		else if (!hasRoomFor(v.getNumberPassengers())) { // Check passenger space
			return false;
		}
		else
			return true; // If both checks clear, ok to embark
	}

	@Override
	public boolean hasRoomFor(Passenger p) {
		return (countPassengers() <= passengerSpace - 1);
	}

	private boolean hasRoomFor(int p) { // Private help function to find out if we have space for P number of people in a vehicle.
		return (countPassengers() + p <= passengerSpace);
	}

	private int actualUsedSpace() { // Get raw used space, where bicycles are worth 1 space.
		int usedSpace = 0;
		for (int i = 0; i < contentsVehicle.size(); i++) {
			usedSpace += contentsVehicle.get(i).getSpace();
		}
		return usedSpace;
	}

	private void addMoney(int m) { // add some ticket money to total
		ticketMoney += m;
	}

	private int calculateMoney(Vehicle v) { // Figures out ticket price for one vehicle, with or without passengers.
		int money = 0;
		if (v instanceof Bicycle) { // Different prices depending on type of vehicle
			money = BICYCLE_AND_PASSENGER_PRICE;
		}
		else if (v instanceof Car) {
			money += CAR_PRICE;
			money += (CAR_PASSENGER_PRICE * v.getNumberPassengers());
		}
		else if (v instanceof Bus) {
			money += BUS_PRICE;
			money += (BUS_PASSENGER_PRICE * v.getNumberPassengers());
		}
		else if (v instanceof Lorry) {
			money += LORRY_PRICE;
			money += LORRY_PASSENGER_PRICE * v.getNumberPassengers();
		}
		return money;
	}

	private int calculateMoney(Passenger p) { // Return ticket price for one passenger.
		return REGULAR_PASSENGER_PRICE;
	}

}

class FerryIterator implements Iterator<Vehicle> { // internal iterator class
	private int nextElement = 0;
	private final ArrayList<Vehicle> elements;
	private final int size;
	
	public FerryIterator(ArrayList<Vehicle> e) {
		elements = e;
		size = e.size();
	}

	@Override
	public boolean hasNext() { // return true if iterator has the next element
		return (nextElement < size);
	}

	@Override
	public Vehicle next() { // return the next element
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		return elements.get(nextElement++);
	}
	
}
