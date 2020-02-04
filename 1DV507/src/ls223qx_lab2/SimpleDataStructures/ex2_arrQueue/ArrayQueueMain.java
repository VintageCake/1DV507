package ls223qx_lab2.SimpleDataStructures.ex2_arrQueue;

import ls223qx_lab2.SimpleDataStructures.ex1_queue.Queue;

public class ArrayQueueMain {
	public static void main(String[] args) {
		Queue q = new ArrayQueue();

		for (int i = 0; i < 10; i++) { // shove 10 elements into a queue
			q.enqueue(i);
		}
		for (int i = 0; i < 10; i++) { // remove 10 elements,
			System.out.println(q.dequeue());
		}
		
		for (int i = 0; i < 100; i++) { // 'walk' through the array 100 times, should be enough to prove that the circular portion works
			q.enqueue(i);
			System.out.println(q.dequeue());
		}
		
		for (int i = 0; i < 3; i++) { // push another 3 elements into queue, array will not resize during this operation
			q.enqueue(i);
		}

		System.out.println(q.toString()); // test both the iterator and toString method, since toString calls the iterator internally
		
		System.out.println(q.last()); // test first and last methods
		System.out.println(q.first());
		
		for (int i = 0; i < 1000000; i++) { // fill with a million elements, testing with lots of elements
			q.enqueue(i);
		}
		System.out.println();
		System.out.println(q.last());
		System.out.println(q.first());

		System.out.println();
		//while (!q.isEmpty()) { // empty the queue
		//	System.out.println(q.dequeue()); // printing to console appears to be a pretty expensive operation
		//}

	}

}
