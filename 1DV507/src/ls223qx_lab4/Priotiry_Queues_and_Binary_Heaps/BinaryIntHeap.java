package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

import java.util.NoSuchElementException;

public class BinaryIntHeap {

	private int[] elements;
	private int size;
	private int currentElement;
	private int root = 1;

	// Parent = current/2
	// left child = 2 * current
	// right child = 2 * current+1
	
	// The instructions for how to do this came from the 2016 lecture recording.

	public BinaryIntHeap() {
		elements = new int[8];
		size = 0;
		currentElement = 1;
	}

	public void insert(int n) {
		if (size == elements.length - 1) {
			resize();
		}
		elements[currentElement] = n;
		percolateUp(currentElement);
		currentElement++;
		size++;

	}

	private void percolateUp(int position) { // Recursive method, push higher values up.
		int currentValue = elements[position];
		int parentPosition = position / 2;
		
		if (parentPosition == 0) {
			return;
		}
		
		int parentValue = elements[parentPosition];

		if (parentValue < currentValue) {
			swap(position, parentPosition);
			percolateUp(parentPosition);
		}
	}
	
	private void percolateDown(int position) { // Recursive method, pushes lower values down.
		int left = 2*position;
		int right = 2*position+1;
		if (left > elements.length-1 || right > elements.length-1) {
			return;
		}
		
		int currentValue = elements[position];
		int leftValue = elements[left];
		int rightValue = elements[right];
		if (leftValue > rightValue) {
			if (currentValue < leftValue) {
				swap(left, position);
				percolateDown(left);
			}
		}
		else {
			if (currentValue < rightValue) {
				swap(right, position);
				percolateDown(right);
			}
		}
		
		
	}

	private void swap(int pos1, int pos2) { // Simply swaps two elements in an array
		int hold = elements[pos1];
		elements[pos1] = elements[pos2];
		elements[pos2] = hold;
	}

	private void resize() {
		int[] hold = new int[elements.length * 2];
		System.arraycopy(elements, 0, hold, 0, elements.length);
		elements = hold;
	}

	public int pullHighest() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int rootValue = elements[root];
		
		elements[root] = elements[currentElement];
		elements[currentElement--] = 0; // Debug visibility
		
		percolateDown(root);
		size--;
		return rootValue;

	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

}
