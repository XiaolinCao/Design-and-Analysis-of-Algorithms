import java.util.*;
import java.io.*;

class Node {
	public int nodeId;
	public ArrayList<Integer> edge = new ArrayList<Integer>();
	public ArrayList<Integer> weight = new ArrayList<Integer>();
	
	Node(int num) {
		this.nodeId = num;
	}
	
	public void addEdge(int endEdge) {
		edge.add(endEdge);
	}
	
	public void addWeight(int cost) {
		weight.add(cost);
	}
}

public class Dijkstra {
	public static int NUM = 200;
	public static int[] X = new int[NUM];
	public static int[] V = new int[NUM];
	public static int[] ans = new int[NUM];
	
	
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < ans.length; i++) {
			ans[i] = 1000000;
		}
//		V = readGraph();
//		System.out.println("The first:" + V.get(1).nodeId + V.get(1).edge + V.get(1).weight);
		
	}
	
	
	public static ArrayList<Node> readGraph() throws FileNotFoundException, IOException {
		ArrayList<Node> g = new ArrayList<Node>();
		BufferedReader in = new BufferedReader (new FileReader("/Users/caoxiaolin/Downloads/Dijkstra.txt"));
		String str;
		Node newNode;
		while((str = in.readLine())!= null) {
			Scanner sca = new Scanner(str);
			int id = sca.nextInt();
			newNode = new Node(id);
			while (sca.hasNext()) {
				int edge = sca.nextInt();
				String comma = sca.next();
				int weight = sca.nextInt();
				newNode.addEdge(edge);
				newNode.addWeight(weight);
			}
			g.add(id-1, newNode);
		}
		in.close();
		return g;
	}
	
	public static void dijkstra(Node source) {
		X[source.nodeId - 1] = source.nodeId;
		ans[source.nodeId - 1] = 0;
		while (!X.equals(V)) {
			for (int i = 0; i < source.edge.size(); i++) {
				int id = source.edge.get(i);
				if (!X.contains(id)) {
					
				}
			}
		}
		
	}
	
	
	

}
