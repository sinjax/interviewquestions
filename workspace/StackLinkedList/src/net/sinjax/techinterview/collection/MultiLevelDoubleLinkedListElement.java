package net.sinjax.techinterview.collection;

public class MultiLevelDoubleLinkedListElement<T>  {
	public MultiLevelDoubleLinkedListElement<T> prev;
	public MultiLevelDoubleLinkedListElement<T> next;
	public MultiLevelDoubleLinkedListElement<T> child;
	public T data;
	
	public MultiLevelDoubleLinkedListElement(T d) {
		this.data = d;
	}
	public MultiLevelDoubleLinkedListElement(T d,MultiLevelDoubleLinkedListElement<T>  prev, MultiLevelDoubleLinkedListElement<T>  next) {
		this.data = d;
		this.next = next;
		this.prev = prev;
	}
	
	public String toString(){
		MultiLevelDoubleLinkedListElement<T> curr = this;
		return levelToString(curr,0) + "\n";
	}
	private String levelToString(MultiLevelDoubleLinkedListElement<T> curr,int tabDepth) {
		String out = "";
		for(int i= 0; i < tabDepth; i++){
			out += "\t";
		}
		out += curr.data;
		if(curr.child!=null)
			out += "\n" + levelToString(curr.child,tabDepth + 1);
		if(curr.next!=null)
			out += "\n" + levelToString(curr.next,tabDepth);
		return out;
	}
}
