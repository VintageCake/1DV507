package ls223qx_lab3.ex3_7;

public class WordTest {
	public static void main(String[] args) { // Simple test of the word class, not part of any ex.
		Word w = new Word("Test");
		Word w2 = new Word("TEST");
		Word wEx = new Word("ABCDEF");
		String w3 = "Test";
		String wNot = "EX";
		
		System.out.println(w.hashCode());
		if (w.equals(w2))
			System.out.println(w2.hashCode());
		
		if (w2.equals(w3)) {
			System.out.println("this works");
		}
		if (!w2.equals(wNot)) {
			System.out.println("this also works");
		}
		System.out.println(wEx.hashCode());
	}

}
