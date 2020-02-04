package test;

public class multiply {
	public static void main(String[] args) {
		System.out.println(mul(5,100));
	}
	private static int mul(int a, int b) {
		if (b == 0) {
			return 0;
		}
		else 
			return mul(a, b-1) +a;
	}

}
