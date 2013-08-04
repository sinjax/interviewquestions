package net.sinjax.techinterview.collection;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDoubleLinkedList {
	@Test public void testAddHeadRemoveHeadTail(){
		MultiLevelDoubleLinkedList<Integer> childHolder = null;
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(5);
		s.addChild(childrenOf5());
		s.add(33);
		s.add(17);
		s.add(2);
		s.addChild(childrenOf2_1());
		s.add(1);
		System.out.println(s.getHead());
		s.flatten();
		System.out.println(s.getHead());
		s.inflate();
		System.out.println(s.getHead());
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf2_1() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(2);
		s.addChild(childrenOf2_2());
		s.add(7);
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf2_2() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(12);
		s.addChild(childrenOf12());
		s.add(5);
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf12() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(21);
		s.add(3);
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf5() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(6);
		s.add(25);
		s.addChild(childrenOf25());
		s.add(6);
		s.addChild(childrenOf6());
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf6() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(9);
		s.addChild(childrenOf9());
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf9() {
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(7);
		return s.head;
	}

	private MultiLevelDoubleLinkedListElement<Integer> childrenOf25(){
		MultiLevelDoubleLinkedList<Integer> s = new MultiLevelDoubleLinkedList<Integer>();
		s.add(8);
		return s.head;
	}
	
}
