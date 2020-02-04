package ls223qx_lab3.ex2;

import java.util.Comparator;

public class SortingAlgorithms {

	private SortingAlgorithms() { // Makes sure this class can never be used as an object
		throw new IllegalStateException("Utility class");
	}

	public static int[] insertionSort(int[] in) {
		if (in.length <= 1) // No need to sort arrays with 1 element
			return in;

		int[] val = new int[in.length]; // Isolate passed array from array to be modified.
		System.arraycopy(in, 0, val, 0, in.length);

		for (int i = 0; i < val.length - 1; i++) {
			int j = i;
			boolean finished = false;

			while (!finished) {
				/*
				 * Swap selected element, moving it 'backwards' into the array until - either
				 * the end of the array is reached or an element of lesser value is found.
				 */
				if (val[j + 1] < val[j]) {
					int hold = val[j + 1];

					val[j + 1] = val[j];
					val[j] = hold;
					if (j != 0)
						j--;
				}
				else {
					finished = true;
				}
			}
		}

		return val;
	}

	public static int[] mergeSort(int[] in) {
		if (in.length <= 1) // We don't have to sort empty or arrays with 1 element.
			return in;

		int[] hold = new int[in.length]; // Isolate passed array from methods by copying into a new one.
		System.arraycopy(in, 0, hold, 0, in.length);

		mergeSort(hold, hold.length);
		return hold;
	}

	private static void mergeSort(int[] passedArray, int length) { // Most of the merge sort implementation is from
																	// https://www.baeldung.com/java-merge-sort
		if (length < 2) { // Base condition, signals to stop splitting
			return;
		}
		int mid = length / 2; // Find rough middle of array

		int[] left = new int[mid]; // Create two arrays, will hold values right and left sides of a split input
									// array
		int[] right = new int[length - mid];

		for (int i = 0; i < mid; i++) { // Copy left and right sides of input array into respective "left" and "right"
										// arrays.
			left[i] = passedArray[i];
		}
		for (int i = mid; i < length; i++) {
			right[i - mid] = passedArray[i];
		}

		mergeSort(left, mid); // Recursively split the left array
		mergeSort(right, length - mid); // Recursively split the right array
		// Splitting stops after base condition is met. Merging is then started.

		merge(passedArray, left, right, mid, length - mid);

	}

	private static void merge(int[] passedArray, int[] leftArray, int[] rightArray, int leftMax, int rightMax) {
		int i = 0, j = 0, k = 0;
		/*
		 * Merge left and right, not by replacing the original array with a new one, but
		 * instead by overwriting the contents of the passed array with the contents of
		 * left and right.
		 */
		while (i < leftMax && j < rightMax) { // Condition prevents out of bounds access
			if (leftArray[i] <= rightArray[j]) {
				passedArray[k++] = leftArray[i++];
			}
			else {
				passedArray[k++] = rightArray[j++];
			}
		}
		while (i < leftMax) { // If one of the arrays are 'done', the rest of the other array can safely be
								// added onto it while still yielding a sorted result.
			passedArray[k++] = leftArray[i++];
		}
		while (j < rightMax) {
			passedArray[k++] = rightArray[j++];
		}
	}

	public static String[] insertionSort(String[] in, Comparator<String> c) {
		if (in.length <= 0)
			return in;

		String[] val = new String[in.length];
		System.arraycopy(in, 0, val, 0, in.length);

		for (int i = 0; i < val.length - 1; i++) {
			int j = i;
			boolean finished = false;

			while (!finished) {
				/*
				 * Swap selected element
				 */
				if (c.compare(val[j + 1], val[j]) < 0) { // String version uses a comparator
					String hold = val[j + 1];
					val[j + 1] = val[j];
					val[j] = hold;
					if (j != 0)
						j--;
				}
				else {
					finished = true;
				}
			}
		}

		return val;
	}

	public static String[] mergeSort(String[] in, Comparator<String> c) {
		if (in.length <= 1)
			return in;

		String[] hold = new String[in.length];
		System.arraycopy(in, 0, hold, 0, in.length);

		mergeSortString(hold, hold.length, c);
		return hold;
	}

	private static void mergeSortString(String[] passedArray, int length, Comparator<String> c) {
		if (length < 2) { // Base condition, signals to stop splitting
			return;
		}
		int mid = length / 2; // Find rough middle of array

		String[] left = new String[mid]; // Create two arrays, will hold values right and left sides of a split input
											// array
		String[] right = new String[length - mid];

		for (int i = 0; i < mid; i++) { // Copy left and right sides of input array into respective "left" and "right"
										// arrays.
			left[i] = passedArray[i];
		}
		for (int i = mid; i < length; i++) {
			right[i - mid] = passedArray[i];
		}

		mergeSortString(left, mid, c); // Recursively split the left array
		mergeSortString(right, length - mid, c); // Recursively split the right array
		// Splitting stops after base condition is met. Merging is then started.

		mergeString(passedArray, left, right, mid, length - mid, c);
	}

	private static void mergeString(String[] passedArray, String[] leftArray, String[] rightArray, int leftMax,
			int rightMax, Comparator<String> c) {
		int i = 0, j = 0, k = 0;
		/*
		 * Merge left and right, not by replacing the original array with a new one, but
		 * instead by overwriting the contents of the passed array with the contents of
		 * left and right.
		 * 
		 * The string version uses a comparator expression to compare strings.
		 */
		while (i < leftMax && j < rightMax) { // Condition prevents out of bounds access
			if (c.compare(leftArray[i], rightArray[j]) < 0) {
				passedArray[k++] = leftArray[i++];
			}
			else {
				passedArray[k++] = rightArray[j++];
			}
		}
		while (i < leftMax) { // If one of the arrays are 'done', the rest of the other array can safely be
								// added onto it while still yielding a sorted result.
			passedArray[k++] = leftArray[i++];
		}
		while (j < rightMax) {
			passedArray[k++] = rightArray[j++];
		}
	}

}
