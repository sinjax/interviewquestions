package net.sinjax.techinterview.collection;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSingleLinkedList {
	@Test public void testAddHeadRemoveHeadTail(){
		LinkedListStack<Integer> s = new LinkedListStack<Integer>();
		for(int i = 0; i < 10; i++){
			s.addToHead(i);
		}
		s.removeHead();
		s.removeTail();
		assertTrue(s.getHead().data.equals(8));
		assertTrue(s.getTail().data.equals(1));
	}
	@Test public void testAddTailRemoveHeadTail(){
		LinkedListStack<Integer> s = new LinkedListStack<Integer>();
		for(int i = 0; i < 10; i++){
			s.addToTail(i);
		}
		s.removeHead();
		s.removeTail();
		assertTrue(s.getHead().data.equals(1));
		assertTrue(s.getTail().data.equals(8));
	}
	
	@Test public void testSimpleCases(){
		LinkedListStack<Integer> s = new LinkedListStack<Integer>();
		s.removeHead();
		s.removeTail();
		assertTrue(s.size() == 0);
		s.add(1);
		s.removeHead();
		assertTrue(s.size() == 0);
		s.add(1);
		s.removeTail();
		assertTrue(s.size() == 0);
		s.add(1);
		s.add(2);
		s.removeHead();
		assertTrue(s.size() == 1);
		assertTrue(s.getHead().data.equals(2));
		assertTrue(s.getTail().data.equals(2));
		s.addToHead(1);
		s.removeTail();
		assertTrue(s.size() == 1);
		assertTrue(s.getHead().data.equals(1));
		assertTrue(s.getTail().data.equals(1));
		s.addToTail(2);
		s.remove(2);
		assertTrue(s.size() == 1);
		assertTrue(s.getHead().data.equals(1));
		assertTrue(s.getTail().data.equals(1));
		s.addToTail(2);
		s.remove(1);
		assertTrue(s.size() == 1);
		assertTrue(s.getHead().data.equals(2));
		assertTrue(s.getTail().data.equals(2));
		s.addToHead(1);
		s.addToTail(3);
		s.remove(2);
		assertTrue(s.getHead().data.equals(1));
		assertTrue(s.getTail().data.equals(3));
	}
	
	@Test public void testFindMthToLast(){
		LinkedListStack<Integer> s = new LinkedListStack<Integer>();
		for(int i = 0; i < 10; i++){
			s.addToHead(i);
		}
		
		assertTrue(s.findFromEnd(3).equals(3));
		assertTrue(s.findFromEnd(0).equals(0));
		assertTrue(s.findFromEnd(9).equals(9));
		assertTrue(s.findFromEnd(11) == null);
	}
	
	@Test public void testIsCyclic(){
		LinkedListStack<Integer> s = new LinkedListStack<Integer>();
		for(int i = 0; i < 10; i++){
			s.addToHead(i);
		}
		assertTrue(!s.isCyclic());
		s.getTail().next = s.find(5);
		assertTrue(s.isCyclic());
	}
}