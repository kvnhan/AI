/**
 * 
 */
package general_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Edge;
import SetUp.Graph;
import SetUp.Node;
import SetUp.Path;
import basic_searches.A_Star;
import basic_searches.Breadth_First;
import basic_searches.Depth_First;
import basic_searches.Depth_Limited;
import basic_searches.Greedy;
import basic_searches.Iterative_Deepening;
import basic_searches.Uniform_Cost;

/**
 * @author jmetzger
 *
 */
public class general_search {
	
	LinkedList<Node> visited;
	LinkedList<Path> pathqueue;
	boolean found = true;
	
	public general_search() {
		
	}
	
	public Node search(Graph g, String method){
		
		LinkedList<Node> nodepath = new LinkedList<Node>();
		Node start_node = g.getS();
		nodepath.add(start_node);
		Path path = new Path(nodepath, 0.0);
		pathqueue.add(path);
		found = false;
		
		
		while(true){
			
			if(found) {
				return null;
			}
			if (pathqueue.isEmpty()){
				System.out.print("\nFail");
				
				return null;
			}
			
			g.printQueue2(pathqueue);
			
			Path curr_path = pathqueue.remove();
			Node curr_node = curr_path.getP().getFirst();
			visited.add(curr_node);
			if (curr_node.getName().equalsIgnoreCase("G")){
				return curr_node;
			}
			LinkedList<Node> new_nodes = g.getChildrenOf(curr_node);
			Collections.sort(new_nodes, new Comparator<Node>(){
				   @Override
				   public int compare(Node o1, Node o2){
				        if(o1.getName().compareTo(o2.getName()) < 0){
				           return -1; 
				        }
				        if(o1.getName().compareTo(o2.getName()) > 0){
				           return 1; 
				        }
				        return 0;
				   }
				}); 

			LinkedList<Path> new_paths = new LinkedList<Path>();

			for (Node n : new_nodes) {
				Boolean in_path = false;
				LinkedList<Node> p = new LinkedList<Node>();
				for(Node i : path.getP()){
					if(n.getName().equalsIgnoreCase(i.getName())){
						in_path = true;
					}
				}

				if (!in_path){
			   Double newcost = 0.0;
			   if(!path.getP().isEmpty()){
			   newcost = path.getDist() + g.distance(path.getP().getFirst(), n);
			   }
				p.addAll(path.getP());
				p.addFirst(n);
				Path newpath = new Path(p, newcost);
				new_paths.add(newpath);
				}
				else{
				}
			}		
			
			g.AddToQueue(new_paths, method);	
			if(!pathqueue.isEmpty()) {
				path = pathqueue.getFirst();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Failed");
				e.printStackTrace();
			}
		}
	}

}
