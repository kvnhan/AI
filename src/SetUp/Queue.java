/**
 * 
 */
package SetUp;

import java.util.LinkedList;

/**
 * @author jmetzger
 *
 */
public class Queue {
	
	public LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	public LinkedList<String> visited = new LinkedList<String>();
	//private LinkedList<String> DeadEnd = new LinkedList<String>();
	public LinkedList<String> dups = new LinkedList<String>();
	//private LinkedList<Node> visitedNode = new LinkedList<Node>();
	
	public Queue() {
		
	}
	

	public LinkedList<LinkedList<Node>> fixQueue(Node child, LinkedList<LinkedList<Node>> dummyqueue){

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
	
	public LinkedList<Node> removedVisitedPath(LinkedList<Node> p){
		LinkedList<Node> l = new LinkedList<Node>();
		for(Node n: p){
			//System.out.println(n.getName() + " " + n.visited);
			if(n.visited == 0 && !visited.contains(n.getName())){
				//System.out.println(n.getName() + " " + n.visited);
				l.addFirst(n);
			}


		}
		return l;
	}
	
	public LinkedList<Node> sortPath(LinkedList<Node> list, Node n){
		double temp = 0.0;
		LinkedList<Node> newPath = new LinkedList<Node>();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getDistanceTo(n) > temp && !dups.contains(list.get(i).getName())){
				newPath.addLast(list.get(i));
				temp = list.get(i).getDistanceTo(n);

			}else{
				newPath.addFirst(list.get(i));
				dups.addFirst(list.get(i).getName());
			}
		}

		return newPath;
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
			System.out.print("<");
			printQueue(nodes);
			System.out.print(">");
		}
		System.out.print("]");
		System.out.println();
	}
	
	public void addQueue(LinkedList<Node> node) {
		queue.addLast(node);
	}

}
