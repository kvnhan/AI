/**
 * 
 */
package SetUp;

/**
 * @author jrmetzger
 *
 */
public class Filename {
	
	/**
	 * Put path to file here
	 */
	private static final String FILENAME = "/Users/jmetzger/Desktop/graph.txt";
	//private static final String FILENAME = "C:/Users/Jon/Desktop/graph.txt";
	//private static final String FILENAME = "C:/Users/Kien/Downloads/graph.txt";
	//private static final String FILENAME = "/Users/joe/Desktop/graph.txt";
		
	Filename(){
		
	}
	
	public String getName() {
		String file = FILENAME;
		return file;
	}
}
