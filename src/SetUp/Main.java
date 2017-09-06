package SetUp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import basic_searches.BFS;
import basic_searches.DFS;
import basic_searches.DLS;


public class Main {

	public static void main(String[] args) {
		
	    BufferedReader br = null;  
		FileReader fr = null;
		Node startState = new Node("S", 0.0, 0.0, 0);
		Graph g = new Graph();

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
						g.createGraph(node1, node2);
						
					}else{
						Node oldnode = new Node(token[0], 0.0, 0.0, 0);
						if(g.NodeExist(oldnode)){
							g.changeNodeCost(oldnode, Double.parseDouble(token[1]));	
						}
					}
				}
			}
			

			Scanner in = new Scanner ( System.in );
			System.out.println("\n===== Project 1 =====");
			System.out.println("\nPlease Select the Search Algorithm below you wish to execute:");
			System.out.println ( "\n1) Depth 1st Search\n2) Breadth 1st Search\n3) Depth-Limited Search\n" );
		    System.out.print ( "Selection: " );
		    switch ( in.nextInt() ) {
		      case 1:
		        System.out.println ( "You picked Depth 1st Search" );
		        System.out.println("\n===== Queue =====\n");
		        DFS dfs = new DFS();
		        dfs.dfs(g, startState);
		        break;
		      case 2:
		        System.out.println ( "You picked Breadth 1st Search" );
		        System.out.println("\n===== Queue =====\n");
		        BFS bfs = new BFS();
		        bfs.bfs(g, startState);
		        break;
		      case 3:
			    System.out.println ( "You picked Depth-Limited Search" );
			    System.out.println("\n===== Queue =====\n");
			    DLS dls = new DLS();
			    dls.dls(g, startState);
			    break;
		      default:
		        System.err.println ( "Unrecognized option" );
		        break;
		    }
			System.out.println("It works");
			System.out.println("\n===== EOF =====\n\n");
			
		}catch(Exception e){
			
			System.out.println("No file exists");
			
		}
		
	}

}
