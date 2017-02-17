package metier;

public class FermetureTransitive {

	/**
	 * this generate the transitive closure of a matrix 
	 * @param graph
	 * @return fermetureClosure matrix
	 */
	public int[][] genererFermetureTransitive(int graph[][]) {
		final int V = graph.length;
		int reach[][] = new int[V][V];
		int i, j, k;

		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				reach[i][j] = graph[i][j];

		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					reach[i][j] = (reach[i][j] != 0) 
							|| ((reach[i][k] != 0) && (reach[k][j] != 0)) ? 1 : 0;
				}
			}
		}
		return reach;
	}
	

//	public static void main(String[] args) {
//		FermetureTransitive ft = new FermetureTransitive();
//		int [][] g = {
//				{0,1,0},
//				{0,0,1},
//				{0,0,0}
//		};
//		ft.printSolution(g);
//		System.out.println("Following matrix is transitive closure"+
//                " of the given graph");
//		int mat[][] = ft.genererFermetureTransitive(g);
//		ft.printSolution(mat);
//		ft.genererFermetureTransitive(g);
//		
//	}
	
}
