package ls223qx_lab3.ex8_10;

public class NorseGod {
	private String name;
	private String race;
	private String description;
	
	public NorseGod() {
		
	}
	public NorseGod(String n, String r, String d) {
		name = n;
		race = r;
		description = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String r) {
		race = r;
	}
	public String getDesc() {
		return description;
	}
	public void setDesc(String d) {
		description = d;
	}

}
