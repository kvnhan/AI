/**
 * 
 */
package SetUp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import general_search.general_search;

/**
 * @author jmetzger kvnhan jwilder
 *
 */


public class Initial {

	private LinkedList<String> visited = new LinkedList<String>();
	private LinkedList<LinkedList<Node>> queue = new LinkedList<LinkedList<Node>>();
	private LinkedList<Node> visitedNode = new LinkedList<Node>();
	private Queue queueClass = new Queue();
	private LinkedList<String> DeadEnd = new LinkedList<String>();
	private Filename filename = new Filename();
	

	public Initial() {

	}

	public void parse(BufferedReader br, FileReader fr, String[] args, Graph g) {
		try {
			String sCurrentLine;
			//String text = args[0];
			//File file = new File(text);
			//String path = file.getAbsolutePath();
			Node node1, node2;
			try {
				fr = new FileReader(filename.getName());
				br = new BufferedReader(fr);

			} catch (Exception e) {
				System.out.println("Sorry");
			}

			while ((sCurrentLine = br.readLine()) != null) {

				// System.out.println(sCurrentLine);

				String[] token = sCurrentLine.split("\\s+");
				if (!(token.equals("#####"))) {
					if (token.length == 3) {
						node1 = new Node(token[0], 0.0, Double.parseDouble(token[2]), 0);
						node2 = new Node(token[1], 0.0, Double.parseDouble(token[2]), 0);
						node1.pairNode(node2);
						node1.addEdge(node2, Double.parseDouble(token[2]));
						node2.addEdge(node1, Double.parseDouble(token[2]));
						g.createGraph(node1, node2);

					} else if (token.length == 2) {
						Node oldnode = new Node(token[0], 0.0, 0.0, 0);
						g.heuristic_dict.put(token[0], Double.parseDouble(token[1]));
						if (g.NodeExist(oldnode)) {
							g.changeNodeCost(oldnode, Double.parseDouble(token[1]));

						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getClass());
			System.out.println(e.toString());
			System.out.println("No file exists");
			System.out.println("\n===== EOF =====\n\n");

		}

	}

	public void selection(Graph g) {
		Scanner in = new Scanner(System.in);
		switch (in.nextInt()) {
		case 1:
			System.out.println("\nSearch Method: Depth 1st Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("DFS");
			//gs.search(g, "DFS");
			break;
		case 2:
			System.out.println("\nSearch Method: Breadth 1st Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("BFS");
			break;
		case 3:
			System.out.println("\nSearch Method: Depth-Limited Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("DLS");
			break;
		case 4:
			System.out.println("\nSearch Method: Iterative Deepening Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("IDS");
			break;
		case 5:
			System.out.println("\nSearch Method: Uniform Cost Search (Branch-and-bound)");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("UCS");
			break;
		case 6:
			System.out.println("\nSearch Method: Greedy Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("Greedy");
			break;
		case 7:
			System.out.println("\nSearch Method: A*");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("A*");
			break;
		case 8:
			System.out.println("\nSearch Method: Hill-Climbing Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("HCS");
			break;
		case 9:
			System.out.println("\nSearch Method: Beam Search");
			System.out.println("\n===== Queue =====\n");
			g.General_Search("BS");
			break;
		case 10:
			System.out.println("\nPrinting ALL SEARCH METHODS");
			g.General_Search("DFS");
			g.General_Search("BFS");
			g.General_Search("DLS");
			g.General_Search("IDS");
			g.General_Search("UCS");
			g.General_Search("Greedy");
			g.General_Search("A*");
			g.General_Search("HCS");
			g.General_Search("BS");
			break;
		default:
			System.err.println("Wrong Input");
			break;
		}
		in.close();

	}

	public void startTree(Graph graph, Node node) {

		LinkedList<Node> startQueue = new LinkedList<Node>();
		LinkedList<Node> path = new LinkedList<Node>();
		Node startState = graph.getS();

		// Find S Node
		if (visited.isEmpty()) {
			path = graph.getChildrenOf(startState);
			startQueue.add(startState);
			queue.add(startQueue);
			visited.add(startState.getName());
			visitedNode.add(startState);
			System.out.println("Expand " + visited.getFirst());
			queueClass.printQueue2();
			System.out.println("\n");
			queue = new LinkedList<LinkedList<Node>>();
			path = graph.getChildrenOf(node);
			path = queueClass.sortPath(path, node);
		} else {
			path = graph.getChildrenOf(node);
			path = queueClass.removedVisitedPath(path);

			// Dead End
			if (path.size() == 0) {
				// System.out.println("** Dead End " + node.getName() + "**\n");
				graph.setVisited(node);
				DeadEnd.add(node.getName());
				queue = queueClass.pop();
				node = visitedNode.getFirst();
				path = graph.getChildrenOf(node);
				path = queueClass.removedVisitedPath(path);
				path = queueClass.sortPath(path, node);

			} else {
				path = queueClass.sortPath(path, node);
				visitedNode.addFirst(node);
				visited.addFirst(node.getName());
				graph.setVisited(node);
				// printQueue(path);
			}
		}

	}

}
