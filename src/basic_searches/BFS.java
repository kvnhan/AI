/**
 * Breath-First-Search Algorithm
 * @author jmetzger kvnhan
 */
package basic_searches;

import java.util.Collections;
import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Node;

public class BFS {

	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	LinkedList<String> visited = new LinkedList<String>();
	LinkedList<String> DeadEnd = new LinkedList<String>();
	LinkedList<String> dups = new LinkedList<String>();
	LinkedList<String> expanded = new LinkedList<String>();
	LinkedList<Node> visitedNode = new LinkedList<Node>();
	int found = 0;
	Node prev = null;

	public BFS(){
	
	}
	
public boolean bfs(Graph graph, Node node, Node from){
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<LinkedList<Node>> dummyqueue = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		dups = new LinkedList<String>();
		

		// Find S Node
		if(visited.isEmpty()){
			node = startState;
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			visited.add(startState.getName());
			visitedNode.add(startState);
			path = graph.getChildrenOf(node);
			path = sortPath(path, node);
			expanded.add(node.getName());
			node.getAdjacentNodes().addFirst(node);
		}else{	
			path = graph.getChildrenOf(node);
			path = removePath(path, from);			
			// Dead End
			if(path.size() == 0){
				System.out.println("Expand " + node.getName());
				printQueue2(queue);
				graph.setVisited(node);
				queue = pop();
				node = queue.getFirst().get(0);
				from = queue.getFirst().get(1);
				path = graph.getChildrenOf(node);
				path = sortPath(path, node);	
			}else{
				path = sortPath(path, node);
				visitedNode.addFirst(node);
				visited.addFirst(node.getName());
				graph.setVisited(node);		
			}
		}
		// Get A list of Adjacent Node
		for(Node child: path){
			if(!child.getName().equals(from.getName()) && !IsDescendant(queue.getFirst(), child)){
				child.reset();
				child.setAdjacentNodes(queue.getFirst());
				child.getAdjacentNodes().addFirst(child);
				System.out.println();
				dummyqueue.addLast(child.getAdjacentNodes());	
			}
		}		
		System.out.println("Expand " + node.getName());
		queue = fixQueue(dummyqueue);
		printQueue2(queue);
		if(!expanded.contains(node.getName())){
			expanded.add(node.getName());
		}
		// Traversing the graph to Find G Node
		if(!queue.getFirst().getFirst().getName().equals("G")){
			queue = pop();
			Node newnode = queue.getFirst().get(0);
			Node fromNode = queue.getFirst().get(1);
			bfs(graph, newnode, fromNode);
		}else{
			System.out.println("Goal Reached!");
		}
		
		return false;
	}

	public LinkedList<LinkedList<Node>> fixQueue(LinkedList<LinkedList<Node>> dummyqueue){
		for(LinkedList<Node> node: dummyqueue){
			queue.addLast(node);
		}
		return queue;
	}
	
	public LinkedList<LinkedList<Node>> pop(){
		queue.removeFirst();
		return queue;
	}
	
	public void printQueue(LinkedList<Node> list){
	
		int size = list.size();
		int count = 0;
		for(Node n: list){
			count++;
			if(size == count){
				System.out.print("" + n.getName() + "");
			}else{
				System.out.print("" + n.getName() + ",");	
			}
		}		
	}
	
	public LinkedList<Node> removePath(LinkedList<Node> p, Node node){
		LinkedList<Node> l = new LinkedList<Node>();
		for(Node n: p){
			if(!n.getName().equals(node.getName())){
				//System.out.println(n.getName() + " " + n.visited);
				l.addFirst(n);
			}						
		}
		return l;
	}
	
	public LinkedList<Node> removedVisitedPath(LinkedList<Node> p){
		LinkedList<Node> l = new LinkedList<Node>();
		for(Node n: p){
			if(n.visited == 0 && !visited.contains(n.getName())){
				l.addFirst(n);
			}
			
			
		}
		return l;
	}

	public LinkedList<Node> sortPath(LinkedList<Node> list, Node n){
		LinkedList<Node> newPath = new LinkedList<Node>();
		LinkedList<String> stringList = new LinkedList<String>();

		for(Node node: list){
			stringList.add(node.getName());
		}
		Collections.sort(stringList);
		for(String str: stringList){
			for(Node node: list){
				if(node.getName().equals(str) && !dups.contains(node.getName())){
					newPath.addLast(node);		
					dups.addFirst(node.getName());
				}
			}
		}
		return newPath;	
	}
	
	public boolean IsDescendant(LinkedList<Node> list, Node path){
		for(Node node: list){
			if(node.getName().equals(path.getName())){
				return true;
			}
		}
		return false;
	}
	public void printQueue2(LinkedList<LinkedList<Node>> list){
		System.out.print("[");
		for(LinkedList<Node> nodes: list){
			for(Node n: nodes){
				if(nodes.size() == 1){
					System.out.println("<" + n.getName() + ">]");
					return;
				}
			}
			System.out.print(" <");
			printQueue(nodes);
			System.out.print("> ");
		}
		System.out.print("]");
		System.out.println();
	}
}
