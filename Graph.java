import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	HashMap<Node, Node> graph = new HashMap<Node, Node>();
	
	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	
	Graph(){
	}

	// Method to great a graph, sort of
	void createGraph(Node node1, Node node2){
		graph.put(node1, node2);
		graph.put(node2, node1);
		
	}
	
	// A method to get a list of children
	public LinkedList<Node> getChildrenOf(Node node){
		LinkedList<Node> list = new LinkedList<Node>();
		for(Node n: graph.keySet()){
			if(node.getName().equals(n.getName())){
				list.addFirst(graph.get(n));
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

	
	public void DFS(Node node){
		
		LinkedList<Node> path = new LinkedList<Node>();
		
		for(Node n: graph.keySet()){
			if(n.getName().equals("S") && n.visited == 0){
				n.setvisted();
				n.adjacentNodes.add(n);
				path = n.adjacentNodes;
				queue.addFirst(path);
				break;
			}
		}
		
		for(Node child: path){
			if(child.adjacentNodes.isEmpty()){
				child.adjacentNodes = path;
				child.adjacentNodes.addFirst(child);
			}else{
				child.adjacentNodes.addFirst(child);
			}
		}
		
		for(Node child: path){
			
		}
		
		return;			
	}

}
