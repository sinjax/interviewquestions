
public class BinaryTreeNode<DataType extends Comparable<DataType>> {
	public BinaryTreeNode<DataType> left = null;
	public BinaryTreeNode<DataType> right = null;
	public DataType value = null;
	public BinaryTreeNode(BinaryTreeNode<DataType> left, BinaryTreeNode<DataType> right, DataType value) {
		this.left = left;
		this.right = right;
		this.value = value;
	}
	
	public BinaryTreeNode() {
		
	}

	public static <DataType extends Comparable<DataType>>BinaryTreeNode<DataType> create(DataType d){
		return new BinaryTreeNode<DataType>(null,null,d);
	}
}
