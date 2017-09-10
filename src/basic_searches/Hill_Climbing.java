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
public class Hill_Climbing {
	LinkedList<Path> pathqueue;
	LinkedList<Path> new_paths;
	HashMap<String, Double> heuristic_dict;
	
	public Hill_Climbing() {
		
	}
	
	public void hillClimbingSearch(LinkedList<Path> pathqueue, LinkedList<Path> new_paths, HashMap<String, Double> heuristic_dict) {
if(pathqueue.size() > 0){
		Collections.sort(new_paths, new Comparator<Path>(){
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
else{
		Collections.sort(new_paths, new Comparator<Path>(){
			   @Override
			   public int compare(Path o1, Path o2){
			        if(o1.getDist().compareTo(o2.getDist()) > 0){
			           return -1; 
			        }
			        if(o1.getDist().compareTo(o2.getDist()) < 0){
			           return 1; 
			        }
			        return 0;
			   }
			});
}

		//for (Path n : new_paths) {
		//System.out.println("NEWPATH: " + n.getP().getFirst().getName());
		//}
			Collections.reverse(new_paths);

		for (Path n : new_paths) {
			n.setDist(heuristic_dict.get(n.getP().getFirst().getName()));
			pathqueue.addFirst(n);
			}

	}
}
