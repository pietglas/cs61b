package extrminpq;

class PriorityNode<T> implements Cloneable {
	public PriorityNode() {};
	public PriorityNode(T data, double priority) {
		data_ = data;
		priority_ = priority;
	}
	public PriorityNode(PriorityNode<T> other) {
		data_ = other.data_;
		priority_ = other.priority_;
	}

	public setPriority(double priority) {priority_ = priority;}
	public setData(T data) {data_ = data;}
	public getPriority() {return priority_;}
	public getData() {return data_;}

	@Override
	public boolean equals(PriorityNode<T> other) {
		data_ == other.data_ ? return true : return false;
	}

	private T data_;
	private double priority_;
}