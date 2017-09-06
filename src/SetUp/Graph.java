package SetUp;
import java.util.AbstractQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	HashMap<Node, Node> graph = new HashMap<Node, Node>();
	
	
	Graph(){
	}

	// Method to great a graph, sort of
	void createGraph(Node node1, Node node2){
		graph.put(node1, node2);
		
		
	}
	
	// A method to get a list of paths
	public LinkedList<Node> getChildrenOf(Node node){
		LinkedList<Node> list = new LinkedList<Node>();
		for(Node n: graph.keySet()){
			if(node.getName().equals(n.getName())){
				list.addFirst(graph.get(n));
				
			}else if(graph.get(n).getName().equals(node.getName())){
				list.addFirst(n);
			}
				
		}
			
			return list;
	}
	
	// A method to check if a node exists 
	public boolean NodeExist(Node node){
		for(Node n: graph.keySet()){
			if(n.getName().equals(node)){
				return true;
			}
		}
		return false;
	}
	
	// A method to change the cost of a node
	public void changeNodeCost(Node node, Double cost){
		if(NodeExist(node)){
			for(Node n: graph.keySet()){
				if(n.getName().equals(node.getName())){
					n.setCost(cost);
				}
			}
		}
		
	}
	
	// A method to get cost of a node
	public double getCost(String node){
		for(Node n: graph.keySet()){
			if(n.getName().equals(node)){
				return n.getCost();
			}
		}
		
		return 0;
	}
	
	public int getSize(){
		return graph.size();
	}

	
	public Node getS(){		
		// Find S
		LinkedList<Node> start = new LinkedList<Node>();
		Node dummyNode = new Node("Not", 0.0, 0.0, 0);
		for(Node n: graph.keySet()){
			if(n.getName().equals("S") && n.visited == 0){
				n.setvisted();
				start.add(n);
				n.setAdjacentNodes(start);
				return n;
			}
		}
		
		return dummyNode;
	}

	
	public void setVisited(Node n){
		
		for(Node node: graph.keySet()){
			if(node.getName().equals(n.getName())){
				node.setvisted();
				
			}
		}
	}
	
	public Node General_Search(){
		Queue<Node> q = new LinkedList<Node>();
		Node goal_node = new Node("G", 0.0, 0.0, 0);
		Node start_node = new Node("S", 0.0, 0.0, 0);
		q.add(start_node);
		while(true){
			if (q.isEmpty()){
				return null;
			}
			Node curr_node = q.remove();
			if (curr_node.name == goal_node.name){
				return curr_node;
			}
			LinkedList<Node> new_nodes = getChildrenOf(curr_node);
			Queue<Node> new_q = AddToQueue(q, new_nodes, "BFS");
			q = new_q;
		}
	}
	
	public Queue<Node> AddToQueue(Queue<Node> q, Queue<Node> new_nodes, String method){
		Queue<Node> new_q;
		if (method == "BFS"){
			new_q = new LinkedList<Node>();
			for (Node n : q) {
		        new_q.add(n);
			}
			for (Node n : new_nodes) {
		        new_q.add(n);
			}
			return new_q;
		}
		
		return null;
	}

	

}
