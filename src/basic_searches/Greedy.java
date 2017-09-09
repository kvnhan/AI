/**
 * 
 */
package basic_searches;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Path;

/**
 * @author jmetzger
 *
 */
public class Greedy {
	
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public Greedy() {
		
	}
	
	public void greedySearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
	
		for (Path n : new_paths) {
			pathqueue.addLast(n);
			}
		for (Path p: pathqueue){
			p.setDist(heuristic_dict.get(p.getP().getFirst().getName()));
		}
		Collections.sort(pathqueue, new Comparator<Path>(){
			   @Override
			   public int compare(Path o1, Path o2){
			        if(o1.getDist().compareTo(o2.getDist()) < 0){
			           return -1; 
			        }
			        if(o1.getDist().compareTo(o2.getDist()) > 0){
			           return 1; 
			        }
			        return 0;
			   }
			}); 
	}

}
