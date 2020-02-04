package ls223qx_lab3;

import java.util.Arrays;
import java.util.Scanner;

public class EuclideanMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] numbers = new int[2];

		int input = 0; // External iterator
		while (input != 2) { // Loop for input handling
			
			if (input == 0) // User prompts
				System.out.print("Input first number: ");
			else
				System.out.print("Input second number: ");
			
			if (sc.hasNextInt()) {  // If number is found, put it into array
				int hold = sc.nextInt();
				if (hold >= 0) {
					numbers[input] = hold;
					input++;
				}

			}
			else { // If character input wasn't able to be parsed into an integer, program will exit.
				System.out.println("Not a valid number");
				System.exit(-1);
			}
		}
		
		sc.close();
		Arrays.sort(numbers);

		System.out.println("Greatest common divisor is: " + returnGCD(numbers[0], numbers[1]));

	}

	public static int returnGCD(int a, int b) {
		while (b != 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

}
