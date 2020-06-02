package extrminpq;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestMinPQ {
	@Test
	public void containsTest() {
		ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
		pq.add("lyrebird", 1);
		pq.add("great tit", 2);
		assertEquals(true, pq.contains("lyrebird"));
		assertEquals(true, pq.contains("great tit"));
		assertEquals(false, pq.contains("eagle"));
	}

	@Test
	public void addRemoveTest() {
		ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
		pq.add("lyrebird", 1);
		pq.add("great tit", 2);
		assertEquals("lyrebird", pq.getSmallest());
		pq.removeSmallest();
		assertEquals("great tit", pq.getSmallest());
	}

	@Test
	public void changePriorityTest() {
		ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
		pq.add("lyrebird", 1);
		pq.add("great tit", 2);
		pq.changePriority("lyrebird", 3);
		assertEquals("great tit", pq.getSmallest());	
	}

	@Test
	public void copyTest() {
		ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
		pq.add("lyrebird", 1);
		pq.add("great tit", 2);
		pq.add("eagle", 0);
		ArrayHeapMinPQ<String> pq1 = new ArrayHeapMinPQ<>(pq);
		assertEquals(true, pq1.contains("lyrebird"));
		assertEquals(true, pq1.contains("great tit"));
		assertEquals(true, pq1.contains("eagle"));
		assertEquals("eagle", pq.getSmallest());
	}
}