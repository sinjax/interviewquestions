package net.sinjax.techinterview.collection;

import java.util.Iterator;

public class MultiLevelDoubleLinkedList<DataType> implements Iterable<DataType>{
	public MultiLevelDoubleLinkedListElement<DataType> head;
	public MultiLevelDoubleLinkedListElement<DataType> tail;
	
	public void add(DataType d){
		addToTail(d);
	}

	public void addToTail(DataType d) {
		if(this.tail == null) // 0 elements in list
		{
			this.tail = new MultiLevelDoubleLinkedListElement<DataType>(d);
			this.head = this.tail;
			return;
		}
		this.tail.next = new MultiLevelDoubleLinkedListElement<DataType>(d);
		MultiLevelDoubleLinkedListElement<DataType> oldTail = tail; 
		this.tail = tail.next;
		this.tail.prev = oldTail;
		
	}
	
	public void addToHead(DataType d) {
		this.head = new MultiLevelDoubleLinkedListElement<DataType>(d,null,this.head);
		if(this.tail == null)tail = head;
	}
	
	public MultiLevelDoubleLinkedListElement<DataType> getHead(){
		return head;
	}
	public MultiLevelDoubleLinkedListElement<DataType> getTail(){
		return tail;
	}

	@Override
	public Iterator<DataType> iterator() {
		return new Iterator<DataType>(){
			MultiLevelDoubleLinkedListElement<DataType> curr = MultiLevelDoubleLinkedList.this.head;
			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public DataType next() {
				DataType dRet = curr.data;
				curr = curr.next;
				return dRet;
			}

			@Override
			public void remove() {
				if(curr==null) return;
				MultiLevelDoubleLinkedListElement<DataType> prev = curr.prev;
				prev.next = curr.next;
				curr.next.prev = prev;
			}
			
		};
	}

	public Iterable<MultiLevelDoubleLinkedListElement<DataType>> entryIterable() {
		return new Iterable<MultiLevelDoubleLinkedListElement<DataType>>(){

			@Override
			public Iterator<MultiLevelDoubleLinkedListElement<DataType>> iterator() {
				return new Iterator<MultiLevelDoubleLinkedListElement<DataType>>(){
					MultiLevelDoubleLinkedListElement<DataType> curr = MultiLevelDoubleLinkedList.this.head;
					@Override
					public boolean hasNext() {
						return curr != null;
					}

					@Override
					public MultiLevelDoubleLinkedListElement<DataType> next() {
						MultiLevelDoubleLinkedListElement<DataType> dRet = curr;
						curr = curr.next;
						return dRet;
					}

					@Override
					public void remove() {
						if(curr==null) return;
						MultiLevelDoubleLinkedListElement<DataType> prev = curr.prev;
						prev.next = curr.next;
						curr.next.prev = prev;
					}
					
				};
			}
			
		};
	}

	public void addChild(MultiLevelDoubleLinkedListElement<DataType> children) {
		addChildToTail(children);
	}

	private void addChildToTail(MultiLevelDoubleLinkedListElement<DataType> children) {
		this.getTail().child = children;
	}

	public void flatten() {
		MultiLevelDoubleLinkedListElement<DataType> curr = head;
		while(curr != null){
			appendChildren(curr,this);
			curr = curr.next;
		}
		
	}
	
	
	private void appendChildren(MultiLevelDoubleLinkedListElement<DataType> node,MultiLevelDoubleLinkedList<DataType> list) {
		MultiLevelDoubleLinkedListElement<DataType> nodeChild = node.child;
		if(nodeChild !=null){
			list.tail.next = node.child;
			nodeChild.prev = list.tail;
			
			for(;nodeChild.next!=null;nodeChild=nodeChild.next);
			
			list.tail = nodeChild;
		}
	}

	public void inflate() {
		exploreAndInflate(head);
		// Go through the list and correct the tail
		MultiLevelDoubleLinkedListElement<DataType> curr = head;
		for(;curr.next != null; curr = curr.next);
		tail = curr;
	}

	private void exploreAndInflate(MultiLevelDoubleLinkedListElement<DataType> node) {
		MultiLevelDoubleLinkedListElement<DataType> curr = node;
		while(curr!=null){
			if(curr.child!=null){
				// If the child is attached
				if(curr.child.prev != null){
					// Detach the child
					curr.child.prev.next = null;
					curr.child.prev = null;
					// Detach it's children
					exploreAndInflate(curr.child);
				}
			}
			curr = curr.next;
			 
		}
	}
}
