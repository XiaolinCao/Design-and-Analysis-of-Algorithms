import java.util.*;
import java.io.*;

public class MaxWIS {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> graph;
		int[] dp;
		int n = 0;
		Set<Integer> S;
		
			BufferedReader in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/mwis.txt"));
			n = Integer.parseInt(in.readLine());
			graph = new ArrayList<Integer>();
			dp = new int[n];
			
			Scanner sca; String str;
			while ((str = in.readLine()) != null) {
				sca = new Scanner(str);
				graph.add(sca.nextInt());
			}
			
			dp[0] = 0;
			dp[1] = graph.get(0);
			for (int i = 2; i < graph.size(); i++) {
				dp[i] = Math.max(dp[i - 1], dp[i - 2] + graph.get(i - 1));
			}
			
			System.out.println(dp[2]);
			
			S = new HashSet<Integer>();
			int position = graph.size();
			while (position > 1) {
				if (dp[position - 1] >= dp[position - 2] + graph.get(position - 1)) {
					position--;
				} else {
					S.add(position);
					position -= 2;
				}
				
			}
			
			int[] test = {1, 2, 3, 4, 17, 117, 517, 997};
			for (int i = 0; i < test.length; i++) {
				if (S.contains(test[i])) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				
			}
			
			

	}

}
