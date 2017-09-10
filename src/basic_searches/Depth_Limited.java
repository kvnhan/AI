/**
 * 
 */
package basic_searches;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import SetUp.Path;

/**
 * @author jmetzger kvnhan
 */

public class Depth_Limited {
	
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public Depth_Limited() {
		
	}
	
	// TODO: what happens if not found goal???
	public void depthLimitedSearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
		Stack<Path> pathstack = new Stack<Path>();
		
		for (Path n : new_paths) {
			pathstack.push(n);
		}
		while(!pathstack.isEmpty()){
			Path curr = pathstack.pop();			
			if(curr.getP().size() <= 3){
				pathqueue.addFirst(curr);
			}
		}
	}
}
