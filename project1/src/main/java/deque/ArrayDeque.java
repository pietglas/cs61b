package deque;

public class ArrayDeque<T> implements Deque<T> {
	/** constructs an empty deque */
	public ArrayDeque() {
		array_ = (T[]) new Object[8];
		pos_begin_ = 2;
		pos_end_ = 3;
		size_ = 0;
	}

	/** Constructs a deque of size `size`, with all entries set to `item` */
	public ArrayDeque(T item, int size) {
		underlying_size_ = 2 * size;
		array_ = (T[]) new Object[underlying_size_];
		size_ = size;
		pos_begin_ = size_ / 2;
		int pos = pos_begin_;
		for (; pos != size_ + pos_begin_; pos++)
			array_[pos] = item;
		pos_end_ = pos;
	}

	/** Copy constructor */
	public ArrayDeque(ArrayDeque<T> other) {
		array_ = (T[]) new Object[other.size() * 2];
		size_ = other.size();
		underlying_size_ = size_ * 2;
		pos_begin_ = size_ / 2;
		int pos = pos_begin_;
		for (; pos != pos_begin_ + size_; pos++)	// copy from utils? 
			array_[pos] = other.get(pos - pos_begin_);
		pos_end_ = pos;
	}

	public void addFirst(T item) {
		if (pos_begin_ == 0) 
			upscale();
		--pos_begin_;
		array_[pos_begin_] = item;
		++size_;
	}

	public void addLast(T item) {
		if (pos_end_ == underlying_size_)
			upscale();
		array_[pos_end_] = item;
		++pos_end_;

	}

	public int size() {
		return size_;
	}

	public void printDeque() {
		for (int pos = pos_begin_; pos != pos_end_; pos++)
			System.out.println(array_[pos]);
	}

	public T removeFirst() {
		T item = array_[pos_begin_];
		++pos_begin_;
		--size_;
		if (size_ < underlying_size_ / 4)
			downscale();
		return item;
	}

	public T removeLast() {
		--pos_end_;
		T item = array_[pos_end_];
		--size_;
		if (size_ < underlying_size_ / 4)
			downscale();
		return item;
	}

	public T get(int index) {
		T item = array_[pos_begin_ + index];
		return item;
	}

	public T getFirst() {
		T item = array_[pos_begin_];
		return item;
	}

	public T getLast() {
		T item = array_[pos_end_ - 1];
		return item;
	}

	private T[] array_;		// usage factor at least 25%
	private int size_;
	private int underlying_size_;
	private int pos_begin_;	
	private int pos_end_;	// first position without an element

	private void upscale() {
		underlying_size_ *= 2;
		T[] new_array = (T[]) new Object[underlying_size_];
		int new_begin = underlying_size_ / 4;
		for (int i = 0; i != size_; i++)
			new_array[i + new_begin] = array_[i + pos_begin_];
		pos_begin_ = new_begin;
		pos_end_ = pos_begin_ + size_;
		array_ = new_array;
	}	

	private void downscale() {
		underlying_size_ /= 2;
		T[] new_array = (T[]) new Object[underlying_size_];
		int new_begin = underlying_size_ / 4;
		for (int i = 0; i != size_; i++)
			new_array[i + new_begin] = array_[i + pos_begin_];
		pos_begin_ = new_begin;
		pos_end_ = pos_begin_ + size_;
		array_ = new_array;
	}
}