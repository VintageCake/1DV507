package ls223qx_lab2.JUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ls223qx_lab2.SimpleDataStructures.ex1_queue.LinkedQueue;
import ls223qx_lab2.SimpleDataStructures.ex1_queue.Queue;
import ls223qx_lab2.SimpleDataStructures.ex2_arrQueue.ArrayQueue;

/*
 * JUnit test that works on different implementations of a Queue.
 * 
 * An object call needs to be changed in makeNew() for other implementations of Queue.
 * 
 */
@SuppressWarnings("unused")
class QueueTest {

	@BeforeEach
	void setUp() {
	}
	
	@Test
	void testEmptyQueue() {
		Queue q1 = build(0); // test empty
		assertEquals(0, q1.size());
		assertThrows(NoSuchElementException.class, () -> q1.dequeue());
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}

	@Test
	void testSize() {
		Queue q2 = build(1000000); // test extreme case of very full queue
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
	void testEnqueue() {
		Queue q1 = build(100); // in an array queue, length is 128 (base_length*2*x)
		for (int i = 0; i < 50; i++) { // remove 0-49 from queue
			q1.dequeue();
		}
		for (int i = 100; i < 150; i++) { // add 100-149 to queue, an arrayQueue should become circular
			q1.enqueue(i);
		}
		
		assertEquals(q1.first(), 50); // check for expected values
		assertEquals(q1.last(), 149);
		for (int i = 50; i < 150; i++) {
			assertEquals(q1.dequeue(), i);
		}
		
		Queue q2 = build(7); // check edge case where last_empty in ArrayQueue has looped around, but the last element has not.
		q2.dequeue();
		q2.enqueue(100);
		assertEquals(q2.last(),100);
		assertEquals(q2.first(),1);
		
		
		
	}
	
	@Test
	void testIterator() { // Check that iterator returns elements in expected order and expected amount by validating against the functionality of the list itself.
		int times = 10000;
		Queue q1 = build(times);
		Iterator<Object> iter = q1.iterator();
		for (int i = 0; i < times; i++) {
			assertEquals(iter.next(), q1.dequeue());
		}
	}

	@Test
	void testFirstLast() {
		Queue q1 = build(10, 15, 9);
		assertEquals(q1.last(), 9); // check first and last element
		assertEquals(q1.first(), 15);

		for (int i = 0; i < 9; i++) { // remove 9 elements, making the first and last element have the same position
			q1.dequeue();
		}
		assertEquals(q1.last(), q1.first());
		q1.dequeue();
		assertThrows(NoSuchElementException.class, () -> q1.first());
		assertThrows(NoSuchElementException.class, () -> q1.last());
	}
	
	
	private Queue makeNew() {
		return new ArrayQueue(); // Can be changed for other implementations
		//return new LinkedQueue();
	}

	private Queue build(int length) {
		Queue q = makeNew(); // Can be changed for other concrete implementations!
		for (int i = 0; i < length; i++) { // fill with dummy objects
			q.enqueue(i);
		}
		return q;
	}

	private Queue build(int length, int first, int last) { // overloaded method for setting first and last elements
		Queue q = makeNew(); // Can be changed for other concrete implementations!
		q.enqueue(first);
		for (int i = 0; i < length - 2; i++) {
			q.enqueue(i);
		}
		q.enqueue(last);
		return q;
	}

}
