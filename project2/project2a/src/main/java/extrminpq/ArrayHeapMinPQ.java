package extrminpq;

class ArrayHeapMinPQ<T> {
	public ArrayHeapMinPQ() {
		array_ = (T[]) new Object[8];
		size_ = 0;
	}

	public ArrayHeapMinPQ(ArrayHeapMinPQ<T> other) {
		System.arraycopy(other.array_, 0, array_, 0, other.size_);
		size_ = other.size_;
	}

	/** checks whether an item is in the priority queue */
	public boolean contains(T item) {
		return containsHelper(item, 0);
	}

	/** add an item to the priority queue */
	public void add(T item) {
		if (size_ == silent_size_)
			scale();
		array_[size_] = item;
		pushUp(size_);	// heapify 
		size_++;		
	}

	/** returns the current smallest item in the priority queue */
	public T getSmallest() {
		return array_[0];
	}

	/** removes the smallest element and returns it */
	public T removeSmallest() {
		T first = getSmallest();
		size_--;
		array_[0] = array_[size_];
		pushDown(0);	// heapify 
		if (size_ < silent_size_ / 4)
			scale(false);	// downscale if > 75% of the array is empty
		return first;
	}

	public int size() {
		return size_;
	}

	private T[] array_;
	private int size_;
	private int silent_size_;

	/** recursively swaps an element with its parent as long
	as its smaller than its parent */
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

	/** reverse of pushUp */
	private void pushDown(int pos) {
		int child1_pos = 2 * pos + 1;
		int child2_pos = 2 * pos + 2;
		int minchild_pos;
		// determine the smallest child, if there are two children 
		if (child2_pos < size_) {
			if (array_[child1_pos] < array_[child2_pos])
				minchild_pos = child1_pos;
			else
				minchild_pos = child2_pos;
		}
		// case of 1 child
		else if (child2_pos == size_) {
			minchild_pos = child1_pos;
		// return if there are no children 
		else
			return;
		// swap parent with the smallest child if child < parent 
		if (array_[pos] > array_[minchild_pos]) {
			T temp = array_[pos];
			array_[pos] = array_[minchild_pos];
			array_[minchild_pos] = temp;
			pushDown(minchild_pos);
		}
	}

	/** helper for the contains() method */
	private boolean containsHelper(T item, int pos) {
		if (array_[pos].equals(item)) 
			return true;
		// item cannot be in the heap if item is smaller than the current 
		// node or pos is >= size_ 
		if (item < array_[pos] || size_ <= pos) 
			return false;
		// if item > array_[pos] and pos < size_ we continue recursively
		return Math.max(containsHelper(item, array_[pos * 2 + 1]), 
				containsHelper(array_[pos * 2 + 2]));
	}

	/** helper function for rescaling the array */
	private void scale(bool up=true) {
		if (up) silent_size_ *= 2;
		else silent_size_ /= 2;
		T[] new_array = (T[]) new Object[silent_size_];
		System.arraycopy(array_, 0, new_array, 0, size_);
		array_ = new_array;
	}	
}