package extrminpq;

import java.util.NoSuchElementException;
import java.util.IllegalArgumentException;
import java.util.HashMap;

class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	public ArrayHeapMinPQ() {
		heap_ = (PriorityNode<T>[]) new Object[8];
		size_ = 0;
	}

	public ArrayHeapMinPQ(ArrayHeapMinPQ<T> other) {
		System.arraycopy(other.heap_, 0, heap_, 0, other.size_);
		for (HashMap.Entry<PriorityNode<T>, double> entry : other.items_.entrySet())
			items_.put(entry.getKey(), entry.getValue());
		size_ = other.size_;
	}

	/** checks whether an item is in the priority queue */
	public boolean contains(T item) {
		return items_.containsKey(item);
	}

	/** add an item to the priority queue. Throws IllegalArgumentException
	if the key already exists */
	public void add(T item, double priority) {
		if (items_.containsKey(item)) throw IllegalArgumentException;
		if (size_ == silent_size_) scale();
		PriorityNode<T> node = new PriorityNode<>(item, priority);
		heap_[size_] = node;
		pushUp(size_);	// heapify 
		items_.put(item, priority);
		size_++;		
	}

	/** returns the current smallest item in the priority queue */
	public T getSmallest() {return heap_[0].getData();}

	/** removes the smallest element and returns it */
	public T removeSmallest() {
		T first = getSmallest().getData();
		size_--;
		heap_[0] = heap_[size_];
		pushDown(0);	// heapify 
		if (size_ < silent_size_ / 4)
			scale(false);	// downscale if > 75% of the array is empty
		return first;
	}

	public int size() {return size_;}

	public boolean empty() {return size_ == 0;}

	public void changePriority(T item, double priority) {
		if (!items_.containsKey(item)) throw NoSuchElementException;
		PriorityNode<T> node = new PriorityNode<>(item, items_.get(item));
		changePriorityHelper(node, priority, 0);
	}

	private PriorityNode<T>[] heap_;
	private HashMap<T item, double priority> items_;
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
		if (heap_[pos] < heap_[parent_pos]) {
			PriorityNode<T> temp = heap_[parent_pos];
			heap_[parent_pos] = heap_[pos];
			heap_[pos] = temp;
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
			if (heap_[child1_pos].getPriority() < heap_[child2_pos].getPriority())
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
		if (heap_[pos].getPriority() > heap_[minchild_pos].getPriority()) {
			PriorityNode<T> temp = heap_[pos];
			heap_[pos] = heap_[minchild_pos];
			heap_[minchild_pos] = temp;
			pushDown(minchild_pos);
		}
	}

	/** helper for the contains() method */
	private boolean containsHelper(PriorityNode<T> item, int pos) {
		if (heap_[pos].equals(item)) 
			return true;
		// item cannot be in the heap if item is smaller than the current 
		// node or pos is >= size_ 
		if (item.getData() < heap_[pos].getData() || size_ <= pos) 
			return false;
		// if item > heap_[pos] and pos < size_ we continue recursively
		return Math.max(containsHelper(item, heap_[pos * 2 + 1]), 
				containsHelper(heap_[pos * 2 + 2]));
	}

	/** helper function for rescaling the array */
	private void scale(bool up=true) {
		if (up) silent_size_ *= 2;
		else silent_size_ /= 2;
		PriorityNode<T>[] new_heap = (PriorityNode<T>[]) new Object[silent_size_];
		System.arraycopy(heap_, 0, new_heap, 0, size_);
		heap_ = new_heap;
	}

	private void changePriorityHelper(PriorityNode<T> node, double priority,
			int pos) {
		if (node.equals(heap_[pos])) {
			double old_priority = heap_[pos].getPriority();
			heap_[pos].setPriority(priority);
			if (priority < old_priority)
				pushUp(pos);
			else
				pushDown(pos);
		}
		else {
			int child1_pos = 2 * pos + 1;
			int child2_pos = 2 * pos + 2;
			if (node.getPriority() < heap_[pos].getPriority())
				changePriorityHelper(node, priority, child1_pos);
			else
				changePriorityHelper(node, priority, child2_pos);
		}
	}	
}

