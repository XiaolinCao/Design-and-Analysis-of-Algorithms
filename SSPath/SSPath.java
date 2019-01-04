import java.io.*;
/*
 * The first line indicates the number of vertices and edges, respectively. Each subsequent 
 * line describes an edge (the first two numbers are its tail and head, respectively) and its length 
 * (the third number). NOTE: some of the edge lengths are negative. NOTE: These graphs may or may 
 * not have negative-cost cycles. Your task is to compute the "shortest shortest path". 
 * Precisely, you must first identify which, if any, of the three graphs have no negative cycles. 
 * For each such graph, you should compute all-pairs shortest paths and remember the smallest one 
 * (i.e., compute \min_{u,v \in V} d(u,v)min u,v∈V d(u,v), where d(u,v)d(u,v) denotes 
 * the shortest-path distance from uu to vv).
 * If each of the three graphs has a negative-cost cycle, then enter "NULL" in the box below. 
 * If exactly one graph has no negative-cost cycles, then enter the length of its shortest shortest 
 * path in the box below. If two or more of the graphs have no negative-cost cycles, then enter the 
 * smallest of the lengths of their shortest shortest paths in the box below.
 * OPTIONAL: You can use whatever algorithm you like to solve this question. If you have extra time, 
 * try comparing the performance of different all-pairs shortest-path algorithms!
 * OPTIONAL: Here is a bigger data set to play with.


 */


public class SSPath {
	static int[][] graph;
	static int[][][] dp;
	static int totalNum;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int file = 1;
		BufferedReader in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/g1.txt"));; 
		
		while (file <= 3) {
			if (file == 2) {
				in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/g2.txt"));
			} else if (file == 3) {
				in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/g3.txt"));
			}
		
			totalNum = Integer.parseInt(in.readLine().split(" ")[0]); // number of vertices
			graph = new int[totalNum][totalNum];
			dp = new int[totalNum][totalNum][2];
			for (int i = 0; i < totalNum; i++) {
				for (int j = 0; j < totalNum; j++) {
					if (i == j) {
						graph[i][j] = 0;
					} else {
						graph[i][j] = 9999999;
					}
				}
			}
			
			String str; int s, v, w;
			while ((str = in.readLine()) != null) {
				s = Integer.parseInt(str.split(" ")[0]); // source vertex
				v = Integer.parseInt(str.split(" ")[1]); // destination vertex
				w = Integer.parseInt(str.split(" ")[2]); // weight
				graph[s - 1][v - 1] = w;
			}
			
			for (int i = 0; i < totalNum; i++) 
				for (int j = 0; j < totalNum; j++) 
					if (i == j) dp[i][j][0] = 0;
					else dp[i][j][0] = graph[i][j];
					
				
			
			
			for (int k = 0; k < totalNum; k++) {
				for (int i = 0; i < totalNum; i++) 
					for (int j = 0; j < totalNum; j++) {
						dp[i][j][1] = Math.min(dp[i][j][0], dp[i][k][0] + dp[k][j][0]);
					}
				
			
				for (int i = 0; i < totalNum; i++) 
					for (int j = 0; j < totalNum; j++) {
						dp[i][j][0] = dp[i][j][1];
		 			}
			}
			
			int i = 0;
			for (i = 0; i < totalNum; i++) {
				if (dp[i][i][0] < 0) {
					System.out.println("dp[i][i][0]" + i + " " + dp[i][i][0]);
					System.out.println("Graph " + file + " has a negative cycle.");
					break;
				}
				
			}
			if (i == totalNum) {
				System.out.println("Graph " + file + " has no negative cycle.");
				int min = 0;
				for (int ix = 0; ix < totalNum; ix++) {
					for (int j = 0; j < totalNum; j++) {
						min = Math.min(min, dp[ix][j][0]);
					}
				}
				System.out.println("minimum cost " + min);
			}
			file++;
		}
	

	}
}
