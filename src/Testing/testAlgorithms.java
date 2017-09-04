/**
 * 
 */
package Testing;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import SetUp.Node;
import basic_searches.DFS;

/**
 * @author jmetzger and kvnhan
 * @citation https://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test
 *
 */

public class testAlgorithms {
	
	Node startState = new Node("S", 0.0, 0.0, 0);
    DFS dfs = new DFS();

    @Test
    public void test() throws Exception
    {
        //assertTrue(res.getContent().length() > 0);
        //assertTrue(res.getFile().exists());
    }
}
