/**
 * Breath-First-Search Algorithm
 * @author jmetzger kvnhan
 */

package basic_searches;

import java.util.LinkedList;
import SetUp.Graph;
import SetUp.Node;



public class BFS {

	LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	LinkedList<String> visited = new LinkedList<String>();
	LinkedList<String> DeadEnd = new LinkedList<String>();
	LinkedList<String> dups = new LinkedList<String>();
	LinkedList<Node> visitedNode = new LinkedList<Node>();
	int found = 0;
	Node prevSib = null;

	public BFS(){

	}

	public boolean bfs(Graph graph, Node node){

		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<LinkedList<Node>> dummyqueue = new LinkedList<LinkedList<Node>>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState;
		startState = graph.getS();


		// Find S Node
		if(visited.isEmpty()){
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			visited.add(startState.getName());
			visitedNode.add(startState);
			System.out.println("Expand " + visited.getFirst());
			printQueue2(queue);
			queue = new LinkedList<LinkedList<Node>>();
			path = graph.getChildrenOf(node);
			path = sortPath(path, node);
		}else{
			path = graph.getChildrenOf(node);
			path = removedVisitedPath(path);

			// Dead End
			if(path.size() == 0){
				System.out.println("Dead End " + node.getName());
				graph.setVisited(node);
				DeadEnd.add(node.getName());
				queue = pop();
				node = visitedNode.getFirst();
				path = graph.getChildrenOf(node);
				path = removedVisitedPath(path);
				path = sortPath(path, node);

			}else{
				path = sortPath(path, node);
				visitedNode.addFirst(node);
				visited.addFirst(node.getName());
				graph.setVisited(node);
				//printQueue(path);
			}
		}

		// Get A list of Adjacent Node
		for(Node child: path){
			if(child.getAdjacentNodes().size() == 0){
				child.setAdjacentNodes(visitedNode);
				child.getAdjacentNodes().addFirst(child);
				printQueue(child.getAdjacentNodes());
				if(queue.size() < 2){
					System.out.println("\n" + queue.size());
					queue.addLast(child.getAdjacentNodes());
					System.out.println("\nSo the queue is emmpty");
				}else{
					System.out.println("Hello");
					System.out.println("\n" + dummyqueue.size());
					queue = pop();
					dummyqueue.addLast(child.getAdjacentNodes());
				}

			}
		}


		// Traversing the graph to Find G Node
		int size = path.size();
		System.out.println(queue.size());
		for(Node c: path){
			if(c.visited == 0 && found == 0){
				if(!c.getName().equals("G")){
					System.out.println("Parent is still " + node.getName());
					System.out.println("Expand " + c.getName() + "\n");
					queue = fixQueue(visitedNode.getFirst(), dummyqueue);
					printQueue2(dummyqueue);
					c.setvisted();
					printQueue2(queue);
					found = 0 ;
					if(size < 2) {
						System.out.println("Node " + c.getName());
						System.out.println("Prev Node " + prevSib.getName());

						bfs(graph, prevSib);
					}else {
						size--;
						prevSib = c;
					}
				}else{
					System.out.println("Expand " + c.getName() + "\n");
					queue = fixQueue(visitedNode.getFirst(), dummyqueue);
					c.setvisted();
					printQueue2(queue);
					System.out.println("goal reached!\n");
					found = 1;
				}
			}

		}
		return false;
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

}
