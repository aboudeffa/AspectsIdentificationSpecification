package metier;
import java.util.*;

public class PlusLongChemin {

	private int maxVertices, maxEdges;
	private int nVertices, nEdges;
	private String[] vertices;
	private double edgeCost[];
	private int edgeFrom[];
	private int edgeTo[];
	Random r = new Random();
	private String startNode;
	private String endNode;
	double INF = Double.MAX_VALUE;

	private final Set<String> settledV = new HashSet<String>();
	private PriorityQueue<String> unSettledV;
	private final Map<String, Double> d = new HashMap<String, Double>();
	private final Map<String, String> pred = new HashMap<String, String>();

//	public static void main(String args[]) {
//		PlusLongChemin p = new PlusLongChemin();
//		int matrice[][] = {
//				{0,1,1,0,0},
//				{0,0,1,1,0},
//				{0,0,0,0,1},
//				{0,0,0,0,1},
//				{0,0,0,0,0}
//		};
//		String path = p.chercherPlusLongChemin(matrice);
//		System.out.println("Critical Path: " + path);
//	}
	
	public String chercherPlusLongChemin(int[][] mat){
		PlusLongChemin graph = new PlusLongChemin();
		Vector<String> noeuds = new Vector<String>();
		graph.LongestPath(mat.length, 2*mat.length);
		graph.quiet();
		for (int l = 0; l < mat.length; l++) {
			graph.addVertex(l+1+"");
			noeuds.addElement(l+1+"");
		}
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] == 1) {
					if ((i == 1) || (j == 1))
						graph.addEdge(noeuds.elementAt(i), noeuds.elementAt(j), 1);
					else
						graph.addEdge(noeuds.elementAt(i), noeuds.elementAt(j), 0.5);
				}
			}
		}
		graph.criticalPath();
		return graph.verticesCriticalPath();
	}
	
	void LongestPath(int maxVertices, int maxEdges) {
		this.maxVertices = maxVertices;
		this.maxEdges = maxEdges;
		nVertices = 0;
		nEdges = 0;
		vertices = new String[maxVertices];
		edgeCost = new double[maxEdges];
		edgeFrom = new int[maxEdges];
		edgeTo = new int[maxEdges];
		print = true;
		unSettledV = new PriorityQueue<String>(maxVertices,minDistanceComparator);
	}
	
	private final Comparator<String> minDistanceComparator = new Comparator<String>() {
		@Override
		public int compare(String from, String to) {
			double result = getMinDistance(from) - getMinDistance(to);
			return (int) ((result == 0) ? from.compareTo(to) : result);
		}
	};

	private int idxNextVertex[];
	private double lengthCriticalPath = 0.0;
	private boolean print;
	
	public void quiet() {
		print = false;
	}

	public void addVertex(String vert) {
		if (nVertices >= maxVertices) {
			throw new IndexOutOfBoundsException("No space for more vertices");
		}
		boolean found = false;
		for (int i = 0; i < nVertices; i++) {
			if (vertices[i].equals(vert))
				found = true;
		}
		if (found) {
			System.out.println("GRAPH: vertex " + vert + " is already known");
		} else {
			if (print)
				System.out.println("GRAPH: Add Vertex: " + vert);
			vertices[nVertices] = vert;
			nVertices++;
		}
	}

	public void addEdge(String from, String to, double cost) {
		int i1, i2;
		i1 = findVertex(from);
		i2 = findVertex(to);
		if (nEdges >= maxEdges) {
			throw new ArrayIndexOutOfBoundsException("No space for more edges");
		}
		if (print)
			System.out.println("GRAPH: Add Edge: " + from + " - " + to + " : " + cost);
		edgeCost[nEdges] = cost;
		edgeFrom[nEdges] = i1;
		edgeTo[nEdges] = i2;
		nEdges++;
	}

	public void newCost(String vert1, String vert2, double cost) {
		int i1, i2, i;
		boolean found;
		i1 = findVertex(vert1);
		i2 = findVertex(vert2);
		found = false;
		for (i = 0; i < nEdges; i++) {
			if (i1 == edgeFrom[i] && i2 == edgeTo[i]) {
				if (print)
					System.out.println("GRAPH: New Cost for edge: " + vert1 + " - " + vert2 + " : " + cost);
				edgeCost[i] = cost;
				found = true;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Edge " + vert1 + " - " + vert2
					+ " not in graph");
		}
	}

	public void criticalPath() {
		idxNextVertex = new int[nVertices];
		if (print)
			System.out.println("GRAPH: Find critical path");
		this.getStart();
		this.getEnd();
		setMinDistance(startNode, 0); 		
		 runBellmanFord(); 				
	}
	
	private void runBellmanFord() {
		double[] distance = new double[nVertices];
		Arrays.fill(distance, INF);
		distance[findVertex(startNode)] = 0;
		pred.put(startNode, null);
		for (int i = 0; i < nVertices; ++i)
			for (int j = 0; j < nEdges; ++j) {
				if (distance[edgeFrom[j]] == INF)
					continue;
				double newDistance = distance[edgeFrom[j]] - edgeCost[j];
				if (newDistance < distance[edgeTo[j]]) {
					distance[edgeTo[j]] = newDistance;
					pred.put(vertices[edgeTo[j]], vertices[edgeFrom[j]]);
				}
			}
		for (int i = 0; i < nEdges; ++i)
			if (distance[edgeFrom[i]] != INF
					&& distance[edgeTo[i]] > distance[edgeFrom[i]]
							+ edgeCost[i]) {
				 if (print) System.out.println("Cycles detected!");
				return;
			}

		for (int i = 0; i < distance.length; ++i)
			if (distance[i] == INF)
				System.out.println("There's no path between " + startNode
						+ " and " + endNode);
		populatePath(distance);
	}

	private void runForwardsPropagation() {
		double currPath = 0.0;
		String curr = startNode;
		idxNextVertex[findVertex(endNode)] = -1;
		int best = 0;
		double largestPath = 0;
		while (curr != endNode) {
			largestPath = 0;
			for (int i = 0; i < nEdges; i++) {
				if (vertices[edgeFrom[i]] == curr) {
					double c = edgeCost[i] + currPath;
					if (c > largestPath) {
						largestPath = c;
						best = edgeTo[i];
					}
				}
			}
			idxNextVertex[findVertex(curr)] = best;
			curr = vertices[best];
			lengthCriticalPath += largestPath;
		}
	}

	private void runBackwardsPropagation() {
		double currPath = 0.0;
		String curr = endNode;
		idxNextVertex[findVertex(endNode)] = -1;
		int best = 0;
		while (curr != startNode) {
			for (int i = 0; i < nEdges; i++) {
				if (vertices[edgeTo[i]] == curr) {
					double c = edgeCost[i] + currPath;
					if (c > lengthCriticalPath)
						lengthCriticalPath += c;
					idxNextVertex[edgeFrom[i]] = findVertex(curr);
					best = i;
				}
			}
			curr = vertices[edgeFrom[best]];
		}
	}

	private void runDijkstra() {
		String vert;
		while ((vert = extractMinDist()) != null) {
			if (vert == vertices[findVertex(endNode)])
				break;
			settledV.add(vert);
			relaxNeighbors(vert);
		}
		if (print)
			for (Map.Entry<String, String> vertex : pred.entrySet()) {
				System.out.println(vertex);
			}
		lengthCriticalPath = d.remove(endNode);
		populatePath2();
	}

	private void populatePath(double[] distance) {
		idxNextVertex[findVertex(endNode)] = -1; 
		String vertex = endNode;
		String p = "";
		while (true) {
			for (Map.Entry<String, String> previous : pred.entrySet()) {
				if (previous.getKey() == vertex) {
					double maxDist = INF;
					if (previous.getValue() != null) {
						if (vertices[findVertex(previous.getKey())] == vertex
								&& distance[findVertex(previous.getValue())] < maxDist) {
							maxDist = distance[findVertex(previous.getKey())];
							p = vertices[findVertex(previous.getValue())];
						}
					} else {
						p = null;
						break;
					}
				}
				if (print)
					System.out.println("finished with predecessors");
			}
			int previous = 0;
			if (p != null) {
				double edgeC = 0;
				for (int i = 0; i < maxEdges; i++) {
					if (vertices[edgeFrom[i]] == p
							&& vertices[edgeTo[i]] == vertex)
						edgeC = edgeCost[i];
				}
				lengthCriticalPath += edgeC;
				previous = findVertex(p);
				idxNextVertex[previous] = findVertex(vertex);
				vertex = vertices[previous];
			} else
				break;
		}
		if (print)
			System.out.println("finished populating");
	}

	/**
	 * This is the path population for Dijkstra's algorithm
	 */
	private void populatePath2() {
		idxNextVertex[findVertex(endNode)] = -1; 
		String vertex = endNode;
		while (true) {
			String p = pred.get(vertex);
			int previous = 0;
			if (p != null) {
				previous = findVertex(p);
				idxNextVertex[previous] = findVertex(vertex);
				vertex = vertices[previous];
			} else
				break;
		}
	}

	private void relaxNeighbors(String vert) {
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[edgeFrom[i]] == vert) { 
				if (!isSettled(vertices[edgeTo[i]])) { 
					double shortestDist = getMinDistance(vert) + edgeCost[i]; 
					if (shortestDist < getMinDistance(vertices[edgeTo[i]])) { 
						setMinDistance(vertices[edgeTo[i]], shortestDist);
						pred.put(vertices[edgeTo[i]], vert); 
					}
				}
			}
		}
	}

	private boolean isSettled(String vertex) {
		return settledV.contains(vertex);
	}

	public void setMinDistance(String vertex, double distance) {
		unSettledV.remove(vertex); 
		d.put(vertex, distance);
		unSettledV.add(vertex); 
	}

	private double getMinDistance(String from) {
		Double dist = d.get(from);
		return (dist == null) ? Integer.MAX_VALUE : dist;
	}

	private void setPred(String vertex, String predes) {
		pred.put(vertex, predes);
	}

	public String getPred(String vertex) {
		return pred.get(vertex);
	}

	public String extractMinDist() {
		return unSettledV.poll();
	}

	public String verticesCriticalPath() {
		String list = vertices[0];
		int currentIdx = 0;
		while (idxNextVertex[currentIdx] >= 0) {
			currentIdx = idxNextVertex[currentIdx];
			list += " - " + vertices[currentIdx];
		}
		return list;
	}

	public double criticalPathLength() {
		return lengthCriticalPath;
	}

	public void print() {
		System.out.println("The current graph can have a maximum of "
				+ maxVertices + " vertices and " + maxEdges + " Edges.");
		System.out.println("Current dimensions are " + nVertices
				+ " vertices and " + nEdges + " Edges.");
		System.out.println("List of Vertices:");
		System.out.print("  ");
		for (int i = 0; i < nVertices; i++)
			System.out.print(vertices[i] + "  ");
		System.out.println();
		System.out.println("List of Edges:");
		for (int i = 0; i < nEdges; i++)
			System.out.println(vertices[edgeFrom[i]] + " - "
					+ vertices[edgeTo[i]] + " : Cost = " + edgeCost[i]);

	}

	private void getEnd() {
		for (int i = 0; i < vertices.length; i++) {
			int from = 0;
			if (vertices[i] != null)
				from = findVertex(vertices[i]);
			for (int j = 0; j < edgeFrom.length; j++) {
				if (edgeFrom[from] != from)
					endNode = vertices[i];
			}
		}
		if (print)
			System.out.println(endNode);
	}

	private void getStart() {
		startNode = vertices[0];
		if (print)
			System.out.println(startNode);
	}

	private int findVertex(String vertex) {
		int i1 = -1;
		boolean found = false;
		for (int i = 0; i < nVertices; i++) {
			if (vertices[i].equals(vertex)) {
				found = true;
				i1 = i;
				break;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Vertex " + vertex
					+ " not in graph");
		}
		return i1;
	}

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

}
