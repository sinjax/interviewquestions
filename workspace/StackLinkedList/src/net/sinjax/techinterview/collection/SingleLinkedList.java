package net.sinjax.techinterview.collection;


public class SingleLinkedList<DataType> {
	
	private SingleLinkedListElement<DataType> head;
	private SingleLinkedListElement<DataType> tail;

	public SingleLinkedList(){
		this.head = null;
		this.tail = null;
	}
	
	public void add(DataType d){
		addToTail(d);
	}

	public void addToTail(DataType d) {
		if(this.tail == null) // 0 elements in list
		{
			this.tail = new SingleLinkedListElement<DataType>(d);
			this.head = this.tail;
			return;
		}
		this.tail.next = new SingleLinkedListElement<DataType>(d);
		this.tail = tail.next;
		
	}
	
	public void addToHead(DataType d) {
		this.head = new SingleLinkedListElement<DataType>(d,this.head);
		if(this.tail == null)tail = head;
	}
	
	public SingleLinkedListElement<DataType> getHead(){
		return head;
	}
	public SingleLinkedListElement<DataType> getTail(){
		return tail;
	}
	
	public void removeHead(){
		remove(head);
	}
	
	public void removeTail(){
		remove(tail);
	}
	
	public void remove(SingleLinkedListElement<DataType> toRemove){
		if(head == null) return; // The list is empty
		if(head == toRemove){
			head = head.next;
			if(head == null){ // The list has 1 element
				tail = null;				
			}
			return;
		}
		// Must be at least 2 long
		SingleLinkedListElement<DataType> previous = head;
		SingleLinkedListElement<DataType> current = head.next;
		while(current!=null && current!=toRemove){
			previous = current;
			current = current.next;
		}
		if(current!=null) // Then it was found
		{
			// Remove the element
			previous.next = null;
			if(current == tail){ // tail removed, update tail
				tail = previous;
			}
		}
		
	}
	
	public void remove(DataType data) {
		if(head == null) return; // The list is empty
		if(head.data == data){
			head = head.next;
			if(head == null){ // The list has 1 element
				tail = null;				
			}
			return;
		}
		// Must be at least 2 long
		SingleLinkedListElement<DataType> previous = head;
		SingleLinkedListElement<DataType> current = head.next;
		while(current!=null && current.data!=data){
			previous = current;
			current = current.next;
		}
		if(current!=null) // Then it was found
		{
			// Remove the element
			previous.next = null;
			if(current == tail){ // tail removed, update tail
				tail = previous;
			}
		}
	}
	
	public SingleLinkedListElement<DataType> find(DataType data) {
		SingleLinkedListElement<DataType> curr = head;
		while(curr!=null){
			if(curr.data.equals(data))
				return curr;
			curr = curr.next;
		}
		return null;
	}

	public int size() {
		int i = 0;
		SingleLinkedListElement<DataType> curr = head;
		while(curr!=null){
			curr = curr.next;
			i++;
		}
		return i;
	}
	
	public DataType findFromEnd(int i) {
		int done = -1;
		SingleLinkedListElement<DataType> curr = head;
		SingleLinkedListElement<DataType> found = head;
		// Go until the current pointer is i from the beggining
		while(done < i && curr !=null){
			curr = curr.next;
			done++;
		}
		// Now as you are incrementing curr, also increment found until you hit the end
		// found is i from the end
		while(curr!=null){
			curr = curr.next;
			found = found.next;
		}
		
		if(done == i) // we found n from the end
			return found.data;
		return null;
	}
	
	public boolean isCyclic(){
		SingleLinkedListElement<DataType> fast = this.head;
		SingleLinkedListElement<DataType> slow = this.head;
		while(true){
			if(fast == null || fast.next == null){
				return false;
			}
			else
			{
				fast = fast.next.next;
				slow = slow.next;
				if(fast == slow || (fast!=null && fast.next == slow)) 
					return true;
			}
		}
	}
}
