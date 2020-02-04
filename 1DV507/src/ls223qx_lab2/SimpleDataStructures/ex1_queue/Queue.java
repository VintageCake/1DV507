package ls223qx_lab2.SimpleDataStructures.ex1_queue;

import java.util.Iterator;

/**
 * An interface for a data-structure that will hold Objects in a queue (First-in first-out data-structure).
 */
public interface Queue {  
	   
   	/**
   	 * Find out the size of the queue.
   	 *
   	 * @return the current <code>size</code> of the queue
   	 */
   	public int size();                     // current queue size 
	   
   	/**
   	 * Checks if queue is empty.
   	 *
   	 * @return true, if queue is empty
   	 */
   	public boolean isEmpty();              // true if queue is empty 
	   
   	/**
   	 * Put <code>element</code> object into the queue.
   	 *
   	 * @param element to be put into the queue
   	 */
   	public void enqueue(Object element);   // add element at end of queue 
	   
   	/**
   	 * Remove an object from the queue and return the object itself.
   	 *
   	 * 
   	 * @return one object at the beginning of the queue
   	 * @throws NoSuchElementException if queue is already empty
   	 */
   	public Object dequeue();               // return and remove first element. 
	   
   	/**
   	 * Returns the first object in the queue.
   	 *
   	 * @return the object at the beginning of the queue without removing it from the queue
   	 * @throws NoSuchElementException if queue is empty
   	 */
   	public Object first();                 // return (without removing) first element 
	   
   	/**
   	 * Returns the last object in the queue.
   	 *
   	 * @return the last object in the queue without removing it from the queue.
   	 * @throws NoSuchElementException if queue is empty
   	 */
   	public Object last();                  // return (without removing) last element 
	   
   	/**
   	 * Interprets the current queue as a string.
   	 *
   	 * @return a String representation of the queue.
   	 */
   	public String toString();              // return a string representation of the queue content
	   
   	/**
   	 * Returns an iterator which contains all current values in all the nodes inside the queue.
   	 * Beware that this probably won't work well with anything other than the primitive data types.
   	 *
   	 * @return an iterator of all currently held elements
   	 */
   	public Iterator<Object> iterator();    // element iterator
	}