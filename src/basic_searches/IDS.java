/**
 * 
 */
package basic_searches;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Node;
import SetUp.Queue;

/**
 * @author jmetzger
 *
 */
public class IDS {
	LinkedList<String> visited = new LinkedList<String>();
	LinkedList<String> expanded = new LinkedList<String>();
	LinkedList<Node> visitedNode = new LinkedList<Node>();
	LinkedList<Node> storage = new LinkedList<Node>();
	HashMap<String, LinkedList<LinkedList<Node>>> map = new HashMap<String, LinkedList<LinkedList<Node>>>();
	HashMap<LinkedList<Node>, Integer> level = new HashMap<LinkedList<Node>, Integer>();
	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	int found = 0;
	Queue queueClass = new Queue();
	int depthChange = 0;
	int sizeChange = 0;
	int limitChange = 0;
	int limit = 0;
	int numPath = 1;
	public IDS(){
		
	}
	
	public boolean ids(Graph graph, Node node, Node from, int depth, LinkedList<Node> list){
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> start = new LinkedList<Node>();
		LinkedList<LinkedList<Node>> dummyqueue = new LinkedList<LinkedList<Node>>();
		LinkedList<LinkedList<Node>> dqueue = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();
		int size = list.size();
	
		System.out.println("L=" + depth);
			
		path = graph.getChildrenOf(node);
		path = removePath(path, from);	
		// Dead End
		if(path.size() == 0){
			if(node.getName().equals("G")){
				System.out.println("\nExpand " + node.getName());
				printQueue2(queue);
				System.out.println("\nGoal Reached!");
				return true;
			}
			System.out.println("\nExpand " + node.getName());
			printQueue2(queue);
			graph.setVisited(node);
			queue = pop();
			node = queue.getFirst().getFirst();
			from = queue.getFirst().get(1);
			path = graph.getChildrenOf(node);
			path = sortPath(path, node);
			list = queue.getFirst();
		}else{
			path = sortPath(path, node);
			visitedNode.addFirst(node);
			visited.addFirst(node.getName());
			graph.setVisited(node);		
		}
		
		if(node.getName().equals("S") && depth == 0){
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			storage.add(node);
			expanded.add(node.getName());
			level.put(startQueue, 0);
			System.out.println("Expand " + node.getName());
			printQueue2(queue);
			ids(graph, node, node, 1, startQueue);
			return true;
		}
		
		if(node.getName().equals("S") && depth > 1){
			startQueue.add(node);
			queue.add(startQueue);
			level.put(startQueue, startQueue.size() - 1);
			node.getAdjacentNodes().addFirst(node);
		}
		// Get A list of Adjacent Node
			LinkedList<Node> adjNode = new LinkedList<Node>();
			adjNode = list;
			if(list.size() - 1 < depth){
				for(Node child: path){
					if(!child.getName().equals(from.getName()) && !IsDescendant(queue.getFirst(), child)){
						child.reset();
						child.setAdjacentNodes(adjNode);
						child.getAdjacentNodes().addFirst(child);
						dummyqueue.addLast(child.getAdjacentNodes());
						start = addT(child.getAdjacentNodes());
						level.put(start, start.size() - 1);
					}
				}
			}
			
			if(!node.getName().equals("S")){
				System.out.println("\nExpand " + node.getName());
				printQueue2(queue);
				if(list.size()- 1 != depth){
					queue = fixDFSQueue(node, dummyqueue);
					printQueue2(queue);
				}
				map.put(node.getName(), dqueue);
			}else{
				System.out.println("\nExpand " + node.getName());
				printQueue2(queue);
				queue = fixDFSQueue(node, dummyqueue);
				printQueue2(queue);
			}
			
			
			if(!expanded.contains(node.getName())){
				expanded.add(node.getName());
			}
			// Traversing the graph to Find G Node
			Node n = null;
			Node f = null;
			LinkedList<Node> temp = new LinkedList<Node>();
			if(!queue.getFirst().getFirst().getName().equals("G")){
				if(queue.size() == 1){
					temp = queue.getFirst();
					n = queue.getFirst().getFirst();
					f =  queue.getFirst().get(1);
				}
				queue.pop();
				if(queue.size() == 0){
					if(getLevel(node) == depth){
						ids(graph, storage.getFirst(), storage.getFirst(), depth + 1, storage);
						return true;
					}else{
						ids(graph, n, f, depth, temp);
						return true;
					}	
				}
				Node newnode2 = queue.getFirst().getFirst();
				Node fromNode2 = queue.getFirst().get(1);
				ids(graph, newnode2, fromNode2, depth, queue.getFirst());
				return true;
				
			}
			
			System.out.println("\nGoal Reached!");
	
			return true;
	}
	
	public int getLevel(Node n){
		for(LinkedList<Node> list : level.keySet()){
			if(list.getFirst().getName().equals(n.getName())){
				return level.get(list);
			}
		}
		
		return 0;
	}
	
	public LinkedList<Node> addT(LinkedList<Node> list){
		LinkedList<Node> queue2 = new LinkedList<Node>();
		for(Node node: list){
			queue2.addLast(node);
		}
		
		return queue2;
	}
	
	public LinkedList<LinkedList<Node>> addToqueue(){
		LinkedList<LinkedList<Node>> queue2 = new LinkedList<LinkedList<Node>>();
		for(LinkedList<Node> node: queue){
			queue2.addLast(node);
		}
		
		return queue2;
	}
	public LinkedList<LinkedList<Node>> pop(){
		queue.removeFirst();
		return queue;
	}
	
	
	public LinkedList<Node> sortPath(LinkedList<Node> list, Node n){
		LinkedList<Node> newPath = new LinkedList<Node>();
		LinkedList<String> dups = new LinkedList<String>();
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

	public boolean IsDescendant(LinkedList<Node> list, Node path){
		for(Node node: list){
			if(node.getName().equals(path.getName())){
				return true;
			}
		}
		return false;
	}
	
	// Use to order queue for BFS
		public LinkedList<LinkedList<Node>> fixBFSQueue(LinkedList<LinkedList<Node>> dummyqueue){
			for(LinkedList<Node> node: dummyqueue){
				queue.addLast(node);
			}
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
	
	public void printQueue2(LinkedList<LinkedList<Node>> queue){
		System.out.print("[");
		for(LinkedList<Node> nodes: queue){
			System.out.print(" <");
			printQueue(nodes);
			System.out.print("> ");	
		}
		System.out.print("]");
		System.out.println();
	}
	
	// Use to order the queue for DFS 
		public LinkedList<LinkedList<Node>> fixDFSQueue(Node child, LinkedList<LinkedList<Node>> dummyqueue){
			LinkedList<LinkedList<Node>> queue2 = new LinkedList<LinkedList<Node>>();
			LinkedList<Node> alist = new LinkedList<Node>();
			alist = queue.getFirst();
			queue = pop();
			for(LinkedList<Node> n: dummyqueue){
				queue2.addLast(n);
			}
			for(LinkedList<Node> node: queue){
				if(!child.getName().equals("S")){
					queue2.addLast(node);
				}else{
					queue2.addFirst(node);
				}
			}
			queue2.addFirst(alist);
			return queue2;
		}
}
