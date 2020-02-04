package ls223qx_lab4.Time_Measuremenets;

import java.util.Random;

public class Exercise3_SortingTime {
	public static void main(String[] args) {
		Timer t = new Timer();
		Random r = new Random();
		long[] time = new long[5];
		
		int sortSize = (int) (1.6 * 10000000);

		for (int i = 0; i < 5; i++) {
			int[] x = new int[sortSize];

			for (int j = 0; j < 1000; j++) {
				x[j] = r.nextInt(sortSize * 2);
			}

			t.start();
			SortingAlgorithms.mergeSort(x);
			t.stop();

			time[i] = t.getElapsed();
		}
		
		long timeAverage = 0;

		for (int i = 0; i < 5; i++) {
			timeAverage += time[i];
		}
		timeAverage = timeAverage / 5;
		double memoryKB = (32 + 4 * sortSize) / 1024d;
		double memoryMB = memoryKB / 1024d;

		System.out.println(memoryKB + " KB");
		System.out.println(memoryMB + " MB");
		System.out.println(sortSize + " array length");
		System.out.println(timeAverage + " Time (ms) average");

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
