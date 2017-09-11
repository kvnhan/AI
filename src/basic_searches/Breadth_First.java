package basic_searches;
import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Path;

/**
 * Breadth_First.java
 * @author jmetzger kvnhan jwilder
 */

public class Breadth_First {
	
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public Breadth_First() {}
	
	public void breadthSearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
		
		for (Path n : new_paths) {
			pathqueue.addLast(n);
			}
	}
}