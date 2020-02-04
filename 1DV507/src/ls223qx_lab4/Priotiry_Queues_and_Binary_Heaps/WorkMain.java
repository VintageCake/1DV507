package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

public class WorkMain {
	public static void main(String[] args) {
		PriorityQueue<Integer> priQ = new BinaryHeapQueue<>();
		
		for (int i = 0; i < 10; i++) { // With with 10
			priQ.insert(new WorkTask<Integer>(i, "this is a task", i+100 ));
			// Syntax for constructor is (priority, description, task).
		}
		while (!priQ.isEmpty()) {
			Task<Integer> h = priQ.pullHighest();
			System.out.println(h.getPriority());
			System.out.println(h.getTask().toString());
			System.out.println();
		}
		for (int i = 0; i < 10; i++) {
			priQ.insert(new WorkTask<Integer>(i, "this is a task", i+100 ));
		}
		Task<Integer> t1 = new WorkTask<>(100, "Hello", 500);
		Task<Integer> t2 = new WorkTask<>(100, "Hello", 500);
		Task<String> t3 = new WorkTask<>(100, "Hello", "Task");
		if (t1.equals(t2)) {
			System.out.println("t1 and t2 is equal");
		}
		if (!t2.equals(t3)) {
			System.out.println("t2 and t3 is not equal");
		}
		priQ.insert(t1);
		priQ.insert(t2);
		if (priQ.peekHighest().equals(t1)) {
			System.out.println("Peek is working, order correct");
		}
		System.out.println(priQ.size()); // Expected is 12
		while (!priQ.isEmpty()) {
			priQ.pullHighest();
		}
		System.out.println(priQ.size()); // Excepted is 0
		
	}

}
