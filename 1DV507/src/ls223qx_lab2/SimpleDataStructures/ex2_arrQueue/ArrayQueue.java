package ls223qx_lab2.SimpleDataStructures.ex2_arrQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ls223qx_lab2.SimpleDataStructures.ex1_queue.Queue;

public class ArrayQueue implements Queue {
	private Object[] queue = new Object[8];
	private int last_empty = 0; // this last doesn't actually point to the last element, instead it points to
								// last+1 which is treated as an empty space.
	private int first = 0; // first will point to the first element
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void enqueue(Object element) { // put stuff in queue, resize if queue is too small
		if (queue.length - 1 == size) {
			queue = resize();
		}
		queue[last_empty] = element;
		iterareLast();
		size++;

	}

	private void iterareLast() { 
		// iterates the last_empty variable, handles the case when reached the end of the array
		if (last_empty == queue.length - 1) {
			last_empty = 0;
		}
		else
			last_empty++;
	}

	@Override
	public Object dequeue() { // 'remove' one element and return it
		if (!this.isEmpty()) {
			Object hold = queue[first];
			// queue[first] = null; // only for testing, makes it really clear what is
			// actually 'removed' in variable debug
			iterateFirst();
			size--;
			return hold;
		}
		else
			throw new NoSuchElementException();
	}

	private void iterateFirst() { // iterates the first variable, handles the case when we reach the end of the
									// array
		if (first == queue.length - 1) { // loop around at max index
			first = 0;
		}
		else
			first++;
	}

	@Override
	public Object first() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		else
			return queue[first];
	}

	@Override
	public Object last() { // returns the last element, which will be last_empty-1 unless no elements are
							// present in the list.
		if (size == 0) {
			throw new NoSuchElementException();
		}
		else if (last_empty == 0) { // edge case when last_empty has looped around the array, while the element we
									// actually want is before the loop.
			return queue[queue.length - 1];
		}
		else if (queue[last_empty - 1] != null)
			return queue[last_empty - 1];
		else
			throw new NoSuchElementException();
	}

	@Override
	public Iterator<Object> iterator() {
		return new QueueIterator();
	}

	@Override
	public String toString() { // may not work all that well for anything other than primitive data types
		Iterator<Object> iteratorElements = this.iterator();
		StringBuilder str = new StringBuilder();
		str.append("[ ");
		iteratorElements.forEachRemaining(s -> str.append(s + " "));
		str.append("]");
		return str.toString();
	}

	private Object[] resize() { // reorders and resizes the array
		Object[] hold = new Object[queue.length * 2];

		if (last_empty < first) { // condition only true if array has turned circular
			int counter = 0;
			for (int i = first, j = 0; i < queue.length; i++, j++) { // get all elements from first to end of array
				hold[j] = queue[i];
				counter++;
			}
			for (int i = 0, j = counter; i < last_empty; i++, j++) { // get all elements that 'continues' from last
																		// iteration
				hold[j] = queue[i];
				counter++;
			}
			first = 0;
			last_empty = counter;
		}
		else {
			System.arraycopy(queue, first, hold, 0, last_empty);
		}
		return hold;
	}

	private class QueueIterator implements Iterator<Object> {
		private Object[] elements;
		private int size;
		private int iterator = 0;

		public QueueIterator() {
			size = size();
			elements = getOrderedQueue();
		}

		@Override
		public boolean hasNext() {
			return (iterator < size);
		}

		@Override
		public Object next() {
			if (hasNext()) {
				return elements[iterator++];
			}
			else
				throw new NoSuchElementException("No more elements to return!");
		}

		private Object[] getOrderedQueue() { // turns the circular array into a linear one, retaining order.
			Object[] hold = new Object[queue.length];
			if (last_empty < first) { // only do this part if array is a circular array
				int counter = 0;
				for (int i = first, j = 0; i < queue.length; i++, j++) { // get all elements from first to end of array
					hold[j] = queue[i];
					counter++;
				}
				for (int i = 0, j = counter; i < last_empty; i++, j++) { // get all elements that 'continues' from last
																			// iteration
					hold[j] = queue[i];
					counter++;
				}
			}
			else {
				System.arraycopy(queue, first, hold, 0, last_empty);
			}
			return hold;
		}

	}
}
