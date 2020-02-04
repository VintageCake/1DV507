package ls223qx_lab1.intCollection;

public class CollectionMain {
	public static void main(String[] args) {
		ArrayIntList testList = new ArrayIntList();
		ArrayIntStack testStack = new ArrayIntStack();
		
		
		//Testing my list
		System.out.println("Is testList empty? " + testList.isEmpty());
		for (int i = 0; i < 100; i++) { // Add 100 elements
			testList.add(i);
		}
		System.out.println("Is testList empty now? " + testList.isEmpty());
		System.out.println(testList.get(50));
		System.out.println(testList.size());

		System.out.println(testList.toString());
		try {
			System.out.println(testList.get(100));
			// Code will not let you access 'empty' parts of the array.
			// err.println arrives to the console slower than normal println, making messages unordered.
		}
		catch (Exception e) {
		}
		
		testList.remove(50);
		System.out.println(testList.toString());

		testList.addAt(10, 99); 
		System.out.println(testList.size());
		while (!testList.isEmpty()) { // Empty contents
			testList.remove(0);
		}
		
		// Testing my stack
		try {
		System.out.println(testStack.pop());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(testStack.size() + " Size");
		testStack.push(1);
		testStack.push(2);
		testStack.push(3);
		System.out.println(testStack.size() + " Size after push");
		System.out.println(testStack.pop());
		System.out.println(testStack.size() + " Size after one pop");
		testStack.push(4);
		testStack.push(5);
		System.out.println(testStack.size() + " Size after two pushes");
		while (!testStack.isEmpty()) { // Pop all values
			System.out.println(testStack.pop() + " Pop");
		}
		System.out.println(testStack.size());
	}


}
