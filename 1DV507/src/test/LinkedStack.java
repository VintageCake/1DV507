package test;

public class LinkedStack<T> implements GenericStack<T> { // Test implementation
	private Node<T> root = null;
	private int size = 0;

	public LinkedStack() {
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T pop() {
		return null;
	}

	@Override
	public void push(T el) {
		if (root == null) {
			root = new Node<>(el);
		}
		else {
			root.add(el);
		}
	}

	@Override
	public T peek() {
		return root.value;
	}

	private class Node<X> {
		X value;
		Node<X> next = null;

		public Node(X val) {
			value = val;
		}

		public void add(X el) {
			if (next == null) {
				next = new Node<>(el);
			}
			else
				next.add(el);
		}
	}

}
