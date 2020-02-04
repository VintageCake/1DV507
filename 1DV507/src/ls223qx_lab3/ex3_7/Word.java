package ls223qx_lab3.ex3_7;

public class Word implements Comparable<Word> {
	private String word;
	private int hash;

	public Word(String str) {
		word = str.toLowerCase(); // Solving equality by simply making constructor do a toLower.
		hash = computeHash(word);
	}

	public String toString() {
		return word;
	}

	public static int computeHash(String str) { // Can provide hash for any string, also used in other classes.
		int h = str.toLowerCase().hashCode(); // Make hashing non-case sensitive
		if (h < 0)
			h = -h;
		return h;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Word) {
			return this.hashCode() == other.hashCode();

		}
		else if (other instanceof String) {
			return computeHash(other.toString()) == this.hashCode();
		}
		else
			return false;
	}

	@Override
	public int compareTo(Word w) {
		return word.compareTo(w.toString());
	}
}
