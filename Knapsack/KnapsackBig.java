import java.util.*;
import java.io.*;

public class KnapsackBig {
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
	
	public static void main(String[] args) throws IOException{
		int[][] dp;
		ArrayList<Subject> subject = new ArrayList<Subject>();
		
			BufferedReader in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/knapsackBig.txt"));
			String firstLine = in.readLine();
			W = Integer.parseInt(firstLine.split(" ")[0]);
			total = Integer.parseInt(firstLine.split(" ")[1]);
			String str;
			while ((str = in.readLine()) != null) {
				int v = Integer.parseInt(str.split(" ")[0]);
				int w = Integer.parseInt(str.split(" ")[1]);
				subject.add(new Subject(v, w));
			}  
			dp = new int[2][W + 1];
			for (int i = 0; i <= W; i++) {
				dp[0][i] = 0;
			}
			
			for (int i = 0; i < total; i++) {
				for (int x = 0; x<= W; x++) {
					int j = 0;
					if (x < subject.get(i).w) {
						
						dp[1][x] = dp[j][x];
					} else {
					
					dp[1][x] = Math.max(dp[j][x], dp[j][x - subject.get(i).w] + subject.get(i).v);
					}
				}
				for (int k = 0; k <= W; k++) {
					dp[0][k] = dp[1][k];
				}
			}
			System.out.println("The answer is: " + dp[1][W]);
		
	}
}
