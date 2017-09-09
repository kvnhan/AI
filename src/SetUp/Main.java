package SetUp;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * main.java
 * @author jmetzger kvnhan jwilder
 * @version 1.2
 */


public class Main {

	public static void main(String[] args) {
		
	    BufferedReader br = null;  
		FileReader fr = null;
		//Node startState = new Node("S", 0.0, 0.0, 0);
		Graph g = new Graph();
		//LinkedList<Node> startQueue = new LinkedList<Node>();
		Initial i = new Initial();

		System.out.println("\n===== Project 1 =====");
		System.out.println("\nPlease Select the Search Algorithm below you wish to execute:");
		System.out.println ( "\n1) Depth 1st Search\n2) Breadth 1st Search\n3) Depth-Limited Search\n4) Iterative Deepening Search\n5) Uniform Cost Search (Branch-and-bound)\n6) Greedy Search\n7) A*\n8) Hill-Climbing Search\n9) Beam Search\n10)PRINT ALL\n" );
	    System.out.print ( "Selection: " );
		
	    i.parse(br, fr, args, g);
	    i.selection(g);
		
	    System.out.println("\n===== EOF =====\n\n");
	}


}
