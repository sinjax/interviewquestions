import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class BinaryTreeTest {
	
	@Test public void testPreorderTraversal(){
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.add(100);
		tree.add(50);
		tree.add(150);
		tree.add(25);
		tree.add(75);
		tree.add(125);
		tree.add(175);
		tree.add(110);
		List<Integer> preorder = tree.preorderTraversal();
		assertTrue(preorder.get(0) == 100);
		assertTrue(preorder.get(1) == 50);
		assertTrue(preorder.get(2) == 25);
		assertTrue(preorder.get(3) == 75);
		assertTrue(preorder.get(4) == 150);
		assertTrue(preorder.get(5) == 125);
		assertTrue(preorder.get(6) == 110);
		assertTrue(preorder.get(7) == 175);
	}
	
	@Test public void testLowestCommonAncestor(){
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		tree.add(20);
		tree.add(8);
		tree.add(22);
		tree.add(4);
		tree.add(12);
		tree.add(10);
		tree.add(14);
		
		Integer common = tree.lowestCommonAncestor(14,4);
		assertTrue(common == 8);
	}
}
