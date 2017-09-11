/**
 * 
 */
package basic_searches;

import java.util.LinkedList;

import SetUp.Graph;
import SetUp.Path;

/**
 * @author jmetzger
 *
 */
public class Print_All {
	
	public Print_All(){
		
	}
	
	public void print(Graph g, LinkedList<Path> newpath, int counter) {
		System.out.print("\n=====================\n");
		System.out.println("\n- Depth First Search\n");
		g.General_Search("DFS");
		System.out.print("=====================\n\n");
		System.out.println("\n- Breadth First Search\n");
		g.General_Search("BFS");
		g.General_Search("DLS");
		g.General_Search("IDS");
		g.General_Search("UCS");
		g.General_Search("Greedy");
		g.General_Search("A*");
		g.General_Search("HCS");
		g.General_Search("BS");
	}
	

}
