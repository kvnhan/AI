/**
 * 
 */
package basic_searches;

import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Node;
/**
 * @author jmetzger kien
 *
 */
public class DFS {
	
LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	
public void DFS(Graph graph, Node node){
		
		LinkedList<Node> path = new LinkedList<Node>();
		
		// Find S
		path = graph.findS();
		
		queue.addFirst(path);
		
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
