package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

public class WorkTask<T> implements Task<T> {
	private String description;
	private int priority;
	private T task;
	
	
	// Contains priority, description and generic task.
	
	public WorkTask(int pri, String desc, T t) {
		description = desc;
		if (!isValidPriority(pri)) {
			throw new IllegalArgumentException("Priority can only be positive integer!");
		}
		priority = pri;
		task = t;
	}
	private boolean isValidPriority(int a) { // Simple check for positive integer
		return (a >= 0);
	}

	@Override
	public T getTask() {
		return task;
	}

	@Override
	public void setTask(T t) {
		task = t;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String s) {
		description = s;
		
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void setPriority(int p) {
		if (isValidPriority(p)) {
			priority = p;
		}
		else {
			throw new IllegalArgumentException("Priority can only be positive integer!");
		}
		
	}
	@Override
	public int hashCode() {
		return priority*5 + description.hashCode() + task.hashCode(); // Simple hash that combines the priority integer with default hashes for string and whatever generic object is used.
		
	}
	@Override
	public boolean equals(Object t) {
		if (t instanceof Task<?> && this.hashCode() == t.hashCode()) { // Check each others hash code.
			return true;
		}
		return false;
	}

}
