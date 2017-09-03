/**
 * 
 */
package basic_searches;

import java.util.LinkedList;

package (default package);

/**
 * @author jmetzger kien
 *
 */
public class DFS {
	
	
public void DFS(Graph graph, Node node){
		
		LinkedList<Node> path = new LinkedList<Node>();
		
		// Find S
		for(Node n: graph.keySet()){
			if(n.getName().equals("S") && n.visited == 0){
				n.setvisted();
				n.adjacentNodes.add(n);
				path = n.adjacentNodes;
				queue.addFirst(path);
				break;
			}
		}
		
		for(Node child: path){
			if(child.adjacentNodes.isEmpty()){
				child.adjacentNodes = path;
				child.adjacentNodes.addFirst(child);
			}else{
				child.adjacentNodes.addFirst(child);
			}
		}
		return;			
	}

}
