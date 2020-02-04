package ls223qx_lab3.ex3_7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashWordSet implements WordSet {
	private int size;
	private Node[] buckets = new Node[8];

	@Override
	public Iterator<Word> iterator() {
		return new HashSetIterator(size);
	}

	@Override
	public void add(Word word) { // Implementation is primarily taken from lecture video 8.
		int bucketIndex = getBucketN(word);
		
		Node n = buckets[bucketIndex];
		while (n != null) {
			if (n.value.hashCode() == word.hashCode()) {
				return; // Do nothing, duplicate element
			}
			else 
				n = n.next;
		}
		n = new Node(word);
		n.next = buckets[bucketIndex]; // Will do nothing for the first node, will attach node to the chain when a node is already present.
		buckets[bucketIndex] = n; // Put node in bucket
		/*
		 * I was a bit taken aback by this implementation at first, but instead of adding the node onto the chain normally -
		 * you actually push the other nodes backwards, placing this one in front (kind of like a queue)
		 * 
		 */
		size++;
		
		if (size == buckets.length) { // Rehash (resize) if limit of buckets is reached.
			rehash();
		}

	}

	@Override
	public boolean contains(Word word) {
		int bucketIndex = getBucketN(word); // Get correct bucket index
		if (buckets[bucketIndex] == null) // If that index is empty, then element does not exist in set.
			return false;

		Node n = buckets[bucketIndex];
		
		boolean finished = false;
		while (!finished) { // Iterate through the bucket until element is found, or we run out of nodes.
			if (word.hashCode() == n.value.hashCode()) {
				return true;
			}
			
			if (n.next != null) {
				n = n.next;
			}
			else {
				finished = true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	private void rehash() { // Also taken from lecture 8
		Node[] temp = buckets;
		buckets = new Node[2*temp.length];
		size = 0;
		
		for (Node n : temp) { // Loop through the old bucket list
			if (n == null) // Skip if empty element
				continue;
			while (n != null) { // Add all the old nodes into the new bucket.
				add(n.value);
				n = n.next;
			}
		}

	}

	private int getBucketN(Word w) {
		int hashCode = w.hashCode();
		if (hashCode < 0)
			hashCode = -hashCode;
		return hashCode % buckets.length; // Bucket number will always be 0-bucket.length-1 using this expression
	}

	private class Node { // Linked node, holds the value and the next node.
		Word value;
		Node next = null;

		public Node(Word w) {
			value = w;
		}

		public String toString() {
			return value.toString();
		}
	}
	
	private class HashSetIterator implements Iterator<Word> {
		private Word[] l;
		private int size = 0;
		private int iterator = 0;
		
		public HashSetIterator(int passedSize) {
			int internalIterator = 0;
			l = new Word[passedSize];
			for (Node n : buckets) { // Reusing the rehash implementation, copies all words in the hash
				if (n == null) 
					continue;
				while (n != null) { 
					l[internalIterator++] = n.value;
					n = n.next;
					size++;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return iterator < size;
		}

		@Override
		public Word next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return l[iterator++];
		}
		
	}

}
