package deque;

class ShowList {
	public static void main(String[] argv) {
		LinkedListDeque<Integer> deq = new LinkedListDeque<>();
		deq.addFirst(1);
		deq.addLast(2);
		LinkedListDeque<Integer> newdeq = new LinkedListDeque<Integer>(deq);
		deq.removeLast();
		newdeq.printDeque();
	}
}