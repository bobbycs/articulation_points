/**
 * Represents a vertex in a graph.
 * @author Bobby Simon
 */
public class Vertex {

	protected int dfsNum;
	protected int low;
	protected int parent;
	protected boolean removed;
	
	public Vertex() {
		dfsNum = -1;
		low = -1;
		parent = -1;
		removed = false;
	}
	
}
