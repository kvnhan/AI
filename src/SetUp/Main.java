package SetUp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import basic_searches.DFS;


public class Main {

	public static void main(String[] args) {
		
	    BufferedReader br = null;  
		FileReader fr = null;
		Node startState = new Node("S", 0.0, 0.0, 0);
		Graph g = new Graph();
		DFS dfs = new DFS();

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
			
			System.out.println(g.getSize());
			//g.DFS(startState);
			dfs.dfs(g, startState);
			System.out.println("It works");
			
		}catch(Exception e){
			
			System.out.println("No file exists");
			
		}
		
	}

}
