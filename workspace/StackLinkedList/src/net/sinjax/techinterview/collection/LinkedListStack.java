package net.sinjax.techinterview.collection;

import java.util.Iterator;

public class LinkedListStack<DATA> extends SingleLinkedList<DATA> implements Stack<DATA>{

	@Override
	public void push(DATA t) {
		this.addToHead(t);
	}

	@Override
	public DATA peek() {
		SingleLinkedListElement<DATA> h = this.getHead();
		if(h == null)
			return null;
		return h.data;
	}

	@Override
	public DATA pop() {
		SingleLinkedListElement<DATA> h = this.getHead();
		if(h == null)
			return null;
		removeHead();
		return h.data;
	}

	@Override
	public Iterator<DATA> iterator() {
		return new Iterator<DATA>(){
			SingleLinkedListElement<DATA> head = getHead();
			@Override
			public boolean hasNext() {
				return head!=null;
			}

			@Override
			public DATA next() {
				DATA ret = head.data;
				head = head.next;
				return ret;
			}

			@Override
			public void remove() {
				LinkedListStack.this.remove(head.data);
			}
			
		};
	}

	
}
