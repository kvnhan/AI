/**
 * 
 */
package basic_searches;

import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Node;
/**
 * @author jmetzger kvnhan
 *
 */
public class DFS {
	
	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();

	public DFS(){
	
	}
	
public void DFS(Graph graph, Node node){
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		path = startState.getPathOf();
		queue.addFirst(path);
		
		for(Node child: path){
			if(child.adjacentNodes.isEmpty()){
				child.adjacentNodes = path;
				child.adjacentNodes.addFirst(child);
			}else{
				child.adjacentNodes.addFirst(child);
			}
		}
		
		System.out.println("Hello");
		return;			
	}

}
