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
	
	public static void main(String[] args) {
		int[][] A;
		ArrayList<Subject> subject = new ArrayList<Subject>();
		try {
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
			A = new int[2][W + 1];
			for (int i = 0; i <= W; i++) {
				A[0][i] = 0;
			}
			
			for (int i = 0; i < total; i++) {
				for (int x = 0; x<= W; x++) {
					int j = 0;
					if (x < subject.get(i).w) {
						
						A[1][x] = A[j][x];
					} else {
					
					A[1][x] = Math.max(A[j][x], A[j][x - subject.get(i).w] + subject.get(i).v);
					}
				}
				for (int k = 0; k <= W; k++) {
					A[0][k] = A[1][k];
				}
			}
			System.out.println("The answer is: " + A[1][W]);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
