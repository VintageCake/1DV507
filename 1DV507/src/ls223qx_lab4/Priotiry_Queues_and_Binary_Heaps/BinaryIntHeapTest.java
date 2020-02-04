package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.Test;

class BinaryIntHeapTest {
	

	@Test
	void testSize() { // Make sure size is correct
		BinaryIntHeap x = new BinaryIntHeap();
		x.insert(10);
		x.insert(10);
		x.insert(30);
		x.insert(40);
		
		assertEquals(x.size(), 4);
		assertEquals(x.pullHighest(), 40);
		assertEquals(x.size(), 3);
	}
	
	@Test
	void testEmpty() { // Tests an empty heap
		BinaryIntHeap x = new BinaryIntHeap();
		assertEquals(x.size(), 0);
		assertThrows(NoSuchElementException.class, () -> x.pullHighest());
	}
	
	@Test
	void testInsert() { // Assert that order and size is correct for large heaps.
		BinaryIntHeap x = new BinaryIntHeap();
		Random r = new Random();
		int times = 10000;
		for (int i = 0; i < times; i++) {
			x.insert(r.nextInt(10000));
		}
		int count = 0;
		int prev = Integer.MAX_VALUE;
		while (!x.isEmpty()) {
			int current = x.pullHighest();
			assertTrue(prev >= current);
			prev = current;
			count++;
		}
		assertEquals(x.size(), 0);
		assertEquals(count, times);
	}

}
