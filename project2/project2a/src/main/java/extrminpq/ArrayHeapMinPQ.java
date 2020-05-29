package extrminpq;

class ArrayHeapMinPQ<T> {
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

	public void add(T item) {
		array_[pos_last_] = item;
		pos_last_++;
		size_++;
		pushUp(pos_last_ - 1);		
	}

	public T getSmallest() {
		return array_[pos_first_];
	}

	public T removeSmallest() {
		T first = getSmallest();
		pos_last_--;
		array_[0] = array_[pos_last_];
		pushDown(0);
	}

	public int size() {
		return size_;
	}

	private T[] array_;
	private int size_;
	private int silent_size_;
	private int pos_first_;
	private int pos_last_;

	private void pushUp(int pos) {
		int parent_pos;
		if (pos % 2 == 0) 
			parent_pos = pos / 2 - 1;
		else
			parent_pos = pos / 2;
		if (array_[pos] < array_[parent_pos]) {
			T temp = array_[parent_pos];
			array_[parent_pos] = array_[pos];
			array_[pos] = temp;
			pushUp(parent_pos);
		}
	}

	private void pushDown(int pos) {
		int child1_pos = 2 * pos + 1;
		int child2_pos = 2 * pos + 2;
		int minchild_pos;
		if (child2_pos < size_) {
			if (array_[child1_pos] < array_[child2_pos])
				minchild_pos = child1_pos;
			else
				minchild_pos = child2_pos;
		}
		else if (child2_pos == size_) {
			minchild_pos = child1_pos;
		else
			return;
		if (array_[pos] > array_[minchild_pos]) {
			T temp = array_[pos];
			array_[pos] = array_[minchild_pos];
			array_[minchild_pos] = temp;
			pushDown(minchild_pos);
		}
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