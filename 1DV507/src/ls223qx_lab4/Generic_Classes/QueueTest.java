package ls223qx_lab4.Generic_Classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * JUnit test that works on different implementations of a Queue.
 * 
 * An object call needs to be changed in makeNew() for other implementations of Queue.
 * 
 */
@SuppressWarnings("unused")
class QueueTest {

	@Test
	void testEmptyQueue() {
		Queue<Integer> q1 = build(0); // test empty
		assertEquals(0, q1.size());
		assertThrows(NoSuchElementException.class, () -> q1.dequeue());
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}
	@Test
	void testEmptyQueueString() { // Tests an empty string queue.
		Queue<String> q1 = new LinkedQueue<>();
		assertEquals(0, q1.size());
		assertThrows(NoSuchElementException.class, () -> q1.dequeue());
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}

	@Test
	void testSize() {
		Queue<Integer> q2 = build(1000000); // test extreme case of very full queue
		assertEquals(1000000, q2.size());
		q2.dequeue();
		q2.dequeue();
		q2.dequeue();
		assertEquals(1000000 - 3, q2.size()); // remove 3, check size
		q2.enqueue(1);
		q2.enqueue(1);
		q2.enqueue(1);
		assertEquals(1000000, q2.size()); // add 3 generic, check size
		while (!q2.isEmpty()) {
			q2.dequeue();
		}
		assertEquals(0, q2.size());
	}
	@Test
	void testSizeString() { // Test size function, with strings.
		Queue<String> q = new LinkedQueue<>();
		int times = 1000000; // Elements to put in
		for (int i = 0; i < times; i++) {
			q.enqueue("....");
		}
		assertEquals(times, q.size());
		q.dequeue();
		assertEquals(times-1, q.size());
		q.dequeue();
		assertEquals(times-2, q.size());
		
		q.enqueue("thing");
		assertEquals(times-1, q.size());
		q.enqueue("thing");
		assertEquals(times, q.size());
		
		while(!q.isEmpty()) {
			q.dequeue();
		}
		assertEquals(0, q.size());
		
	}

	@Test
	void testEnqueue() { 
		Queue<Integer> q1 = build(100); // in an array queue, length is 128 (base_length*2*x)
		for (int i = 0; i < 50; i++) { // remove 0-49 from queue
			q1.dequeue();
		}
		for (int i = 100; i < 150; i++) { // add 100-149 to queue, an arrayQueue should become circular
			q1.enqueue(i);
		}

		assertEquals((int) q1.first(), 50); // check for expected values
		assertEquals((int) q1.last(), 149);
		for (int i = 50; i < 150; i++) {
			assertEquals((int) q1.dequeue(), i);
		}

		Queue<Integer> q2 = build(7); // check edge case where last_empty in ArrayQueue has looped around, but the
										// last element has not.
		q2.dequeue();
		q2.enqueue(100);
		assertEquals((int) q2.last(), 100);
		assertEquals((int) q2.first(), 1);
	}
	
	@Test
	void testEnqueueString() { // Basic assertion that content that comes out of the queue is expected.
		Queue<String> q = new LinkedQueue<>();
		String[] x = {"First", "A", "B", "C", "Last"};
		for (String t : x) {
			q.enqueue(t);
		}
		for (String t : x) {
			assertEquals(q.dequeue(), t);
		}
	}

	@Test
	void testIterator() { // Check that iterator returns elements in expected order and expected amount by
							// validating against the functionality of the list itself.
		int times = 10000;
		Queue<Integer> q1 = build(times);
		Iterator<Integer> iter = q1.iterator();
		for (int i = 0; i < times; i++) {
			assertEquals(iter.next(), q1.dequeue());
		}
	}

	@Test
	void testFirstLast() {
		Queue<Integer> q1 = build(10, 15, 9);
		assertEquals((int) q1.last(), 9); // check first and last element
		assertEquals((int) q1.first(), 15);

		for (int i = 0; i < 9; i++) { // remove 9 elements, making the first and last element have the same position
			q1.dequeue();
		}
		assertEquals(q1.last(), q1.first());
		q1.dequeue();
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}
	@Test
	void testFirstLastString() {
		Queue<String> q1 = new LinkedQueue<>();
		q1.enqueue("First");
		q1.enqueue("A");
		q1.enqueue("B");
		q1.enqueue("C");
		q1.enqueue("Last");
		
		assertEquals(q1.last(), "Last"); // check first and last element
		assertEquals(q1.first(), "First");

		for (int i = 0; i < 4; i++) { // Remove four elements, make first and last point to the same element
			q1.dequeue();
		}
		assertEquals(q1.last(), q1.first());
		q1.dequeue();
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}
	
	@Test	
	void testIteratorString() {
		Queue<String> q1 = new LinkedQueue<>();
		String[] x = {"First", "A", "B", "C", "Last"};
		for (String t : x) {
			q1.enqueue(t);
		}
		Iterator<String> it = q1.iterator();
		for (String t : x) {
			assertEquals(q1.dequeue(), it.next());
		}
		
	}

	private Queue<Integer> build(int length) {
		Queue<Integer> q = new LinkedQueue<>(); // Can be changed for other concrete implementations!
		for (int i = 0; i < length; i++) { // fill with dummy objects
			q.enqueue(i);
		}
		return q;
	}

	private Queue<Integer> build(int length, int first, int last) { // overloaded method for setting first and last elements
		Queue<Integer> q = new LinkedQueue<>(); // Can be changed for other concrete implementations!
		q.enqueue(first);
		for (int i = 0; i < length - 2; i++) {
			q.enqueue(i);
		}
		q.enqueue(last);
		return q;
	}

}