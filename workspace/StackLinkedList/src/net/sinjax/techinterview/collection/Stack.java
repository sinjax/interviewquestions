package net.sinjax.techinterview.collection;

public interface Stack<DataType> extends Iterable<DataType>{
	public void push(DataType t);
	public DataType peek();
	public DataType pop();
}
