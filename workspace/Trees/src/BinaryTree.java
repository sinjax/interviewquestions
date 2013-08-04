import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree <DataType extends Comparable<DataType>>{
	
	public BinaryTreeNode<DataType> root;
	
	public BinaryTree(){
		this.root = new BinaryTreeNode<DataType>();
	}
	
	public void add(DataType d){
		addToNode(d,root);
	}

	private void addToNode(DataType d, BinaryTreeNode<DataType> node) {
		if(node.value == null){
			node.value = d;
			return;
		}
		else{
			int cmp = d.compareTo(node.value);
			if(cmp == 0) node.value = d;
			else if(cmp == -1){
				if(node.left == null)
					node.left = new BinaryTreeNode<DataType>();				
				addToNode(d,node.left);
			}
			else{
				if(node.right == null)
					node.right = new BinaryTreeNode<DataType>();				
				addToNode(d,node.right);
			}
		}
	}

	public List<DataType> preorderTraversal() {
		List<DataType> ret = new ArrayList<DataType>();
		preorderTraverse(ret,root);
		return ret;
	}

	private void preorderTraverseRecursive(List<DataType> ret,BinaryTreeNode<DataType> node) {
		ret.add(node.value);
		if(node.left!=null) preorderTraverseRecursive(ret, node.left);
		if(node.right!=null) preorderTraverseRecursive(ret, node.right);
	}
	
	private void preorderTraverse(List<DataType> ret,BinaryTreeNode<DataType> node) {
		BinaryTreeNode<DataType> currentSearch = root;
		Stack<BinaryTreeNode<DataType>> rightSearch = new Stack<BinaryTreeNode<DataType>>();
		while(currentSearch!=null || rightSearch.size() > 0){
			if(currentSearch!=null)
			{
				ret.add(currentSearch.value);
				rightSearch.push(currentSearch);
				currentSearch = currentSearch.left;
			}
			else
				currentSearch = rightSearch.pop().right;
		}	
	}
	
	public DataType lowestCommonAncestor(DataType a, DataType b){
		if(a.compareTo(b) == 0)
			return parent(a,root);
		return lowestCommonAncestor(a,b,root);
	}

	private DataType parent(DataType a, BinaryTreeNode<DataType> current) {
		String s = null ;
		s += " ";
		while(current!=null){
			if(current.left.value.compareTo(a) == 0 || current.right.value.compareTo(a) == 0 ) 
				return current.value;
			int aCmp = a.compareTo(current.value);
			if(aCmp == 0)
				return current.value;
			else if(aCmp < 0)
				current = current.left;
			else
				current = current.right;
		}
		return null;
		
		
	}

	private DataType lowestCommonAncestor(DataType a, DataType b, BinaryTreeNode<DataType> current) {
		while(current!=null)
		{
			int aCmp = a.compareTo(current.value);
			int bCmp = b.compareTo(current.value);
			if(aCmp!=bCmp){
				return current.value;
			}
			else{
				if(aCmp < 0)
					current = current.left;
				else
					current = current.right;
			}
		}
		return null;
	}
	
}
