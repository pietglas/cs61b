package deque;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestDeques {
	Integer a = 1;
	Integer b = 2;
	Deque<Integer> deq = new LinkedListDeque<>();
	@Test
	public void checkFirst() {
		deq.addFirst(1);
		assertEquals(a, deq.getFirst());
	}

	@Test
	public void checkLast() {
		deq.addLast(2);
		assertEquals(b, deq.getLast());
	}

	@Test
	public void checkItem() {
		deq.addFirst(2);
		deq.addFirst(1);
		assertEquals(b, deq.get(1));
	}

	@Test
	public void checkRemove() {
		deq.addLast(1);
		deq.addLast(3);
		deq.removeLast();
		assertEquals(a, deq.getLast());
	}

	ArrayDeque<String> arr = new ArrayDeque<>("hoi", 10);
	@Test 
	public void checkArray() {
		for (int i = 0; i != 10; i++)
			assertEquals("hoi", arr.get(i));
	}

	ArrayDeque<String> newarr = new ArrayDeque<>(arr);
	@Test
	public void checkCopy() {
		for (int i = 0; i != 10; i++)
			newarr.addLast("inner peace");
		assertEquals(20, newarr.size());
		assertEquals(40, newarr.underlying_size());
		for (int i = 0; i != 11; i++)
			newarr.removeLast();
		assertEquals(9, newarr.size());
		assertEquals(20, newarr.underlying_size());
	}

}