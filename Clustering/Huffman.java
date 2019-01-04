import java.io.*;
import java.util.*;

public class Huffman {
	
	static class HuffmanNode implements Comparable<HuffmanNode> {
		int val;
		HuffmanNode left;
		HuffmanNode right;
		
		public HuffmanNode(int n) {
			this.val = n;
			this.left = null;
			this.right = null;
		}
		
		@Override
		public int compareTo(HuffmanNode other) {
			return this.val - other.val;
 		}
	}
	
	public static int getLongestPath(HuffmanNode node) {
		if (node == null) return 0;
		
		return (1 + Math.max(getLongestPath(node.left), getLongestPath(node.right)));
	}
	
	public static int getShortest(HuffmanNode node) {
		if (node == null)  return 0;
		
		return (1 + Math.min(getShortest(node.left), getShortest(node.right)));
	}
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException, NumberFormatException {
		// TODO Auto-generated method stub
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
		int n = 0;
		HuffmanNode root = null;
		int max = 0;
		int min = 0;
		
			FileInputStream f = new FileInputStream("/Users/caoxiaolin/Downloads/huffman.txt");
			DataInputStream d = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(d));
			n = Integer.parseInt(b.readLine());
			
			String str; HuffmanNode hn; Scanner sca;
			while ((str = b.readLine()) != null) {
				sca = new Scanner(str);
				hn = new HuffmanNode(sca.nextInt());
				pq.add(hn);
			}
			b.close();
			
			
			 
			while (pq.size() > 1) {
				HuffmanNode x = pq.peek();
				pq.poll();
				
				HuffmanNode y = pq.peek();
				pq.poll();
				
				HuffmanNode parent = new HuffmanNode(x.val + y.val);
				parent.left = x;
				parent.right = y;
				root = parent;
				
				pq.add(parent);
			}
			
			max = getLongestPath(root);
			System.out.println(max);
			
			min = getShortest(root);
			System.out.println(min);
			
	
	}

}
