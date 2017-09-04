package SetUp;
import java.util.HashMap;
import java.util.LinkedList;

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
		Node dummyNode = new Node("Not", 0.0, 0.0, 0);
		for(Node n: graph.keySet()){
			if(n.getName().equals("S") && n.visited == 0){
				n.setvisted();
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

	

}
