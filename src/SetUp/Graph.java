package SetUp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import basic_searches.A_Star;
import basic_searches.Beam;
import basic_searches.Breadth_First;
import basic_searches.Depth_First;
import basic_searches.Depth_Limited;
import basic_searches.Greedy;
import basic_searches.Hill_Climbing;
import basic_searches.Iterative_Deepening;
import basic_searches.Uniform_Cost;

/**
 * Graph.java
 * @author jmetzger kvnhan jwilder
 */

public class Graph {
	HashMap<Node, Node> graph = new HashMap<Node, Node>();
	LinkedList<Node> visited = new LinkedList<Node>();
	LinkedList<Edge> edges = new LinkedList<Edge>();
	LinkedList<Path> pathqueue = new LinkedList<Path>();
	HashMap<String, Double> heuristic_dict = new HashMap<String, Double>();
	LinkedList<Node> list = new LinkedList<Node>();
	boolean found = true;
	Greedy greedy = new Greedy();
	Breadth_First breadth = new Breadth_First();
	Depth_First depth = new Depth_First();
	Depth_Limited depthLimited = new Depth_Limited();
	Uniform_Cost uniform = new Uniform_Cost();
	Iterative_Deepening iterative = new Iterative_Deepening();
	Hill_Climbing hillclimb = new Hill_Climbing();
	A_Star a = new A_Star();
	Beam beam = new Beam();
	
	Graph(){
	}

	
	public double getHeuristics(Node n){
		return heuristic_dict.get(n.getName());
	}
	// Method to great a graph, sort of
	void createGraph(Node node1, Node node2){
		graph.put(node1, node2);
		heuristic_dict.put("G", 0.0);
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
			if(n.getName().equals("S")){
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
	
	
	public Node General_Search(String method){
		
		LinkedList<Node> nodepath = new LinkedList<Node>();
		Node start_node = this.getS();
		nodepath.add(start_node);
		Path path = new Path(nodepath, 0.0);
		pathqueue.add(path);
		found = false;
		System.out.println("Expanded\t\t" + "Queue");
		while(true){
			
			if(found) {
				System.out.println("\nGoal Reached!\n");
				return null;
			}
			
			if (pathqueue.isEmpty()){
				System.out.print("\nFail\n\n");
				return null;
			}
			
			if(!method.equals("BS") && !method.equals("IDS")){
				System.out.print(pathqueue.getFirst().getP().getFirst().getName());
				if(pathqueue.getFirst().getP().getFirst().getName().equals("G")){
					printQueue2(pathqueue);
					pathqueue = new LinkedList<Path>();
					System.out.println("\nGoal Reached!\n");
					return null;
				}
				printQueue2(pathqueue);
			}
			
			Path curr_path = pathqueue.remove();
			Node curr_node = curr_path.p.getFirst();
			visited.add(curr_node);
			if (curr_node.name.equalsIgnoreCase("G")){
				return curr_node;
			}
			LinkedList<Node> new_nodes = getChildrenOf(curr_node);
			Collections.sort(new_nodes, new Comparator<Node>(){
				   @Override
				   public int compare(Node o1, Node o2){
				        if(o1.name.compareTo(o2.name) < 0){
				           return -1; 
				        }
				        if(o1.name.compareTo(o2.name) > 0){
				           return 1; 
				        }
				        return 0;
				   }
				}); 

			LinkedList<Path> new_paths = new LinkedList<Path>();

			for (Node n : new_nodes) {
				Boolean in_path = false;
				LinkedList<Node> p = new LinkedList<Node>();
				for(Node i : path.p){
					if(n.name.equalsIgnoreCase(i.name)){
						in_path = true;
					}
				}

				if (!in_path){
			   Double newcost = 0.0;
			   if(!path.p.isEmpty()){
			   newcost = path.getDist() + distance(path.p.getFirst(), n);
			   }
				p.addAll(path.p);
				p.addFirst(n);
				Path newpath = new Path(p, newcost);
				new_paths.add(newpath);
				}
				else{
				}
			}		
			
			AddToQueue(new_paths, method);	
			if(!pathqueue.isEmpty()) {
				path = pathqueue.getFirst();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Failed");
				e.printStackTrace();
			}
		}
	}
	
	public Double distance(Node first, Node n) {
		 
		for(Edge e : n.edges){
//			 System.out.println("From: " + n.name);
//			 System.out.println("To: " + e.to.name);
//			 System.out.println("Needs to match: " + first.name);
			if(e.to.name.equalsIgnoreCase(first.name)){
//				System.out.println("11: " + e.dist);
				return e.dist;
			}
		}
		return 0.0;
	}

	public void AddToQueue(LinkedList<Path> new_paths, String method){		
		Node node = new Node("S", 0.0, 0.0, 0);
		switch(method) {
			case "DFS":
				depth.depthSearch(pathqueue,new_paths,heuristic_dict);
				break;
			case "BFS":
				breadth.breadthSearch(pathqueue, new_paths, heuristic_dict);
				break;
			case "DLS":
				depthLimited.depthLimitedSearch(pathqueue,new_paths,heuristic_dict);
				break;
			case "IDS":
				found = iterative.ids(this,node,node,0, list);
				break;
			case "UCS":
				uniform.uniformSearch(pathqueue,new_paths,heuristic_dict);
				break;
			case "Greedy":
				greedy.greedySearch(pathqueue, new_paths, heuristic_dict);
				break;
			case "A*":
				a.aSearch(pathqueue, new_paths, heuristic_dict);
				break;
			case "HCS":
				hillclimb.hillClimbingSearch(pathqueue, new_paths, heuristic_dict);
				break;
			case "BS":
				found = beam.bs(this, node, node, list);
				break;
			default:
		        System.err.println ( "Wrong Input" );
		        return;
		}
	}

	public void printQueue(Path list){

		int size = list.p.size();
		int count = 0;
		for(Node n: list.p){
			count++;
			if(size == count){
				System.out.print("" + n.getName() + "");
			}else{
				System.out.print("" + n.getName() + ",");
			}
		}


	}
	
	public void printQueue2(Queue<Path> list){

		System.out.print("\t\t[");
		double cost = 0.0;
		for(Path nodes: list){
			cost = nodes.getDist();
			BigDecimal bd = new BigDecimal(cost);
			System.out.print(bd.setScale(1, RoundingMode.HALF_UP).setScale(1));
			for(Node n: nodes.p){
				
				if(nodes.p.size() == 1){
					System.out.println(" <" + n.getName() + "> ]");
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
	
	public double getTotalDistance(LinkedList<Node> list){
		LinkedList<Node> newList = new LinkedList<Node>();
		for(Node n: list){
			newList.addLast(n);
		}
		double totalDist = 0.0;
		Node temp = new Node("Dummy", 0.0, 0.0, 0);
		for(Node node: newList){
			if(node.getName().equals("S")){
				totalDist = 0.0;
			}else{
				totalDist += getDist(temp, node); 
				System.out.println("Total Dist:" + totalDist);
			}
			temp = node;
		}
		
		return totalDist;
	}
	
	public double getDist(Node from, Node to){
		double distance = 0.0;
		for(Node n: from.direction.keySet()){
			if(n.getName().equals(to.getName())){
				distance = from.direction.get(n);
			}
		}
		if(distance == 0){
			for(Node n: to.direction.keySet()){
				if(n.getName().equals(from.getName())){
					distance = to.direction.get(n);
				}
			}
		}
		
		System.out.println("Distance from " + from.name + " to " + to.name + ": " + distance);
		return distance;
	}


}