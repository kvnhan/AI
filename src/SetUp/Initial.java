/**
 * 
 */
package SetUp;

import java.util.LinkedList;

/**
 * @author jmetzger
 *
 */
public class Initial {
	
	private LinkedList<String> visited = new LinkedList<String>();
	private LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	private LinkedList<Node> visitedNode = new LinkedList<Node>();
	private Queue queueClass = new Queue();
	private LinkedList<String> DeadEnd = new LinkedList<String>();
	
	public Initial() {
		
	}

	public void startTree(Graph graph, Node node) {
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState = graph.getS();

	
		// Find S Node
	if(visited.isEmpty()){
		path = graph.getChildrenOf(startState);
		startQueue.add(startState);
		queue.add(startQueue);
		visited.add(startState.getName());
		visitedNode.add(startState);
		System.out.println("Expand " + visited.getFirst());
		queueClass.printQueue2(queue);
		System.out.println("\n");
		queue = new LinkedList<LinkedList<Node>>();
		path = graph.getChildrenOf(node);
		path = queueClass.sortPath(path, node);
	}else{	
		path = graph.getChildrenOf(node);
		path = queueClass.removedVisitedPath(path);
		
		// Dead End
		if(path.size() == 0){
			//System.out.println("** Dead End " + node.getName() + "**\n");
			graph.setVisited(node);
			DeadEnd.add(node.getName());
			queue = queueClass.pop();
			node = visitedNode.getFirst();
			path = graph.getChildrenOf(node);
			path = queueClass.removedVisitedPath(path);
			path = queueClass.sortPath(path, node);
			
		}else{
			path = queueClass.sortPath(path, node);
			visitedNode.addFirst(node);
			visited.addFirst(node.getName());
			graph.setVisited(node);
			//printQueue(path);	
		}
	}
	
	}
	
}
