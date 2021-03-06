package basic_searches;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Node;

/**
 * Beam.java
 * @author jmetzger kvnhan jwilder
 */

public class Beam {
	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	HashMap<Integer, LinkedList<Node>> map = new HashMap<Integer, LinkedList<Node>>();
	HashMap<Double, LinkedList<Node>> heu_Map = new HashMap<Double, LinkedList<Node>>();
	LinkedList<String> expanded = new LinkedList<String>();
	int best_nodes = 2;
	boolean foundG = false;
	
	public boolean bs(Graph graph, Node node, Node from, LinkedList<Node> list){
		int level = list.size();
		
		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<LinkedList<Node>> dummyqueue = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> path = new LinkedList<Node>();
		
		if(node.getName().equals("G")){
			System.out.print(node.getName());
			printQueue2(graph, queue);
			return true;
		}
		
		path = graph.getChildrenOf(node);
		path = removePath(path, from);
		
		if(path.size() == 0){
			System.out.print(node.getName());
			printQueue2(graph, queue);
			graph.setVisited(node);
			queue = pop();
			node = queue.getFirst().getFirst();
			from = queue.getFirst().get(1);
			path = graph.getChildrenOf(node);
			path = removePath(path, from);
			path = sortPath(path, node);
			list = queue.getFirst();
		}
		if(node.getName().equals("S")){
			startQueue.add(node);
			queue.add(startQueue);
			list = queue.getFirst();
			path = sortPath(path, node);

		}else{
			path = sortPath(path, node);
			addValues(level - 1, path, node);
		}

		LinkedList<Node> adjNode = new LinkedList<Node>();
		LinkedList<Node> best = new LinkedList<Node>();

		adjNode = list;
		for(Node child: path){
			if(!child.getName().equals(from.getName()) && !IsDescendant(queue.getFirst(), child)){
				addValues(level + 1, path, child);
				child.reset();
				child.setAdjacentNodes(adjNode);
				child.getAdjacentNodes().addFirst(child);
				dummyqueue.addLast(child.getAdjacentNodes());
				heu_Map.put(graph.getHeuristics(child), child.getAdjacentNodes());
				}
			}
		best = sortH(level + 1, graph);
		best = bestNodes(level + 1);
		best = sortPath(best, node);
		System.out.print(node.getName());
		printQueue2(graph, queue);
	
		LinkedList<Node> temp = new LinkedList<Node>();
		if(!queue.getFirst().getFirst().getName().equals("G")){
			
			queue = getTwo(graph, best);
			queue.pop();
			Node newnode2 = queue.getFirst().getFirst();
			Node fromNode2 = queue.getFirst().get(1);	
			temp = queue.getFirst();
			foundG = bs(graph, newnode2, fromNode2, temp);
		}
		
		
		return foundG;
	}
	
	public LinkedList<Node> addValues(int level, LinkedList<Node> list, Node node){
		LinkedList<Node> temp = new LinkedList<Node>();
		LinkedList<String> str = new LinkedList<String>();
		if(map.isEmpty() || map.get(level) == null){
			map.put(level, list);
			return list;
		}else{
			temp = map.get(level);
			for(Node n: temp){
				str.addFirst(n.getName());
			}
			if(!str.contains(node.getName())){
				temp.addLast(node);
				map.put(level, temp);
			}
		}
		return temp;
	}
	
	public LinkedList<Node> sortH(int level, Graph g){
		LinkedList<Node> path = new LinkedList<Node>();
		path = map.get(level);
		
		Collections.sort(path, new Comparator<Node>(){
			   @Override
			   public int compare(Node o1, Node o2){
			        if( g.getHeuristics(o1)< g.getHeuristics(o2)){
			           return -1; 
			        }
			        if(g.getHeuristics(o1) >g.getHeuristics(o2)){
			           return 1; 
			        }else{
			        	return 0;
			        }
			   }
			}); 
		
		return path;
		
		
	}
	public LinkedList<Node> bestNodes(int l){
		LinkedList<Node> path = new LinkedList<Node>();
		for(Integer level: map.keySet()){
			if(level == l){
				for(int i = 0; i < map.get(l).size();){
					path.addLast(map.get(l).get(i));
					if(map.get(l).size() > 1){
						path.addLast(map.get(l).get(i + 1));
					}
					return path;
					
				}
			}
		}
		return path;
	}
	
	
	public LinkedList<LinkedList<Node>> sortQueue(LinkedList<LinkedList<Node>> q){
		LinkedList<LinkedList<Node>> newq = new LinkedList<LinkedList<Node>>();
		int level = q.getFirst().size() - 1;
		for(LinkedList<Node> n: queue){
			if(n.size() - 1 != level){
				newq.addLast(n);
			}
		}
		
		return newq;
	}
	public LinkedList<LinkedList<Node>> getTwo(Graph g, LinkedList<Node> list){
		LinkedList<Node> l = new LinkedList<Node>();
		LinkedList<LinkedList<Node>> newq = new LinkedList<LinkedList<Node>>();
		LinkedList<LinkedList<Node>> q = new LinkedList<LinkedList<Node>>();
		double value;
		for(Node n: list){
			value = g.getHeuristics(n);
			l = heu_Map.get(value);
			newq.addLast(l);
		}
		
		q = sortQueue(newq);
		for(LinkedList<Node> node: newq){
			q.addLast(node);
		}
		return q;
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
	
	
	public void printQueue(Graph g, LinkedList<Node> list){
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
	
	public void printQueue2(Graph g, LinkedList<LinkedList<Node>> queue){
		
		System.out.print("                 [");
		for(LinkedList<Node> nodes: queue){
			double heu = g.getHeuristics(nodes.getFirst());
			System.out.print(heu);
			System.out.print(" <");
			printQueue(g, nodes);
			System.out.print("> ");	
		}
		System.out.print("]");
		System.out.println();
	}
	
	

}
