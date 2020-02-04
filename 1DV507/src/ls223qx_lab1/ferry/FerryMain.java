package ls223qx_lab1.ferry;

import java.util.Iterator;

@SuppressWarnings("unused")
public class FerryMain {
	public static void main(String[] args) {
		Ferry ferry = new FerryTransporter(); 

		ferry.embark(new Bus(6)); // Put a bus containing 6 people on the ferry
		System.out.println(ferry.countPassengers() + " Passengers"); // Display passengers, used vehicle space and how much money earned
		System.out.println(ferry.countVehicleSpace() + " Vehicle space used");
		System.out.println(ferry.countMoney() + " Money earned");
		System.out.println();

		while (ferry.hasSpaceFor(new Bus(6))) { // Put buses containing 6 people until ferry has no more space
			ferry.embark(new Bus(6));
		}

		System.out.println(ferry.countPassengers()); 
		System.out.println(ferry.countVehicleSpace());
		System.out.println(ferry.countMoney());
		System.out.println();

		while (ferry.hasSpaceFor(new Bicycle(1))) { // fill ferry with passengers and bicycles
			ferry.embark(new Bicycle(1));
		}

		System.out.println(ferry.countPassengers() + " Passengers");
		System.out.println(ferry.countVehicleSpace() + " Vehicle space used");
		System.out.println();

		ferry.disembark(); // Clear all
		System.out.println(ferry.countPassengers() + " Passengers");
		System.out.println(ferry.countVehicleSpace() + " Vehicle space used");
		System.out.println(ferry.countMoney());
		System.out.println();

		for (int i = 0; i < 4; i++) { // Fill with some different types of vehicles
			ferry.embark(new Lorry(2));
		}
		for (int i = 0; i < 4; i++) {
			ferry.embark(new Car(3));
		}
		try { // Try to overfill ferry
			for (int i = 0; i < 11; i++) {
				ferry.embark(new Bus(11));
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println(ferry.countPassengers() + " Passengers");
		System.out.println(ferry.countVehicleSpace() + " space used out of 40");
		System.out.println(ferry.countMoney());
		while (ferry.hasSpaceFor(new Lorry(0))) { // Fill vehicle space
			ferry.embark(new Lorry(0));
		}
		while(ferry.hasRoomFor(new Passenger())) { // Fill passenger space
			ferry.embark(new Passenger());
		}
		System.out.println();
		
		System.out.println(ferry.countPassengers() + " Passengers");
		System.out.println(ferry.countVehicleSpace() + " space used out of 40");
		System.out.println(ferry.countMoney());
		
		/*Iterator<Vehicle> vehicles = ferry.iterator(); // Iterator test, gives a lot of output.
		while (vehicles.hasNext()) {
			System.out.println(vehicles.next().toString());
		}
		*/
		ferry.disembark();

	}
}
