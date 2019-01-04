import java.util.*;
import java.io.*;

public class Knapsack {
	static int total;
	static int W;
	static class Subject {
		int v; // value 
		int w; //weight
		
		public Subject(int value, int weight) {
			this.v = value; 
			this.w = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int[][] dp;
		ArrayList<Subject> subject = new ArrayList<Subject>();
		
			BufferedReader in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/knapsack1.txt"));
			String firstLine = in.readLine();
			W = Integer.parseInt(firstLine.split(" ")[0]);
			total = Integer.parseInt(firstLine.split(" ")[1]);
			String str;
			while ((str = in.readLine()) != null) {
				int v = Integer.parseInt(str.split(" ")[0]);
				int w = Integer.parseInt(str.split(" ")[1]);
				subject.add(new Subject(v, w));
			}  
			dp = new int[total][W + 1];
			for (int i = 0; i <= W; i++) {
				dp[0][i] = 0;
			}
			
			for (int i = 0; i < total; i++) {
				for (int x = 0; x<= W; x++) {
					int j = (i == 0) ? 0 : i - 1;
					if (x < subject.get(i).w) {
						
						dp[i][x] = dp[j][x];
					} else {
					
					dp[i][x] = Math.max(dp[j][x], dp[j][x - subject.get(i).w] + subject.get(i).v);
					}
				}
			}
			System.out.println("The answer is: " + dp[total - 1][W]);
		
	}
}
