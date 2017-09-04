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
	LinkedList<Node> visited = new LinkedList<Node>();

	public DFS(){
	
	}
	
public void dfs(Graph graph, Node node){
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		
		// Find S Node
		if(visited.isEmpty()){
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			visited.add(startState);
			System.out.println("Expand " + visited.getFirst().getName());
			path = graph.getChildrenOf(node);
			path = sortPath(path, node);
		}else{
			
			path = graph.getChildrenOf(node);
			path = removedVisitedPath(visited, path);
			sortPath(path, node);
			
			
		}
		
		// Dead End
		if(path.size() == 0){
			System.out.println("Dead End " + node.getName());
			graph.setVisited(node);
			node = visited.getFirst();
			
		}
		
		// Get A list of Adjacent Node
		for(Node child: path){
			if(child.getAdjacentNodes().size() == 0){
				child.setAdjacentNodes(visited);
				child.getAdjacentNodes().addFirst(child);
				printQueue(child.getAdjacentNodes());
			}
		}
		
		// Traversing the graph to Find G Node
		for(Node c: path){
			if(c.visited == 0){
				if(!c.getName().equals("G")){
					System.out.println("Expand " + c.getName());
					c.setvisted();
					visited.addFirst(c);
					dfs(graph, c);
				}else{
					
					System.out.println("Found " + c.getName());
					return;
				}
			}
		}		
	}

	public void printQueue(LinkedList<Node> list){
		for(Node n: list){
			System.out.print(" " + n.getName() + " ");
		}
		System.out.println();
	}
	
	public LinkedList<Node> removedVisitedPath(LinkedList<Node> v, LinkedList<Node> p){
		System.out.println(v.size());
		System.out.println(p.size());
		LinkedList<Node> newlist = new LinkedList<Node>();
		for(Node node: v){
			for(Node no: p){
				if(!no.getName().equals(node.getName())){
					if(no.visited == 0){
						newlist.addFirst(no);
					}
				}
			}
		}
		System.out.println("After");
		System.out.println(newlist.size());
		printQueue(newlist);
		return newlist;
	}

	public LinkedList<Node> sortPath(LinkedList<Node> list, Node n){
		double temp = 0.0;
		LinkedList<Node> newPath = new LinkedList<Node>();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getDistanceTo(n) > temp){
				newPath.addLast(list.get(i));
				temp = list.get(i).getDistanceTo(n);
				
			}else{
				newPath.addFirst(list.get(i));
			}
		}
		
		return newPath;
		
	}
	
	public void removeDups(LinkedList<Node> list){
		LinkedList<Node> nl = new LinkedList<Node>();
		Node currentNode = list.getFirst();
		
	}
}
