package SetUp;
import java.util.LinkedList;
import java.util.HashMap;

public class Node implements Comparable<Node>{
	String name;
	double cost, distance;
	public int visited; // 0 = not visited, 1 = visited
	HashMap<Node, Double> direction = new HashMap<Node, Double>();
	public LinkedList<Node> adjacentNodes = new LinkedList<Node>();;
	LinkedList<HashMap<Node, Double>> path = new LinkedList<>();
	Node(String name, double cost, double distance, int visited){
		this.name = name;
		this.cost = cost;
		this.distance = distance;
		this.visited = visited;
	}
	

	public String getName(){
		return name;
	}
	
	public double getCost(){
		return cost;
	}
	

	public void pairNode(Node n){
		direction.put(n, distance);
		path.add(direction);
		
	}
	
	public LinkedList<Node> getAdjacentNodes(){
		return adjacentNodes;
	}
	
	public void setAdjacentNodes(LinkedList<Node> newNodes){
		for(Node node: newNodes){
			if(!this.getName().equals(node.getName())){
				this.adjacentNodes.addLast(node);
			}
		}
	}
	public LinkedList<Node> getPathOf(){
		
		LinkedList<Node> list = new LinkedList<Node>();
		
		for(HashMap<Node, Double> n: path){
			for(Node node: n.keySet()){
				list.add(node);
				
			}
		}
		
		//remember to sort it
		
		return list;
	}
	// Method to get the distance between nodes
	public double getDistanceTo(Node node){
		double distance = 0.0;
		for(Node n: direction.keySet()){
			if(n.getName().equals(node.getName())){
				distance = direction.get(n);
			}
		}
		
		return distance;
	}
	
	public void setCost(double c){
		this.cost = c;
	}
	

	public void setvisted(){
		this.visited = 1;
	}


	@Override
	public int compareTo(Node arg0) {
		double cost = arg0.getCost();
		if(this.getCost() > cost){
			return 1;
		}else if(this.getCost() == cost){
			return 0;
		}else{
			return -1;
		}
	
	}
	

}
