package extrminpq;

class ArrayHeapMinPQ<T> implements Deque<T> {
	public ArrayHeapMinPQ() {
		array_ = (T[]) new Object[8];
		size_ = 0;
		pos_first_ = 2;
		pos_last_ = 3;
	}

	public ArrayHeapMinPQ(ArrayHeapMinPQ<T> other) {
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

	private T[] array_;
	private int size_;
	private int silent_size_;
	private int pos_first_;
	private int pos_last_;

	private int pushUp() {

	}

	private int pushDown() {

	}

	private boolean containsHelper(T item, int pos) {
		if (array_[pos] == item)
			return true;
		if (item < array_[pos] || pos_last_ <= pos)
			return false;
		return Math.max(containsHelper(item, array_[pos * 2 + 1]), 
				containsHelper(array_[pos * 2 + 2]));
	}

	private void upscale() {
		silent_size_ *= 2;
		T[] new_array = (T[]) new Object[silent_size_];
		int new_begin = silent_size_ / 4;
		for (int i = 0; i != size_; i++)
			new_array[i + new_begin] = array_[i + pos_first_];
		pos_first_ = new_begin;
		pos_last_ = pos_first_ + size_;
		array_ = new_array;
	}	

	private void downscale() {
		silent_size_ /= 2;
		T[] new_array = (T[]) new Object[silent_size_];
		int new_begin = silent_size_ / 4;
		for (int i = 0; i != size_; i++)
			new_array[i + new_begin] = array_[i + pos_first_];
		pos_first_ = new_begin;
		pos_last_ = pos_first_ + size_;
		array_ = new_array;
	}
}