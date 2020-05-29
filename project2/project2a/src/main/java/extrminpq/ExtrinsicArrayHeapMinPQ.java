package extrminpq;

class ExtrinsicArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	public ExtrinsicArrayHeapMinPQ() {
		
	}

	public ExtrinsicArrayHeapMinPQ(ExtrinsicArrayHeapMinPQ<T> other) {
		
	}

	public boolean contains(T item) {
		return containsHelper(item, pos_first_);
	}

	public void add(T item, double priority) {
		
	}

	public T getSmallest() {
		return heap_.getSmallest();
	}

	public T removeSmallest() {
		return heap_.removeSmallest();
	}

	public int size() {
		return heap_.size();
	}

	public void changePriority(T item, double priority) {

	}

	private ArrayHeapMinPQ<T> heap_;
} 