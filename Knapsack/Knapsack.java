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
	
	public static void main(String[] args) {
		int[][] A;
		ArrayList<Subject> subject = new ArrayList<Subject>();
		try {
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
			A = new int[total][W + 1];
			for (int i = 0; i <= W; i++) {
				A[0][i] = 0;
			}
			
			for (int i = 0; i < total; i++) {
				for (int x = 0; x<= W; x++) {
					int j = (i == 0) ? 0 : i - 1;
					if (x < subject.get(i).w) {
						
						A[i][x] = A[j][x];
					} else {
					
					A[i][x] = Math.max(A[j][x], A[j][x - subject.get(i).w] + subject.get(i).v);
					}
				}
			}
			System.out.println("The answer is: " + A[total - 1][W]);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
