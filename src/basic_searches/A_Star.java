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
public class A_Star {
	
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public A_Star() {
		
	}
	
	public void aSearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
		for (Path n : new_paths) {
			if(n.getP().size() > 2){
			n.setDist(n.getDist() + heuristic_dict.get(n.getP().getFirst().getName()) - heuristic_dict.get(n.getP().get(1).getName()));
			}
			else{
				n.setDist(n.getDist() + heuristic_dict.get(n.getP().getFirst().getName()));
			}
			pathqueue.addLast(n);
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
