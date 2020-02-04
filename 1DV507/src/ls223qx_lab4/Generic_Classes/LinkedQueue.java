package ls223qx_lab4.Generic_Classes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

	/** The current number of nodes in the element. */
	private int size;

	/** The tail node, which should always point to the last node in the queue. */
	private Node tail; // latest

	/** The head node in the queue. */
	private Node head; // first

	/**
	 * Instantiates a new linked queue.
	 */
	public LinkedQueue() {
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void enqueue(T element) { // put element into the queue
		Node n = new Node(element);
		if (size == 0) { // special case if we're dealing with the first node in the queue, no tail node
							// to modify at first
			head = n;
			tail = n;
			size++;
		}
		else {
			tail.next = n;
			tail = n;
			size++;
		}
	}

	@Override
	public T dequeue() { // remove and return one node value (return element of type T)
		if (!isEmpty()) {
			Node lastHead = head;
			head = lastHead.next;
			size--;
			return lastHead.value;
		}
		else
			throw new NoSuchElementException("Reached end of queue");
	}

	@Override
	public T first() {
		if (!this.isEmpty())
			return head.value;
		else
			throw new NoSuchElementException("Queue is empty!");
	}

	@Override
	public T last() {
		if (!this.isEmpty())
			return tail.value;
		else
			throw new NoSuchElementException("Queue is empty!");
	}

	@Override
	public Iterator<T> iterator() { // return iterator of all nodes
		return new QueueIterator<>(head, size);
	}

	@Override
	public String toString() { // This method will really only work for primitive types.
		Iterator<T> iteratorElements = this.iterator();
		StringBuilder str = new StringBuilder();
		str.append("[ ");
		iteratorElements.forEachRemaining(s -> str.append(s + " "));
		str.append("]");
		return str.toString();
	}

	private class Node {
		// Private inner class, node implementation of a linked collection
		T value;

		Node next = null; // reference to next node

		Node(T v) {
			value = v;
		}
	}

	private class QueueIterator<X> implements Iterator<X> { // Private inner class, returns an iterator with all the
		// elements of the nodes, not the nodes themselves.
		private X[] elements;
		private int size;
		private int iterator = 0;

		@SuppressWarnings("unchecked")
		public QueueIterator(Node node, int nodeSize) {
			size = nodeSize;
			elements = (X[]) new Object[nodeSize]; // Casting to passed type, object is generic so this should always work.

			int iteration = 0;
			while (node != null) { // get elements from all nodes
				elements[iteration++] = (X) node.value; // Passed type will be the same as node stored type, but casting is still required for compiler to not complain.
				node = node.next;
			}
		}

		@Override
		public boolean hasNext() {
			return (iterator < size);
		}

		@Override
		public X next() {
			if (hasNext()) {
				return elements[iterator++];
			}
			else
				throw new NoSuchElementException("No more elements to return!");
		}

	}

}
