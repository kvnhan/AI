package basic_searches;
import SetUp.Graph;

/**
 * Print_All.java
 * @author jmetzger kvnhan jwilder
 */

public class Print_All {
	
	public Print_All(){
		
	}
	
	public void print(Graph g) {
		System.out.println("\nSearch Method: Depth 1st Search");
		g.General_Search("DFS");
		System.out.println("\nSearch Method: Breadth 1st Search");
		g.General_Search("BFS");
		System.out.println("\nSearch Method: Depth-Limited Search (Limit = 2)");
		g.General_Search("DLS");
		System.out.println("\nSearch Method: Iterative Deepening Search");
		g.General_Search("IDS");
		System.out.println("\nSearch Method: Uniform Cost Search (Branch-and-bound)");
		g.General_Search("UCS");
		System.out.println("\nSearch Method: Greedy Search");
		g.General_Search("Greedy");
		System.out.println("\nSearch Method: A*");
		g.General_Search("A*");
		System.out.println("\nSearch Method: Hill-Climbing Search (no backtracking)");
		g.General_Search("HCS");
		System.out.println("\nSearch Method: Beam Search (w = 2)");
		g.General_Search("BS");
	}
	

}
