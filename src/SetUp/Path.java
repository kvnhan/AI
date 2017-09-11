package SetUp;
import java.util.LinkedList;

/**
 * Path.java
 * @author jmetzger kvnhan jwilder
 */

public class Path {
	LinkedList<Node> p;
	private Double dist;
	
	public Path(LinkedList<Node> p, Double dist){
		this.p = p;
		this.setDist(dist);
	}
	
	public LinkedList<Node> getP() {
		return p;
	}
	
	public Double getDist() {
		return dist;
	}

	/**
	 * @param dist the dist to set
	 */
	public void setDist(Double dist) {
		this.dist = dist;
	}
}
