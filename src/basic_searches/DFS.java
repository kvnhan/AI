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
	
public void dfs(Graph graph, Node node){
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		
		if(startState.getName().equals("S")){
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
		}else{
			path = graph.getChildrenOf(node);
		}
		for(Node child: path){
			if(child.getAdjacentNodes().size() == 0){
				System.out.println(child.getName());
				child.setAdjacentNodes(startQueue);
				child.getAdjacentNodes().addFirst(child);
				printQueue(child.getAdjacentNodes());
			}else{
				child.getAdjacentNodes().addFirst(child);
				System.out.println(child.getName());
				printQueue(child.getAdjacentNodes());


			}
		}
		
		for(Node c: path){
			if(c.visited == 0){
				if(!c.getName().equals("G")){
					c.setvisted();
					dfs(graph, c);
				}else{
					System.out.println("Found " + c.getName());
					return;
				}
			}
		}
	
		return;			
	}

	public void printQueue(LinkedList<Node> list){
		for(Node n: list){
			System.out.print(" " + n.getName() + " ");
		}
		System.out.println();
	}

}
