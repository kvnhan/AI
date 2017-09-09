/**
 * 
 */
package Testing;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;

import SetUp.Node;
import basic_searches.Depth_First;

/**
 * @author jmetzger and kvnhan
 * @citation https://stackoverflow.com/questions/3891375/how-to-read-a-text-file-resource-into-java-unit-test
 *
 */

public class testAlgorithms {
	
	Node startState = new Node("S", 0.0, 0.0, 0);
    Depth_First dfs = new Depth_First();

    @Test
    public void test() throws Exception
    {
        //assertTrue(res.getContent().length() > 0);
        //assertTrue(res.getFile().exists());
    }
}
