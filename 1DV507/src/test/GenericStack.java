package test;

public interface GenericStack<T> {
	public int size();
	public T pop();
	public void push(T el);
	public T peek();

}
