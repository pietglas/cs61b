package extrminpq;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.ArrayList;

/** Extrinsic minimum priority queue, implemented as a heap. When adding
a new element, the client needs to specify the priority as a double value. 
The lower the value, the higher the priority. */
class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
	/** the following class stores the data and the priority */
	private class PriorityNode {
		public PriorityNode() {};
		public PriorityNode(T data, double priority) {
			data_ = data;
			priority_ = priority;
		}
		public PriorityNode(PriorityNode other) {
			data_ = other.data_;
			priority_ = other.priority_;
		}

		public void setPriority(double priority) {priority_ = priority;}
		public void setData(T data) {data_ = data;}
		public double getPriority() {return priority_;}
		public T getData() {return data_;}

		private T data_;
		private double priority_;
	}

	/** default constructor */
	public ArrayHeapMinPQ() {
		heap_ = new ArrayList<>();
		items_ = new HashMap<>();
		size_ = 0;
	}

	/** copy constructor */
	public ArrayHeapMinPQ(ArrayHeapMinPQ<T> other) {
		heap_ = new ArrayList<>();
		items_ = new HashMap<>();
		for (int i = 0; i != other.size_; i++)
			heap_.add(new PriorityNode(other.heap_.get(i)));
		for (HashMap.Entry<T, Double> entry : other.items_.entrySet())
			items_.put(entry.getKey(), entry.getValue());
		size_ = other.size_;
	}

	/** checks whether an item is in the priority queue */
	@Override
	public boolean contains(T item) {
		return items_.containsKey(item);
	}

	/** add an item to the priority queue. Throws IllegalArgumentException
	if the key already exists */
	@Override
	public void add(T item, double priority) {
		if (items_.containsKey(item)) throw new 
				IllegalArgumentException("pq already contains the item");
		heap_.add(new PriorityNode(item, priority));
		pushUp(size_);	// heapify 
		items_.put(item, priority);
		size_++;		
	}

	/** returns the current smallest item in the priority queue */
	@Override
	public T getSmallest() {return heap_.get(0).getData();}

	/** removes the smallest element and returns it */
	@Override
	public T removeSmallest() {
		T first = getSmallest();
		size_--;
		heap_.set(0, heap_.get(size_));
		pushDown(0);	// heapify 
		return first;
	}

	/** returns the size of the priority queue */
	@Override
	public int size() {return size_;}

	/** returns true iff the priority queue is empty */
	@Override
	public boolean empty() {return size_ == 0;}

	/** changes the priority of an item in the priority queue. 
	Throws an IllegalArgumentException if the queue does not contain
	the item */ 
	@Override
	public void changePriority(T item, double priority) {
		if (!items_.containsKey(item)) throw new 
			NoSuchElementException("queue does not contain the item");
		PriorityNode node = new PriorityNode(item, items_.get(item));
		changePriorityHelper(node, priority, 0);
	}

	private ArrayList<PriorityNode> heap_;
	// library for the items and their priorities, to ensure 
	// that add() and contain() run in logarithmic time
	private HashMap<T, Double> items_;
	private int size_;
	// private int silent_size_;

	/** swap two items in the heap */
	private void swap(int pos1, int pos2) {
		PriorityNode temp = heap_.get(pos1);
		heap_.set(pos1, heap_.get(pos2));
		heap_.set(pos2, temp);
	}

	/** recursively swaps an element with its parent as long
	as its smaller than its parent */
	private void pushUp(int pos) {
		int parent_pos;
		if (pos % 2 == 0) 
			parent_pos = pos / 2 - 1;
		else
			parent_pos = pos / 2;
		if (parent_pos >= 0 && 
			heap_.get(pos).getPriority() < heap_.get(parent_pos).getPriority()) {
			swap(pos, parent_pos);
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
			if (heap_.get(child1_pos).getPriority() < 
				heap_.get(child2_pos).getPriority())
				minchild_pos = child1_pos;
			else
				minchild_pos = child2_pos;
		}
		// case of 1 child
		else if (child2_pos == size_) 
			minchild_pos = child1_pos;
		// return if there are no children 
		else
			return;
		// swap parent with the smallest child if child < parent 
		if (heap_.get(pos).getPriority() > heap_.get(minchild_pos).getPriority()) {
			swap(pos, minchild_pos);
			pushDown(minchild_pos);
		}
	}

	/** helper for the contains() method */
	private boolean containsHelper(PriorityNode item, int pos) {
		if (heap_.get(pos).getData().equals(item.getData())) 
			return true;
		// item cannot be in the heap if item is smaller than the current 
		// node or pos is >= size_ 
		if (item.getPriority() < heap_.get(pos).getPriority() || size_ <= pos) 
			return false;
		// if item > heap_[pos] and pos < size_ we continue recursively
		return containsHelper(item, pos * 2 + 1) || 
			containsHelper(item, pos * 2 + 2);
	}

	/** helper function for changePriority() */
	private void changePriorityHelper(PriorityNode node, double priority,
			int pos) {
		if (node.getData().equals(heap_.get(pos).getData())) {
			double old_priority = heap_.get(pos).getPriority();
			heap_.get(pos).setPriority(priority);
			// if the new priority is higher (i.e. the new double value lower),
			// we possibly need to push the element up. Otherwise, we might need 
			// to push it down. 
			if (priority < old_priority)
				pushUp(pos);
			else
				pushDown(pos);
		}
		else { // keep searching until we find the element
			int child1_pos = 2 * pos + 1;
			int child2_pos = 2 * pos + 2;
			if (node.getPriority() < heap_.get(pos).getPriority())
				changePriorityHelper(node, priority, child1_pos);
			else
				changePriorityHelper(node, priority, child2_pos);
		}
	}	
}

