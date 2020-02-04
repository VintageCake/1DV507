package ls223qx_lab1.ferry;

public class Bus extends Vehicle {
	private static final int SPACE_REQUIRED = 4 * 5;
	private static final int MAX_PASSENGERS = 20;

	protected Bus(int pass) {
		super(pass, SPACE_REQUIRED, MAX_PASSENGERS);
	}

}
