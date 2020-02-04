package ls223qx_lab1.intCollection;

public class ArrayIntList extends AbstractIntCollection implements IntList {

	public ArrayIntList() {
		// Empty constructor, no arguments needed.
	}

	@Override
	public void add(int n) {
		values[size] = n;
		size++;
		resizeCheck();

	}

	@Override
	public void addAt(int n, int index) { // Replaces a value, or behaves like add in case of a 'last' element.
		if (index == size) {
			this.add(n);
		}
		else if (index < size && index >= 0) // Replace
			values[index] = n;
		else {
			throw new IndexOutOfBoundsException("Bottom or top range of list exeeded! Index requested: " + index);
			// Throw when index is out of range
		}
	}

	@Override
	public void remove(int index) { // Remove value from array, taking all values in front of affected index one
									// step back.
		if (checkIndex(index, size)) {
			int[] newValues = new int[values.length];
			int iterator = 0;
			for (int i = 0; i < values.length; i++) {
				if (i != index) {
					newValues[iterator] = values[i];
					iterator++;
				}
			}
			values = newValues;
			size--;
		}
		else
			throw new IndexOutOfBoundsException("Index out of range!");
	}

	@Override
	public int get(int index) { // return specified index, simple check for index violation
		if (checkIndex(index, size - 1))
			return values[index];
		else
			throw new IndexOutOfBoundsException("Index out of range!");
	}

	@Override
	public int indexOf(int n) {
		for (int i = 0; i < values.length; i++) {
			if (n == values[i]) {
				return i;
			}
		}
		return -1; // Return -1 if not found
	}

	private void resizeCheck() {
		if (size == values.length) // Resize on full array
			resize();
	}

}
