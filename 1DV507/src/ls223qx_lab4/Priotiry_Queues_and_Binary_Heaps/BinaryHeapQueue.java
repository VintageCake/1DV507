package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

import java.util.NoSuchElementException;

public class BinaryHeapQueue<T> implements PriorityQueue<T> {
	private Task<T>[] tasks;
	private int size;
	private int currentElement;
	private int root = 1;
	
	// Parent = current/2
	// left child = 2 * current
	// right child = 2 * current+1
	
	@SuppressWarnings("unchecked")
	public BinaryHeapQueue() {
		size = 0;
		currentElement = 1;
		tasks = (Task<T>[]) new Task[8]; // Can't cast from Object[] to Task<>[], needs to be a raw task array first.
	}
	@SuppressWarnings("unchecked")
	private void resize() {
		Task<T>[] hold = (Task<T>[]) new Task[tasks.length * 2];
		System.arraycopy(tasks, 0, hold, 0, tasks.length);
		tasks = hold;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Task<T> t) {
		for (Task<T> x : tasks) {
			if (x != null && t.equals(x)) {
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void insert(Task<T> element) {
		if (size == tasks.length - 1) {
			resize();
		}
		tasks[currentElement] = element;
		percolateUp(currentElement);
		currentElement++;
		size++;
	}
	

	@Override
	public Task<T> pullHighest() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Task<T> rootValue = tasks[root];
		
		tasks[root] = tasks[currentElement-1];
		tasks[currentElement--] = null; // Debug visibility
		
		percolateDown(root);
		size--;
		return rootValue;
	}
	private void swap(int pos1, int pos2) { // Simply swaps two elements in an array
		Task<T> hold = tasks[pos1];
		tasks[pos1] = tasks[pos2];
		tasks[pos2] = hold;
	}
	private void percolateUp(int position) { // Recursive method, push higher values up.
		int parentPosition = position / 2;
		
		if (parentPosition == 0) {
			return;
		}
		int currentPriority = tasks[position].getPriority();
		int parentPriority = tasks[parentPosition].getPriority();

		if (parentPriority < currentPriority) {
			swap(position, parentPosition);
			percolateUp(parentPosition);
		}
	}
	private void percolateDown(int position) { // Recursive method, pushes lower values down.
		int left = 2*position;
		int right = 2*position+1;
		if (left > size || right > size) {
			return;
		}
		
		int currentValue = tasks[position].getPriority();
		int leftValue = tasks[left].getPriority();
		int rightValue = tasks[right].getPriority();
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

	@Override
	public Task<T> peekHighest() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return tasks[root];
		}
	}

}
