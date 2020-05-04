package project1;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeques {
	public static void main(String[] args) {
		Deque<int> deque = new LinkedListDeque<>();
		deque.addFirst(1);
		deque.addLast(2);
		assertEquels(1, deque.getFirst());
		assertEquels(1, deque.get(0));
		assertEquels(2, deque.getLast());
		assertEquels(2, deque.size());
	}
}