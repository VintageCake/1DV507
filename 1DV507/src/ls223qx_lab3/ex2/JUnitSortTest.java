package ls223qx_lab3.ex2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class JUnitSortTest {

	@Test
	void testIntInsertionSort() {
		int[] x = new int[100];
		for (int i = 0, j = x.length - 1; i < x.length; i++, j--) { // Fill with integers in reverse order
			x[i] = j;
		}
		x = SortingAlgorithms.insertionSort(x);
		for (int i = 0; i < x.length; i++) {
			assertEquals(x[i], i);
		}

		int[] one = { 1 };
		int[] oneSorted = SortingAlgorithms.insertionSort(one);
		assertTrue(Arrays.equals(one, oneSorted));
	}

	@Test
	void testIntInstertionRandom() {
		Random rand = new Random();
		int[] x = new int[100];
		for (int i = 0; i < x.length; i++) { // Fill with integers of random value, 0 is bottom
			x[i] = rand.nextInt(100);
		}

		x = SortingAlgorithms.insertionSort(x);
		int prev = 0;
		for (int i = 0; i < x.length; i++) { // Assert that all values are either increasing or same as previous
			assertTrue(x[i] >= prev);
		}

	}

	@Test
	void testIntEvenMergeSort() { // Array with even numbers
		int[] x = { 1, 9, 3, 5, 8, 7, 4, 0, 2, 6 };
		x = SortingAlgorithms.mergeSort(x); // Order correctly
		for (int i = 0; i < x.length; i++) { // Validate content
			assertEquals(x[i], i);
		}

	}

	@Test
	void testIntOddMergeSort() { // Array with odd numbers
		int[] x = { 1, 9, 3, 5, 8, 7, 4, 0, 2, 6, 10 };
		x = SortingAlgorithms.mergeSort(x); // Order correctly
		for (int i = 0; i < x.length; i++) { // Validate content
			assertEquals(x[i], i);
		}
	}

	@Test
	void testIntSingleMergeSort() { // Handling of single array
		int[] one = { 1 };
		int[] oneSorted = SortingAlgorithms.mergeSort(one);
		assertTrue(Arrays.equals(one, oneSorted));
	}

	@Test
	void testStringArrayInsertionSort() {
		String[] x = { "XYZ", "ABC", "CDE", "XYZ", "ABC" };
		String[] y = { "ABC", "ABC", "CDE", "XYZ", "XYZ" }; // Expected output.
		x = SortingAlgorithms.insertionSort(x, (s1, s2) -> s1.compareTo(s2));
		assertTrue(Arrays.equals(x, y));

		String[] one = { "ABC" }; // Test case where only one element is present.
		String[] oneSorted = SortingAlgorithms.insertionSort(one, (s1, s2) -> s1.compareTo(s2));
		assertTrue(Arrays.equals(one, oneSorted));

	}

	@Test
	void testStringArrayMergeSort() {
		String[] x = { "XYZ", "ABC", "CDE", "XYZ", "ABC" };
		String[] y = { "ABC", "ABC", "CDE", "XYZ", "XYZ" };
		x = SortingAlgorithms.mergeSort(x, (s1, s2) -> s1.compareTo(s2));
		assertTrue(Arrays.equals(x, y));

		String[] one = { "ABC" };
		String[] oneSorted = SortingAlgorithms.mergeSort(one, (s1, s2) -> s1.compareTo(s2));
		assertTrue(Arrays.equals(one, oneSorted));

	}
}
