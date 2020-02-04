package ls223qx_lab1;

import java.util.Arrays;

public class PascalMain {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(pascalRow(1))); // Test
		System.out.println(Arrays.toString(pascalRow(2)));
		System.out.println(Arrays.toString(pascalRow(3)));
		System.out.println(Arrays.toString(pascalRow(4)));
		System.out.println(Arrays.toString(pascalRow(5)));

	}

	public static int[] pascalRow(int n) { // Felt easier to just split method into one regular and one helper method
		if(n==0) {
			n++;
		}
		int[] pascal = new int[n];
		for (int i = 0; i < n; i++) {
				pascal[i] = pascalCompute(n, i); // Build an array with pascals numbers for row number N, get element at position i of the array.
		}
		return pascal;
	}

	private static int pascalCompute(int n, int element) {
		if (element == 0 || element == n-1) {
			return 1;
		}
		else
			return pascalCompute(n - 1, element) + pascalCompute(n - 1, element - 1);
		/* 'Array' shape is like this:
		 * 1
		 * 1, 1
		 * 1, 2, 1
		 * 1, 3, 3, 1
		 * 1, 4, 6, 4, 1
		 * 
		 * To get the next element, look at the previous rows element with the same position, and combine it with the previous rows earlier element.
		 * Ex. Compute the second element in the fourth row.
		 * 
		 * Take the element of the same position of the previous row - this will be the number two.
		 * To actually 'find' that element, we will recursively call this function again until we have a value.
		 * Then add this to the element of the previous row at one index less, this will be the number one.
		 * The result is three.
		 * 
		 * The first and last element of every new row is always 1, which will be the base condition for this recursive function.
		 * 
		 * 
		 */
	}

}
