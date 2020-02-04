package ls223qx_lab2.SimpleDataStructures.ex1_queue;

import java.util.Iterator;

public class QueueMain {
	public static void main(String[] args) {
		Queue q = new LinkedQueue();
		for (int i = 0; i < 20; i++) { // fill queue with 20 integers
			q.enqueue(i);
		}
		Iterator<Object> i = q.iterator(); // testing iterator
		while (i.hasNext()) { 
			System.out.println(i.next());
		}
		System.out.println();
		System.out.println(q.size()); // print size
		System.out.println(q.toString()); // create string interpretation
		System.out.println();
		
		System.out.println(q.last()); // View last and first element
		System.out.println(q.first());
		System.out.println();
		while (!q.isEmpty()) {
			System.out.println(q.dequeue()); // dequeue all elements
		}
		
		if (q.isEmpty()) { // when size is 0, do this
			System.out.println("Queue is empty!");
		}
		
		/*for (int x = 0; x < 5; x++) { // uncomment this for testing of first and last
		*	q.enqueue(x);
		}*/
		
		try {
			System.out.println(q.last());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(q.first());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
