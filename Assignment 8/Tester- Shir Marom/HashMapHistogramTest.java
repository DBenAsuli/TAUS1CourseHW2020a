package il.ac.tau.cs.sw1.ex8.histogram;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class HashMapHistogramTest {
	
	
	@Test
	public void test1() {
		
		HashMapHistogram<String> hm = new HashMapHistogram<String>();
		
		hm.addItem("a");
		assertEquals(hm.getCountForItem("a"), 1);
		
		hm.addItem("a");
		assertEquals(hm.getCountForItem("a"), 2);
		
		try {
			hm.addItemKTimes("b", -1);
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
			
		}
		
		try {
			hm.addItemKTimes("b", -5);
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
			
		}
		
		assertEquals(hm.getCountForItem("b"), 0);
		assertEquals(hm.getCountForItem("c"), 0);
	}
	
	@Test
	public void test2() {
		
		HashMapHistogram<String> hm = new HashMapHistogram<String>();
		
		hm.addItem("a");
		try {
			hm.removeItem("a");
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		}
		
		assertEquals(hm.getCountForItem("a"), 0);
		
		try {
			hm.removeItem("a");
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			
		}
		
		try {
			hm.removeItem("b");
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			
		}
		
	}
	
	@Test
	public void test3() {
		
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		try {
			hm.addItemKTimes(2, 8);
		} catch (IllegalKValueException e) {
			fail("should not reach this line");
		}
		assertEquals(hm.getCountForItem(2), 8);
		
		try {
			hm.removeItem(2);
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		}
		System.out.println(hm.getCountForItem(2));
		assertEquals(hm.getCountForItem(2), 7);
		
		try {
			hm.removeItemKTimes(2, 7);
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
			fail("should not reach this line");
		}
		
		assertEquals(hm.getCountForItem(2), 0);
		
	}

	@Test
	public void test4() {
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		try {
			hm.removeItemKTimes(10, -5);
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			
		} catch (IllegalKValueException e) {
			fail("should not reach this line");
		}
		
		try {
			hm.removeItemKTimes(10, 0);
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			
		} catch (IllegalKValueException e) {
			fail("should not reach this line");
		}
		
		hm.addItem(5);
		try {
			hm.removeItemKTimes(5, 8);
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
	
		}
		assertEquals(hm.getCountForItem(5), 1);
		
		try {
			hm.removeItemKTimes(5, -1);
			fail("should not reach this line");
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
			
		}
	}
	
	@Test
	public void test5() {
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		Integer[] items = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        List<Integer> list = Arrays.asList(items);

		hm.addAll(list);
		
		for(int i = 1; i <= 4; i++) {
			assertEquals(hm.getCountForItem(i), i);
		}
		
		Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4));
		assertEquals(hm.getItemsSet(), expected);
		
		try {
			hm.removeItem(1);
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		}
		
		expected = new HashSet<>(Arrays.asList(2, 3, 4));
		assertEquals(hm.getItemsSet(), expected);
		assertEquals(hm.getCountForItem(1), 0);
		assertEquals(hm.getCountForItem(2), 2);
		assertEquals(hm.getCountForItem(3), 3);
		assertEquals(hm.getCountForItem(4), 4);
		
		try {
			hm.removeItemKTimes(2, 1);
			hm.removeItemKTimes(3, 3);
			hm.removeItemKTimes(4, 4);
		} catch (IllegalItemException e) {
			fail("should not reach this line");
		} catch (IllegalKValueException e) {
			fail("should not reach this line");
		}
		
		expected = new HashSet<>(Arrays.asList(2));
		assertEquals(hm.getItemsSet(), expected);
		assertEquals(hm.getCountForItem(1), 0);
		assertEquals(hm.getCountForItem(2), 1);
		assertEquals(hm.getCountForItem(3), 0);
		assertEquals(hm.getCountForItem(4), 0);
	}
	
	@Test 
	public void test6() {
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		Integer[] items = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        List<Integer> list = Arrays.asList(items);
        hm.addAll(list);
        hm.clear();
        for(int i = 0; i < 10; i++) {
        	assertEquals(hm.getCountForItem(i), 0);
        }
        Set<Integer> expected = new HashSet<>();
		assertEquals(hm.getItemsSet(), expected);
	}
	
	@Test
	public void test7() {
		HashMapHistogram<Integer> hm1 = new HashMapHistogram<Integer>();
		Integer[] items = {1, 3, 5};
        List<Integer> list = Arrays.asList(items);
        hm1.addAll(list);
  
        HashMapHistogram<Integer> hm2 = new HashMapHistogram<Integer>();
		items = new Integer[] {2, 4, 6};
        list = Arrays.asList(items);
        hm2.addAll(list);
        
        hm1.update(hm2);
        
        for(int i = 1; i <= 6; i++)
        	assertEquals(hm1.getCountForItem(i), 1);
        	
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertEquals(hm1.getItemsSet(), expected);
		
	}
	
	@Test
	public void test8() {
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		Integer[] items = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(items);
        hm.addAll(list);
        hm.update(hm);
        
        for(int i = 1; i <= 5; i++)
        	assertEquals(hm.getCountForItem(i), 2);
        
        Set<Integer> expected = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
		assertEquals(hm.getItemsSet(), expected);
	}
	
	@Test
	public void test9() {
		
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		Integer[] items = {1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5};
        List<Integer> list = Arrays.asList(items);
        hm.addAll(list);
        
        ArrayList<Integer> values = new ArrayList<>();
        
        for(Integer i : hm)
        	values.add(i);
        
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 5, 4, 1, 2));
        
		assertEquals(values, expected);
	}
	
	@Test
	public void test10() {
		
		HashMapHistogram<Integer> hm = new HashMapHistogram<Integer>();
		Integer[] items = {5, 4, 3, 2, 1};
        List<Integer> list = Arrays.asList(items);
        hm.addAll(list);
        
        ArrayList<Integer> values = new ArrayList<>();
        
        for(Integer i : hm)
        	values.add(i);
        
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
		assertEquals(values, expected);
	}
}
