package ls223qx_lab3.ex3_7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeWordSet implements WordSet {
	private binaryWord root = null;
	private int size;

	@Override
	public Iterator<Word> iterator() {
		return new HashTreeIterator(size);
	}

	@Override
	public void add(Word word) { // Checks if word is present before doing an add (will break performance somewhat, but this doesn't break size)
		// Better implementation is making binaryWord.add a boolean that returns true if element was added, false if not.
		if ((root != null) && root.contains(word)) {
			return;
		}
		else if (root == null) {
			root = new binaryWord(word);
		}
		else {
			root.add(word);
		}
		size++;
	}

	@Override
	public boolean contains(Word word) {
		if (size != 0) {
			return root.contains(word);
		}
		else
			return false;
	}

	@Override
	public int size() {
		return size;
	}

	private class binaryWord { // Contains a word and two 'next' values (left,right).
		Word value;
		binaryWord left = null;
		binaryWord right = null;

		binaryWord(Word w) {
			value = w;
		}

		public String toString() {
			return value.toString();
		}
		

		void add(Word w) { // Recursively find a spot for the word, if compareTo function returns 0 (same element), nothing will be added.
			if (w.toString().compareTo(value.toString()) < 0) {
				if (left == null) {
					left = new binaryWord(w);
				}
				else {
					left.add(w);
				}
			}
			else if (w.toString().compareTo(value.toString()) > 0) {
				if (right == null) {
					right = new binaryWord(w);
				}
				else {
					right.add(w);
				}
			}
		}

		boolean contains(Word w) {  // Recursively look for a word in the tree
			if (w.toString().compareTo(value.toString()) < 0) {
				if (left == null) {
					return false;
				}
				else {
					return left.contains(w);
				}
			}
			else if (w.toString().compareTo(value.toString()) > 0) {
				if (right == null) {
					return false;
				}
				else {
					return right.contains(w);
				}
			}
			return true;
		}
	}

	private class HashTreeIterator implements Iterator<Word> {
		private Word[] l;
		private binaryWord tree = root;
		private int size;
		private int creationIteration = 0;
		private int iterator = 0;

		HashTreeIterator(int passedSize) {
			l = new Word[passedSize];
			getElements(tree);
			size = l.length;
			
		}
		private void getElements(binaryWord x) { // Recursively go through the binary tree, left-to-right in-order, elements added to a list in a sorted order.
			if (x.left != null) {
				getElements(x.left);
			}
			l[creationIteration++] = x.value;
			if (x.right != null) {
				getElements(x.right);
			}
		}

		@Override
		public boolean hasNext() {
			return (iterator < size);
		}

		@Override
		public Word next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			return l[iterator++];
		}
	}

}
