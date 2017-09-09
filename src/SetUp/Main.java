package SetUp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import basic_searches.BFS;
import basic_searches.DFS;
import basic_searches.DLS;
import basic_searches.IDS;

/**
 * main.java
 * @author jmetzger kvnhan jwilder
 * @version 1.2
 */


public class Main {

	//private static final String FILENAME = "C:/Users/Kien Nhan/Downloads/graph.txt";

	public static void main(String[] args) {
		
	    BufferedReader br = null;  
		FileReader fr = null;
		Node startState = new Node("S", 0.0, 0.0, 0);
		Graph g = new Graph();
		LinkedList<Node> startQueue = new LinkedList<Node>();

		try{
			
			String sCurrentLine;
			String text = args[0];
			File file = new File(text);
			String path = file.getAbsolutePath();
			Node node1, node2;
			try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			}catch(Exception e){
				System.out.println("Sorry");
			}
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				System.out.println(sCurrentLine);
				
				String[] token = sCurrentLine.split("\\s+");
				if(!(token.equals("#####"))){
					if(token.length == 3){
						node1 = new Node(token[0], 0.0, Double.parseDouble(token[2]), 0);
						node2 = new Node(token[1], 0.0, Double.parseDouble(token[2]), 0);
						node1.pairNode(node2);
						node1.addEdge(node2, Double.parseDouble(token[2]));
						node2.addEdge(node1, Double.parseDouble(token[2]));
						g.createGraph(node1, node2);
						
					}else if(token.length == 2){
						Node oldnode = new Node(token[0], 0.0, 0.0, 0);
						g.heuristic_dict.put(token[0], Double.parseDouble(token[1]));
						if(g.NodeExist(oldnode)){
							g.changeNodeCost(oldnode, Double.parseDouble(token[1]));

						}
					}
				}
			}
			System.out.println("---BFS---");
			//g.General_Search("BFS");
			System.out.println("---DFS---");
			//g.General_Search("DFS");
			System.out.println("---DLS---");
			//g.General_Search("DLS");
			System.out.println("---UCS---");
			//g.General_Search("UCS");
			System.out.println("---Greedy---");
			//g.General_Search("Greedy");
			System.out.println("---A*---");
			//g.General_Search("A*");
			
			Scanner in = new Scanner ( System.in );
			System.out.println("\n===== Project 1 =====");
			System.out.println("\nPlease Select the Search Algorithm below you wish to execute:");
			System.out.println ( "\n1) Depth 1st Search\n2) Breadth 1st Search\n3) Depth-Limited Search\n4) Iterative Deepening Search\n5) Uniform Cost Search (Branch-and-bound)\n6) Greedy Search\n7) A*\n8) Hill-Climbing Search\n9) Beam Search\n" );
		    System.out.print ( "Selection: " );
		    switch ( in.nextInt() ) {
		      case 1:
		        System.out.println ( "\nSearch Method: Depth 1st Search" );
		        System.out.println("\n===== Queue =====\n");
		        DFS dfs = new DFS();
		        dfs.dfs(g, startState);
		        break;
		      case 2:
		        System.out.println ( "\nSearch Method: Breadth 1st Search" );
		        System.out.println("\n===== Queue =====\n");
		        BFS bfs = new BFS();
		        bfs.bfs(g, startState, startState);
		        break;
		      case 3:
			    System.out.println ( "\nSearch Method: Depth-Limited Search" );
			    System.out.println("\n===== Queue =====\n");
			    DLS dls = new DLS();
			    dls.dls(g, startState, startState);
			    break;
		      case 4:
				System.out.println ( "\nSearch Method: Iterative Deepening Search" );
			    System.out.println("\n===== Queue =====\n");
			    IDS ids = new IDS();
			    ids.ids(g, startState, startState, 0, startQueue);
			    break;
		      case 5:
				System.out.println ( "\nSearch Method: Uniform Cost Search (Branch-and-bound)" );
			    System.out.println("\n===== Queue =====\n");
			    //UCS ucs = new UCS();
			    //ucs.ucs(g, startState, startState);
			    break;
		      case 6:
				System.out.println ( "\nSearch Method: Greedy Search" );
			    System.out.println("\n===== Queue =====\n");
			    //GS gs = new GS();
			    //gs.gs(g, startState, startState);
			    break;
		      case 7:
				System.out.println ( "\nSearch Method: A*" );
			    System.out.println("\n===== Queue =====\n");
			    //AS as = new AS();
			    //as.as(g, startState, startState);
			    break;
		      case 8:
				System.out.println ( "\nSearch Method: Hill-Climbing Search" );
			    System.out.println("\n===== Queue =====\n");
			    //hcs hcs = new HCS();
			    //hcs.hcs(g, startState, startState);
			    break;
		      case 9:
				System.out.println ( "\nSearch Method: Beam Search" );
			    System.out.println("\n===== Queue =====\n");
			    //bs bs = new BS();
			    //bs.bs(g, startState, startState);
			    break;
		      default:
		        System.err.println ( "Wrong Input" );
		        return;
		    }

			System.out.println("\n===== EOF =====\n\n");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getClass());
			System.out.println(e.toString());
			System.out.println("No file exists");
			
		}
		
	}

}
