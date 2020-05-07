package deque;

class ShowList {
	public static void main(String[] argv) {
		Deque<Integer> deq = new LinkedListDeque<>();
		deq.addFirst(1);
		deq.addLast(2);
		System.out.println(deq.size());
		deq.printDeque();
	}
}