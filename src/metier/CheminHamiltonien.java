package metier;

import java.util.Vector;

public class CheminHamiltonien {

	static int len;
	static int[] path;
	static Vector<Integer> v = new Vector<Integer>();
	static int count = 0;

	public Vector<Integer> chercherCheminHamilronien(int[][] graph) {
		this.HamiltonPath(graph, 1);
		return v;
	}

	public void HamiltonPath(int[][] graph, int start) { 
 		len = graph.length;
		path = new int[len];
		int i;
		for (i = start - 1; i < start; i++) { 
			path[0] = i + 1;
			findHamiltonpath(graph, 0, i, 0);
		}
	}

	public void findHamiltonpath(int[][] M, int x, int y, int l) {
		int i;
		for (i = x; i < len; i++) { 
			if (M[i][y] != 0) { 
				if (detect(path, i + 1))
					continue;
				l++; 
				path[l] = i + 1; 
				if (l == len - 1) {
					count++;
					if (count == 1)
					for (int j : path) {
						v.addElement(j);
					}
					l--;
				}
				M[i][y] = M[y][i] = 0; 
				findHamiltonpath(M, 0, i, l);
				l--;
				M[i][y] = M[y][i] = 1; 
			}
		}
		path[l + 1] = 0;
	} 
	
	private boolean detect(int[] x, int target) {
		boolean t = false;
		for (int i : x) {
			if (i == target) {
				t = true;
				break;
			}
		}
		return t;
	}
		
//	public static void main(String[] args) {
//		int[][] y = { { 0, 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 0, 1 },
//				{ 0, 1, 0, 1, 1, 0 }, { 0, 0, 1, 0, 0, 0 },
//				{ 0, 0, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1, 0 } };
//		CheminHamiltonien chham = new CheminHamiltonien();
//		chham.chercherCheminHamilronien(y);
//		for (int i=0; i<v.size();i++) {
//			System.out.print(v.elementAt(i)+" ");
//		}
//		
//	}
}
