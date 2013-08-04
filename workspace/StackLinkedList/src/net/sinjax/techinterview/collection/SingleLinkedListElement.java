package net.sinjax.techinterview.collection;

public class SingleLinkedListElement<T> {
	public SingleLinkedListElement(T d) {
		this.data = d;
	}
	public SingleLinkedListElement(T d,SingleLinkedListElement<T>  next) {
		this.data = d;
		this.next = next;
	}
	T data;
	SingleLinkedListElement<T> next;
}
