package ls223qx_lab1.ferry;

public class Bicycle extends Vehicle {
	private static final int SPACE_REQUIRED = 1; // 1/5 * 5, base unit used is 1 instead of a float of 1/5
	private static final int MAX_PASSENGERS = 1; // defining maximum number of passengers
	
	public Bicycle(int pass) {
		super(pass, SPACE_REQUIRED, MAX_PASSENGERS); // passing parameters to superclass
	}
	
}
