package extrminpq;

class ExtrinsicArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	public ExtrinsicArrayHeapMinPQ() {
		array_ = (T[]) new Object[8];
		size_ = 0;
		pos_first_ = 2;
		pos_last_ = 3;
	}

	public ExtrinsicArrayHeapMinPQ(ExtrinsicArrayHeapMinPQ<T> other) {
		System.arraycopy(other.array_, other.pos_first_, 
			array_, other.pos_first_, other.size_);
		size_ = other.size_;
		pos_first_ = other.pos_first_;
		pos_last_ = other.pos_last_;
	}

	public boolean contains(T item) {
		return containsHelper(item, pos_first_);
	}

	public void add(T item, double priority) {
		
	}

	public T getSmallest() {
		return array_[pos_first_];
	}

	public T removeSmallest();

	public int size() {
		return size_;
	}

	public void changePriority(T item, double priority);

	private ArrayHeapMinPQ<T> heap_;
} 