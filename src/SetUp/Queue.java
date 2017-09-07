/**
 * 
 */
package SetUp;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author jmetzger
 *
 */
public class Queue {
	
	public LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	public LinkedList<String> visited = new LinkedList<String>();

	public Queue() {
	}
	
	public LinkedList<LinkedList<Node>> getQueue(){
		return queue;
	}
	

	public Node getFromnode(){
		return queue.getFirst().get(1);
		
	}
	
	public Node getExpandedNode(){
		Node node;
		node = queue.getFirst().getFirst();
		return node;
	}
	
	public LinkedList<Node> peekFirst(){
		return queue.getFirst();
	}
	
	public LinkedList<Node> getPathFrom(){
		return queue.getFirst();

	}
	
	// Use to order the queue for DFS 
	public LinkedList<LinkedList<Node>> fixDFSQueue(Node child, LinkedList<LinkedList<Node>> dummyqueue){

		LinkedList<LinkedList<Node>> queue2 = new LinkedList<LinkedList<Node>>();
		for(LinkedList<Node> node: dummyqueue){
			queue2.addLast(node);
		}
		for(LinkedList<Node> node: queue){
			if(!node.getFirst().getName().equals(child.getName())){
				queue2.addLast(node);
			}
		}
		return queue2;
	}

	// Use to order queue for BFS
	public LinkedList<LinkedList<Node>> fixBFSQueue(LinkedList<LinkedList<Node>> dummyqueue){
		for(LinkedList<Node> node: dummyqueue){
			queue.addLast(node);
		}
		return queue;
	}
	
	
	// Use to order queue for Uniformed Cost Search
	public LinkedList<LinkedList<Node>> fixUCSQueue(LinkedList<LinkedList<Node>> dummyqueue){
		Node dummyNode = new Node("Dummy", 0.0,0.0,0);
		
		HashMap<Double, LinkedList<Node>> map = new HashMap<Double, LinkedList<Node>>();
		double distance = 0.0;
		LinkedList<Double> numlist = new LinkedList<Double>();		
		for(LinkedList<Node> node: dummyqueue){
			distance = dummyNode.getTotalDistance(node);
			numlist.add(distance);
			map.put(distance, node);
		}
		Collections.sort(numlist);
		//Might need to consider a tie
		for(Double d: numlist){
			for(Double doub: map.keySet()){
				if(doub == d){
					queue.addLast(map.get(doub));
				}
			}
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
	
	//Remove any node that is repeated 
	public LinkedList<Node> removePath(LinkedList<Node> p, Node node){
		LinkedList<Node> l = new LinkedList<Node>();
		for(Node n: p){
			if(!n.getName().equals(node.getName())){
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
	
	public boolean IsDescendant(LinkedList<Node> list, Node path){
		for(Node node: list){
			if(node.getName().equals(path.getName())){
				return true;
			}
		}
		return false;
	}
	
	public void printQueue2(){
		System.out.print("[");
		for(LinkedList<Node> nodes: queue){
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
	
	public void addQueue(LinkedList<Node> node) {
		queue.addLast(node);
	}

}
