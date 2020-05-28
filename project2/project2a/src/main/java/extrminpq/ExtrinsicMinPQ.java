package extrminpq;

public interface ExtrinsicMinPQ<T> {
	public boolean contains(T item);
	public void add(T item, double priority);
	public T getSmallest();
	public T removeSmallest();
	public int size();
	public void changePriority(T item, double priority);
}