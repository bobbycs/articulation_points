import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Find articulation points and biconnected components in a graph.
 * 
 * @author Bobby Simon bobbycs - 1110547
 * 
 */
public class BiconnectedComponentFinder {

	static ArrayList<ArrayList<Integer>> edgeList;
	static ArrayList<Vertex> vertices;
	static ArrayList<String> components;
	static HashMap<Vertex, Vertex> artPoints;

	static int dfscounter, compcounter, numPoints;
	static String comp, points;

	/**
	 * @param args
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException,
			IOException {

		processInput(args[0]);

		dfscounter = 0;
		compcounter = 0;
		numPoints = 0;
		components = new ArrayList<String>();
		points = "Articulation Points:";

		// TIME ARTICULATION POINT FIND AND BICONNECTED COMPONENTS
		long startTime = System.nanoTime();

		findArticulationPoints(vertices.get(0));

		// STOP TIMER
		long endTime = System.nanoTime();

		System.out.println("Number of Biconnected Components = " + compcounter);
		System.out.println("Number of Articulation Points: " + numPoints);
		System.out.println(points);

		for (String s : components) {
			System.out.println(s);
		}

		System.out.println("Runtime: " + (endTime - startTime)
				* Math.pow(10, -9) + " seconds");

	}

	// finds articulations points of a graph
	private static void findArticulationPoints(Vertex v) {

		v.dfsNum = ++dfscounter;
		v.low = v.dfsNum;

		int vNum = vertices.indexOf(v);
		ArrayList<Integer> vList = edgeList.get(vNum);
		for (int i = 0; i < vList.size(); i++) {
			Vertex x = vertices.get(vList.get(i));

			if (x.dfsNum == -1) {
				x.parent = vNum;
				findArticulationPoints(x);
				v.low = Math.min(v.low, x.low);

				if (x.low >= v.dfsNum) {

					// for output of components
					points += "  " + vNum;
					numPoints++;
					comp = "Component " + (compcounter + 1) + ": {";

					// find biconnected components
					biconnectedComponent(v, x);

					// for output of components
					comp += "}";
					components.add(comp);
					comp = "";
					compcounter++;
				}
			} else if (v.parent != vList.get(i)) {
				v.low = Math.min(v.low, x.dfsNum);
			}
		}
	}

	// finds biconnected components of a graph using articulation points
	private static void biconnectedComponent(Vertex v, Vertex x) {

		int xNum = vertices.indexOf(x);
		if (xNum == vertices.indexOf(v) || x.removed) {
			return;
		}
		ArrayList<Integer> xList = edgeList.get(xNum);
		for (int i = 0; i < xList.size(); i++) {
			int xIn = xList.get(i);
			if (!vertices.get(xIn).removed) {
				comp += "{" + xNum + "," + xList.get(i) + "}";
				x.removed = true;
				biconnectedComponent(v, vertices.get(xIn));
			}

		}

	}

	// preprocessing the input and builds adjacency list representation of the
	// graph
	private static void processInput(String filename)
			throws NumberFormatException, IOException {

		BufferedReader fileIn = new BufferedReader(new FileReader(filename));
		int numVertices = Integer.parseInt(fileIn.readLine());

		// initialize vertices
		vertices = new ArrayList<Vertex>(numVertices);
		for (int i = 0; i < numVertices; i++) {
			vertices.add(new Vertex());
		}

		// initialize adjacency list
		edgeList = new ArrayList<ArrayList<Integer>>(numVertices);
		for (int i = 0; i < numVertices; i++) {
			edgeList.add(new ArrayList<Integer>());
		}

		String oneLine;
		fileIn.read();
		while ((oneLine = fileIn.readLine()) != null) {
			String[] tokens = oneLine.split(" ", 2);
			fileIn.read();

			addEdge(Integer.parseInt(tokens[0].trim()),
					Integer.parseInt(tokens[1].trim()));
			addEdge(Integer.parseInt(tokens[1].trim()),
					Integer.parseInt(tokens[0].trim()));

		}

		int edges = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			edges += edgeList.get(0).size();
		}
		edges /= 2;

		System.out.println("Number of Nodes: " + numVertices);
		System.out.println("Number of Edges: " + edges);
	}

	// adds an edge to the adjacency list
	private static void addEdge(int n1, int n2) {
		edgeList.get(n1).add(n2);
	}

}
