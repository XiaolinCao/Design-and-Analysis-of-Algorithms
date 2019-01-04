// Week 4 assignment: implement the contraction algorithm 
// Minimum cut is 17
import java.util.*;
import java.io.*;
import java.io.IOException;
// class for vertex with edges
class Vertex {
	public int vertexId;
	public LinkedList<Integer> edge = new LinkedList<Integer>();
	
	Vertex(int id) {
		this.vertexId = id;
	}
	
	// add a new edge to the vertex
	public void addEdge(int endVertex) {
		edge.add(endVertex);
	}
	
	// remove an edge from the vertex 
	public void removeEdge(Integer vertexId) {
		if(edge.contains(vertexId)) {
			edge.remove(vertexId);
		}
	}
	
	// return the size of the vertex
	public int sizeOfVertex() {
		return edge.size();
	}
}

class AdjacencyLists {
	public Hashtable<Integer, Vertex> vertices = new Hashtable<Integer, Vertex>();
	
	// add a new vertex to the graph
	public void addVertex(int id, Vertex newVertex) {
		vertices.put(id, newVertex);
	}
	
	// remove an edge 
	public void removeEdge(int vertexId1, int vertexId2) {
		Vertex node1 = vertices.get(vertexId1);
		Vertex node2 = vertices.get(vertexId2);
		
		if (node1 != null && node2 != null) {
			node1.removeEdge(vertexId2);
			node2.removeEdge(vertexId1);
		}
	}
	
	// return the number of Vertices in the graph
	public int sizeOfGraph() {
		return vertices.size();
	}
	
	// remove a Vertex from the vertices
	public void removeVertex(int id) {
		vertices.remove(id);
	}
	
	// merge two nodes into one
	public void merge(int vertexId1, int vertexId2) {
		Vertex node1 = vertices.get(vertexId1);
		Vertex node2 = vertices.get(vertexId2);
		
		// merge two nodes into one super node with id=vertexId1
		for(int i = 0; i < node2.edge.size(); i++) {
			int node = node2.edge.get(i);
			node1.addEdge(node);
			
//		 need to replace node2 with node1 in other vertices
			 
			Vertex other = vertices.get(node);
			other.removeEdge(node2.vertexId);
			other.addEdge(node1.vertexId);
		}
		removeVertex(vertexId2);
	}
	
	// delete self-loop 
	public void delete(int vertexId) {
		Vertex node = vertices.get(vertexId);
		
		for(int i = 0; i < node.edge.size(); i++) {
			if (vertexId == node.edge.get(i)) {
				node.edge.remove(i);
				i--;
			}
		}
	}
}


public class KargarMinCut {
	
	// read graph from file
	public static AdjacencyLists readGraph() throws FileNotFoundException, IOException{
		AdjacencyLists graph = new AdjacencyLists();
		
		BufferedReader in = new BufferedReader (new FileReader("/Users/caoxiaolin/eclipse-workspace/KMinCut/src/kargarMinCut.txt"));
		String str;
		Vertex newVertex;
		while ((str = in.readLine()) != null) {
			Scanner sca = new Scanner(str);
			newVertex = new Vertex(sca.nextInt());
			while (sca.hasNextInt()) {
				newVertex.edge.add(sca.nextInt());
			}
			graph.addVertex(newVertex.vertexId, newVertex);
			sca.close();
		}
		in.close();
		
		return graph;
	}
	
	// random contraction algorithm
	public static int randContr (AdjacencyLists graph) {
		int minCut = 0;
		while (graph.sizeOfGraph() > 2) {
			// choose a random edge to contract
			Random rand = new Random();
			
			Object[] array = graph.vertices.keySet().toArray();
			int rand1 = rand.nextInt(array.length);
			int vertex1Id = (Integer) array[rand1];
			
			int rangeOfVertex2Id = graph.vertices.get(vertex1Id).sizeOfVertex();
			int rand2 = rand.nextInt(rangeOfVertex2Id);
			int vertex2Id = graph.vertices.get(vertex1Id).edge.get(rand2);
			
			graph.removeEdge(vertex1Id, vertex2Id);
			graph.merge(vertex1Id, vertex2Id);
			graph.delete(vertex1Id);
			
			minCut = graph.vertices.get(vertex1Id).sizeOfVertex();
		}
		return minCut;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		int min = 300;
		int loop = 200;
		int temp;
		AdjacencyLists graph;
		
		for (int i = 1; i <= loop; i++) {
			graph = readGraph();
			temp = randContr(graph);
			if (temp < min) {
				min = temp;
			}
		}
		
		System.out.println("The minimum cut is: " + min);
	}

}
