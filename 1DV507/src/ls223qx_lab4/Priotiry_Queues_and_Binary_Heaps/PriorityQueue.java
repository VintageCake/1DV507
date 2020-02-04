package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

public interface PriorityQueue<T> {
	public int size();
	public boolean contains(Task<T> t);
	public boolean isEmpty();
	public String toString();
	
	public void insert(Task<T> element);
	public Task<T> pullHighest();
	public Task<T> peekHighest();
}
