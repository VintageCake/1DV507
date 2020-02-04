package ls223qx_lab2.SimpleDataStructures.ex1_queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Class LinkedQueue.
 * 
 * A linked implementation of a Queue using nodes.
 */
public class LinkedQueue implements Queue {

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
	public void enqueue(Object element) { // put element into the queue
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
	public Object dequeue() { // remove and return one node value
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
	public Object first() {
		if (!this.isEmpty())
			return head.value;
		else
			throw new NoSuchElementException("Queue is empty!");
	}

	@Override
	public Object last() {
		if (!this.isEmpty())
			return tail.value;
		else
			throw new NoSuchElementException("Queue is empty!");
	}

	@Override
	public Iterator<Object> iterator() { // return iterator of node
		return new QueueIterator(head, size);
	}

	@Override
	public String toString() { // This method will really only work for primitive types.
		Iterator<Object> iteratorElements = this.iterator();
		StringBuilder str = new StringBuilder();
		str.append("[ ");
		iteratorElements.forEachRemaining(s -> str.append(s + " "));
		str.append("]");
		return str.toString();
	}

	private class Node {
		// Private inner class, node implementation of a linked collection
		Object value;

		Node next = null; // reference to next node

		Node(Object v) {
			value = v;
		}
	}

	private class QueueIterator implements Iterator<Object> { // Private inner class, returns an iterator with all the
		// elements of the nodes, not the nodes themselves.
		private Object[] elements;
		private int size;
		private int iterator = 0;

		public QueueIterator(Node node, int nodeSize) {
			size = nodeSize;
			elements = new Object[nodeSize];

			int iteration = 0;
			while (node != null) { // get elements from all nodes
				elements[iteration++] = node.value;
				node = node.next;
			}
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

	}
}
