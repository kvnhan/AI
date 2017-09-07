package SetUp;

import java.util.LinkedList;

public class Path {
	LinkedList<Node> p;
	Double dist;
	
	public Path(LinkedList<Node> p, Double dist){
		this.p = p;
		this.dist = dist;
	}
}
