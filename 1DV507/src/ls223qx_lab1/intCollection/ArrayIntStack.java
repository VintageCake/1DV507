package ls223qx_lab1.intCollection;

public class ArrayIntStack extends AbstractIntCollection implements IntStack {
	int iterator = size - 1; // Size will always be one more than iterator
	// Iterator position should always point at an element that is to be modified by a push.

	public ArrayIntStack() {
		// Empty constructor
	}

	@Override
	public void push(int n) {
		size++;
		iterator++;
		if (size == values.length) {
			resize();
		}
		values[size] = n;

	}

	@Override
	public int pop() {
		if (iterator == -1) {
			throw new IndexOutOfBoundsException("Empty stack!");
		}
		int hold = values[size];
		size--;
		iterator--;
		return hold;
	}

	@Override
	public int peek() {
		if (iterator >= 0)
			return values[iterator];
		else
			throw new IndexOutOfBoundsException("Empty stack!");
	}

}
