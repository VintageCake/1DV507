package ls223qx_lab4.Time_Measuremenets;

public class Exercise2_StringTime {
	public static void main(String[] args) {
		long countTimes = 0;

		long end = System.currentTimeMillis() + 1000;

		String s1 = "";
		while (end > System.currentTimeMillis()) {
			s1 += "s";
			countTimes++;
		}
		System.out.println(countTimes + " Short string +");
		System.out.println(s1.length() + " String length");
		System.out.println();

		countTimes = 0;
		String s2 = "";
		end = System.currentTimeMillis() + 1000;
		while (end > System.currentTimeMillis()) {
			s2 += "lkjtnllkhvopqwefjfpoqwepofjvåpiqwepojlkfjpoqwepojöfklvpihqwelkfjrphqwepolnv.,nas"; // 80 character
																										// string
			countTimes++;
		}
		System.out.println(countTimes + " Long string +");
		System.out.println(s2.length() + " String length");
		System.out.println();
	}

}
