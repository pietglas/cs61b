package project1;

public class LinkedListDeque<T> implements Deque<T> {
	private class ListNode {
		public ListNode() {}
		public ListNode(T item, ListNode prev, ListNode next) {
			item_ = item;
			prev_ = prev;
			next_ = next;
		}
		public T item_;
		public ListNode prev_;
		public ListNode next_;
	}

	public LinkedListDeque() {
		sentinel_ = new ListNode();
		sentinel_.prev_ = sentinel_;
		sentinel_.next_ = sentinel_;
		size_ = 0;
	}

	/** Adds item to front of the deque */
	public void addFirst(T item) {
		sentinel_.next_.prev_ = new ListNode(item, sentinel_, sentinel_.next_);
		sentinel_.next_ = sentinel_.next_.prev_;
		if (size_ == 0) {
			sentinel_.prev_ = sentinel_.next_;
		}
		++size_;
	}

	/** adds item to the bakc of the deque */
	public void addLast(T item) {
		if (size_ == 0)
			addFirst(item);
		else {
			sentinel_.prev_.next_ = new ListNode(item, sentinel_.prev_, sentinel_);
			sentinel_.prev_ = sentinel_.prev_.next_;
			++size_;
		}
	}

	/** returns the size of the deque */
	public int size() {
		return size_;
	}

	/** prints the deque */
	public void printDeque() {
		ListNode node = sentinel_.next_;
		for (int pos = 0; pos != size_; pos++) {
			System.out.println(node.item_);
			node = node.next_;
		}
	}

	/** removes the first item from the deque and returns it. Returns
	null if the deque is empty. */
	public T removeFirst() {
		T data = sentinel_.next_.item_;
		sentinel_.next_ = sentinel_.next_.next_;
		sentinel_.next_.prev_ = sentinel_;
		--size_;
		return data;
	}

	/** removes the last item from the deque and returns it.Returns
	null if the deque is empty. */
	public T removeLast() {
		T data = sentinel_.prev_.item_;
		sentinel_.prev_ = sentinel_.prev_.prev_;
		sentinel_.prev_.next_ = sentinel_;
		--size_;
		return data;
	}

	/** returns the item at index in the deque.Returns
	null if the deque is empty. */
	public T get(int index) {
		if (size_ == 0)
			return null;
		else if (size_ / 2 < index) {
			ListNode node = sentinel_.prev_;
			for (int pos = size_ - 1; pos != index; pos --)
				node = node.prev_;
			return node.item_;
		}
		else {
			ListNode node = sentinel_.next_;
			for (int pos = 0; pos != index; pos++)
				node = node.next_;
			return node.item_;
		}
	}

	/** returns the first item from the deque.Returns
	null if the deque is empty. */
	public T getFirst() {
		return get(0);
	}

	/** returns the last item from the deque. Returns
	null if the deque is empty. */
	public T getLast() {
		return get(size_ - 1);
	}
	
	private int size_;
	private ListNode sentinel_;
}