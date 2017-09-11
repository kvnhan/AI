package SetUp;

/**
 * Edge.java
 * @author jmetzger kvnhan jwilder
 */

public class Edge {
	Node to;
	Double dist;
	
	public Edge( Node to,Double dist){
		this.to = to;
		this.dist = dist;
	}
}
