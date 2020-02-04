package ls223qx_lab1;

public class PrintRecursive {
	public static void main(String[] args) {
		String str = "Hello Everyone!";

		print(str, 0);
		System.out.println(); // Line break
		printReverse(str, 0);

	}

	public static void print(String a, int b) {
		if (b < a.length()) { // If valid index, print the element from respective index position
			System.out.print(a.charAt(b));
			print(a, b + 1); // recursively call method again with the next index.
		}
	}

	public static void printReverse(String a, int b) {
		if (b < a.length()) {
			printReverse(a, b + 1); // Call next method right away, means that the rest of the method won't be executed until the base condition isn't met. 
			System.out.print(a.charAt(b)); // This part will first be executed on the very last method call, meaning it'll be the last index of the string -- result will be backwards.
		}
	}

}
