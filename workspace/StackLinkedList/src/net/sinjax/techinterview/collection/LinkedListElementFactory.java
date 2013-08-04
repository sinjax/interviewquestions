package net.sinjax.techinterview.collection;

public interface LinkedListElementFactory<D,T extends SingleLinkedListElement<D>> {
	public T newNode(D data);
	public T newNode(D data,T nextNode);
	public T getNext(T f);
}
