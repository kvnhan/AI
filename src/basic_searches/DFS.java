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
	LinkedList<String> visited = new LinkedList<String>();
	LinkedList<String> dups = new LinkedList<String>();
	LinkedList<Node> visitedNode = new LinkedList<Node>();
	int found = 0;


	public DFS(){
	
	}
	
public boolean dfs(Graph graph, Node node){
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		// Find S Node
		if(visited.isEmpty()){
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			visited.add(startState.getName());
			visitedNode.add(startState);
			System.out.println("Expand " + visited.getFirst() + "\n");
			path = graph.getChildrenOf(node);
			path = sortPath(path, node);
		}else{
			
			path = graph.getChildrenOf(node);
			path = removedVisitedPath(path);
			path = sortPath(path, node);
			//printQueue(path);
			
			
		}
		
		// Dead End
		if(path.size() == 0){
			System.out.println("Dead End " + node.getName());
			graph.setVisited(node);
			node = visitedNode.getFirst();
			
		}
		
		// Get A list of Adjacent Node
		for(Node child: path){
			if(child.getAdjacentNodes().size() == 0){
				child.setAdjacentNodes(visitedNode);
				child.getAdjacentNodes().addFirst(child);
				printQueue(child.getAdjacentNodes());
			}
		}
		
		// Traversing the graph to Find G Node
		for(Node c: path){
			if(c.visited == 0 && found == 0){
				if(!c.getName().equals("G")){
					System.out.println("Expand " + c.getName() + "\n");
					c.setvisted();
					//System.out.println(c.getName() + " " + c.visited);
					visitedNode.addFirst(c);
					visited.addFirst(c.getName());
					dfs(graph, c);
				}else{
					System.out.println("Found " + c.getName());
					System.out.println("goal reached!\n");
					found = 1;
					return true;
				}
			}
		}
		
		return false;
	}

	public void printQueue(LinkedList<Node> list){
		for(Node n: list){
			System.out.print(" " + n.getName() + " ");
		}
		System.out.println();
	}
	
	public LinkedList<Node> removedVisitedPath(LinkedList<Node> p){
		LinkedList<Node> l = new LinkedList<Node>();
		for(Node n: p){
			//System.out.println(n.getName() + " " + n.visited);
			if(n.visited == 0 && !visited.contains(n.getName())){
				//System.out.println(n.getName() + " " + n.visited);
				l.addFirst(n);
			}
			
			
		}
		return l;
	}

	public LinkedList<Node> sortPath(LinkedList<Node> list, Node n){
		double temp = 0.0;
		LinkedList<Node> newPath = new LinkedList<Node>();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getDistanceTo(n) > temp && !dups.contains(list.get(i).getName())){
				newPath.addLast(list.get(i));
				temp = list.get(i).getDistanceTo(n);
				
			}else{
				newPath.addFirst(list.get(i));
				dups.addFirst(list.get(i).getName());
			}
		}
		
		return newPath;	
	}
	

}
