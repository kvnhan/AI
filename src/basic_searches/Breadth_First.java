/**
 * Breath-First-Search Algorithm
 * @author jmetzger kvnhan
 */
package basic_searches;

import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Path;

public class Breadth_First {
	
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public Breadth_First() {
		
	}
	
	public void breadthSearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
		
		for (Path n : new_paths) {
			pathqueue.addLast(n);
			}
	}
}