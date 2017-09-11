package SetUp;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Main.java
 * @author jmetzger kvnhan jwilder
 */

public class Main {

	public static void main(String[] args) {
		
	    BufferedReader br = null;  
		FileReader fr = null;
		Graph g = new Graph();
		Initial i = new Initial();

		System.out.println("\n========================================\n=============== Project 1 ==============\n============ CS4341 : 9.11.17 ==========\n=============== Kien Nhan ==============\n============ Jonathan Metzger ==========\n============= Joseph Wilder ============\n========================================");
		System.out.println("\nPlease Select the Search Algorithm below\nyou wish to execute:");
		System.out.println ( "\n0) Print All\n1) Depth 1st Search\n2) Breadth 1st Search\n3) Depth-Limited Search\n4) Iterative Deepening Search\n5) Uniform Cost Search (Branch-and-bound)\n6) Greedy Search\n7) A*\n8) Hill-Climbing Search\n9) Beam Search\n" );
	    System.out.print ( "Selection: " );
		
	    i.parse(br, fr, args, g);
	    i.selection(g);
		
	    System.out.println("\n================== EOF =================\n\n");
	}
}
