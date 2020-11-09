package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {
	
	private static final int ELEMS = 100_000;
	private static final int TO_MS = 1_000_000;
	private static final int READS = 1_000;

    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	final List<Integer> list1 = new ArrayList<>();
    	for (int i = 1000; i < 2000; i++) {
    		list1.add(i);
    	}
    	
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	final List<Integer> list2 = new LinkedList<>(list1);
    	
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	final int temp = list1.get(list1.size() - 1);
    	list1.set(list1.size() - 1, list1.get(0));
    	list1.set(0, temp);
    	
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	for (final int n : list1) {
    		System.out.print(n + " ");
    	}
    	System.out.println();
    	
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	long time = System.nanoTime();
    	for (int i = 0; i < ELEMS; i++) {
    		list1.set(0, i);
    	}
    	long time1 = System.nanoTime() - time;
    	
    	time = System.nanoTime();
    	for (int i = 0; i < ELEMS; i++) {
    		list2.set(0, i);
    	}
    	long time2 = System.nanoTime() - time;
    	
    	System.out.println("Time required to insert " + ELEMS + " elements as first element in an ArrayList: " + time1 + "ns (" + time1 / TO_MS + "ms)");
    	System.out.println("Time required to insert " + ELEMS + " elements as first element in a LinkedList: " + time2 + "ns (" + time2 / TO_MS + "ms)");
    	
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
    	time = System.nanoTime();
    	for (int i = 0; i < READS; i++) {
    		list1.get(list1.size() / 2);
    	}
    	time1 = System.nanoTime() - time;
    	
    	time = System.nanoTime();
    	for (int i = 0; i < READS; i++) {
    		list2.get(list2.size() / 2);
    	}
    	time2 = System.nanoTime();
    	
    	System.out.println("Time required to read " + READS + " elements in the middle of an ArrayList: " + time1 + "ns (" + time1 / TO_MS + "ms)");
    	System.out.println("Time required to read " + READS + " elements in the middle of a LinkedList: " + time2 + "ns (" + time2 / TO_MS + "ms)");
    	
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
    	final Map<String, Long> world = new HashMap<>();
    	world.put("Africa", 1110635000L);
    	world.put("Americas", 972005000L);
    	world.put("Antarctica", 0L);
    	world.put("Asia", 4298723000L);
    	world.put("Europe", 742452000L);
    	world.put("Oceania", 38304000L);
    	
        /*
         * 8) Compute the population of the world
         */
    	long population = 0;
    	for (final long pop : world.values()) {
    		population += pop;
    	}
    	
    	System.out.println("World population: " + population);
    }
}
