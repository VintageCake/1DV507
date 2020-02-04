package ls223qx_lab4.Time_Measuremenets;

public class Exercise2_StringBuilderTime {
	public static void main(String[] args) {
		Timer t = new Timer();
		t.start();

		long countTimes = 0;
		long end = System.currentTimeMillis() + 920;
		StringBuilder str1 = new StringBuilder();
		while (end > System.currentTimeMillis()) {
			str1.append("s");
			countTimes++;
		}
		long beforeTranslate = System.currentTimeMillis();
		String str1Done = str1.toString();
		t.stop();
		long afterTranslate = System.currentTimeMillis();
		long finalToString = afterTranslate - beforeTranslate;

		System.out.println(countTimes + " Short string append");
		System.out.println(str1Done.length() + " String length");
		System.out.println(finalToString + " Time (ms) for final call");
		System.out.println(t.getElapsed() + " Total time");
		System.out.println();
		
		
		
		t.start();
		countTimes = 0;
		end = System.currentTimeMillis() + 650; // Inconsistent execution time, change this variable and check final execution time.
		StringBuilder str2 = new StringBuilder();
		while (end > System.currentTimeMillis()) {
			str2.append("lkjtnllkhvopqwefjfpoqwepofjvåpiqwepojlkfjpoqwepojöfklvpihqwelkfjrphqwepolnv.,nas");
			countTimes++;
		}
		beforeTranslate = System.currentTimeMillis();
		String str2Done = str2.toString();
		afterTranslate = System.currentTimeMillis();
		finalToString = afterTranslate - beforeTranslate;
		t.stop();

		System.out.println(countTimes + " Long string append");
		System.out.println(str2Done.length() + " String length");
		System.out.println(finalToString + " Time (ms) for final call");
		System.out.println(t.getElapsed() + " Total time");
	}
	private static class Timer {
		private long time = 0;
		private long elapsed = 0;

		public Timer() {

		}

		public void start() {
			time = System.currentTimeMillis();
		}

		public void stop() {
			elapsed = System.currentTimeMillis() - time;
		}

		public long getElapsed() {
			return elapsed;
		}
	}
}
