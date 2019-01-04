// Strongly connected components
import java.util.*;
import java.io.*;

class Node {
	int id; // vertex id
	LinkedList<Integer> edge = new LinkedList<Integer>();
	boolean explored = false;
	int time;
	int leader;
	
	Node (int id) {
		this.id = id;
	}
	
	public void addEdge(int nodeId) {
		edge.add(nodeId);
	}
	
	public void removeEdge(int nodeId) {
		edge.remove(nodeId);
	}
}

class DirectedGraph {
	int n;
	Node[] vertex;
	
	DirectedGraph(int num) {
		this.n = num;
		vertex = new Node[num];
	}
	
	public void addNode(int id, Node newNode) {
		if (id >= 0 && id < n) {
			vertex[id - 1] = newNode;
		}
	}
	
	public void removeNode(int id) {
		vertex[id - 1 ] = null;
	}
 }

public class Sccs {
	public static int NUM = 875714;
	public static Node[] G = new Node[NUM];
	public static Node[] newG = new Node[NUM];
	public static int t = 0; // for finishing time in the 1st pass
	public static int s = 0; // for leaders in the 2nd pass
	
	public static DirectedGraph readGraph() throws IOException {
		DirectedGraph dGraph = new DirectedGraph(NUM);
		BufferedReader sccFile = new BufferedReader (new FileReader("/Users/caoxiaolin/Downloads/SCC.txt"));
		String str;
		Node newNode;
			
		while ((str = sccFile.readLine()) != null) {
				Scanner sca = new Scanner(str);
				int nodeId = sca.nextInt();
				int edge = sca.nextInt();
				
				if (dGraph.vertex[nodeId - 1] != null) {
					dGraph.vertex[nodeId - 1].addEdge(nodeId);
				} else {
					newNode = new Node(nodeId);
					newNode.addEdge(edge);
					dGraph.vertex[nodeId - 1] = newNode;
				}
		}
		
		sccFile.close();
		return dGraph;
	}
	
	public static DirectedGraph readRevGraph() throws IOException {
		DirectedGraph revGraph = new DirectedGraph(NUM);
		BufferedReader sccFile = new BufferedReader (new FileReader("/Users/caoxiaolin/Downloads/SCC.txt"));
		String str;
		Node newNode;
		while ((str = sccFile.readLine()) != null) {
			Scanner sca = new Scanner(str);
			int edge = sca.nextInt();
			int nodeId = sca.nextInt();
			
			if (revGraph.vertex[nodeId - 1] != null) {
				revGraph.vertex[nodeId - 1].addEdge(nodeId);
			} else {
				newNode = new Node(nodeId);
				newNode.addEdge(edge);
				revGraph.vertex[nodeId - 1] = newNode;
			}
		}
		sccFile.close();
		return revGraph;
	}
	
	public static void dfsLoop(DirectedGraph g) {
		
		for (int i = NUM - 1; i >= 0; i--) {
			if (g.vertex[i] != null) {
				if (!g.vertex[i].explored) {
					s = g.vertex[i].id; 
					dfs(g,g.vertex[i]);
				}
			}
		}
	}
	
	public static void dfs(DirectedGraph g, Node node) {
		node.explored = true; 
		node.leader = s;
		
		for (int i = 0; i < node.edge.size(); i++) {
			int ids = node.edge.get(i);
			if (!g.vertex[ids - 1].explored) {
				dfs(g, g.vertex[ids - 1]);
			}
		}
		t++;
		node.time = t;
		node.id = t;
		
//		g[i].explored = true;
//		g[i].leader = s;
//		LinkedList<Integer> next = new LinkedList<Integer>();
//		next = g[i].edge;
//		for (int j = 0; j < next.size(); j++) {
//			if (g[next.get(j)] != null) {
//				if (!g[next.get(j)].explored) {
//					dfs(g,j);
//				}
//			}
//		}
//		t++;
//		g[i].time = t;
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 DirectedGraph revG = readRevGraph();
		 DirectedGraph g = readGraph();
		 dfsLoop(revG);
		 dfsLoop(g);
	}

}
