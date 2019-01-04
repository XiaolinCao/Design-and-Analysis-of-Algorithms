import java.util.*;
import java.io.*;
public class Clustering {
	static int[][] array; // parent and rank
	
	static class Edge implements Comparable<Edge> {
		int i;
		int j;
		int cost;
		public Edge(int i , int j , int cost) {
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge other) {
			if (this.cost >= other.cost) return 1;
			else return -1;
		}
		
		
	}
	
	
	// use path compression 
	public static int find(int i) {
		if (array[i][0] != i) {
			array[i][0] = find(array[i][0]);
		}
		return array[i][0];
	}
	
	
	
	public static void union(int i, int j) {
		int parenti = find(i);
		int parentj = find(j);
		
		if (parenti != parentj) {
			int ranki = array[i][1];
			int rankj = array[j][1];
			if (ranki <= rankj) {
				array[i][0] = parentj;
				if (ranki == rankj) rankj++;
				array[j][1] = rankj;
				
			}
			
			if (ranki > rankj) {
				array[j][0] = parenti;
			}
		}
	}
	
	public static int numOfClusters(int[][] test) {
		
		Set<Integer> distinct = new HashSet<Integer>();
		for (int i = 0; i < test.length - 1; i++) {
			distinct.add(array[i][0]);
		}
		return distinct.size();
	}
	
		
	
	public static void main(String[] args) throws FileNotFoundException, NumberFormatException, IOException{
		// TODO Auto-generated method stub
		ArrayList<Edge> edges = new ArrayList<Edge>();
//		ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
		int k = 4;
		
		int n = 0;
		
			FileInputStream f = new FileInputStream("/Users/caoxiaolin/Downloads/clustering.txt");
			DataInputStream d = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(d));
			n = Integer.parseInt(b.readLine());
			
			array = new int[n][2];
			
			for(int i = 0; i < n; i++) { 
				array[i][0] = i; // define each node's parent as itself
				array[i][1] = 0;
			}
			
			String str; int source, dest, cost;
			while((str = b.readLine()) != null) {
				source = Integer.parseInt(str.split(" ")[0]) - 1; // indexed by node - 1
				dest = Integer.parseInt(str.split(" ")[1]) - 1;
				cost = Integer.parseInt(str.split(" ")[2]);
				edges.add(new Edge(source, dest, cost));
			}
			Collections.sort(edges);
			 
				for(Edge e: edges) {
					union(e.i, e.j);
					
				}
			
			
			int max = Integer.MAX_VALUE;
			for (Edge e: edges) {
				if (find(e.i) != find(e.j)) max = Math.min(max, e.cost);
			}
			System.out.println("maximum distance " + max);
		

	}

}
