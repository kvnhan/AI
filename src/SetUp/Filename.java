package SetUp;
import java.io.File;

/**
 * Filename.java
 * @author jmetzger kvnhan jwilder
 */

public class Filename {
	
	private String FILENAME = null;
	

	Filename(){}
	
	public String getName(String[] args) {
		
		/**
		 * Put path to file here
		 */
		//FILENAME = "src/graph.txt";
		FILENAME = "src/BonusProblem.txt";
		//FILENAME = "src/second_graph.txt";
		//FILENAME = "C:/Users/Jon/Desktop/graph.txt";
		//FILENAME = "C:/Users/Kien/Downloads/graph.txt";
		//FILENAME = "/Users/joe/Desktop/graph.txt";
			

		String filename = FILENAME;
		
		if (filename == null) {
			String text = args[0];
			File file = new File(text);
			String path = file.getAbsolutePath();
			return path;
		}
		return filename;
		

	}
}
