package ls223qx_lab1.ferry;

public class Lorry extends Vehicle {
	private static final int SPACE_REQUIRED = 1*5; 
	private static final int MAX_PASSENGERS = 4;
	
	public Lorry(int pass) {
		super(pass, SPACE_REQUIRED, MAX_PASSENGERS);
	}

}
