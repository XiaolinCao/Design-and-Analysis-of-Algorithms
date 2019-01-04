import java.io.*;
import java.util.*;

class Jobs implements Comparable<Jobs> {
	int weight;
	int length;
	
	public Jobs(int w, int l) {
		this.weight = w;
		this.length = l;
	}
	
	public int compareTo(Jobs other) {
		final int BEFORE = 1;
		final int EQUAL = 0;
		final int AFTER = -1;
		
		if (this.weight - this.length == other.weight - other.length) {
			if (this.weight > other.weight) {
				return BEFORE;
			}
			return AFTER;
//			return(this.weight > other.weight?(BEFORE) : (AFTER));
		}
		return (this.weight - this.length >other.weight - other.length? (BEFORE) : (AFTER));
	}
 }
public class GreedySchedule {
//	public static Integer[] difference;
//	public static Map<Integer, ArrayList<Jobs>> map = new HashMap<Integer, ArrayList<Jobs>>();
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("/Users/caoxiaolin/Downloads/jobs.txt"));
		int total = input.nextInt();
		ArrayList<Jobs> list = new ArrayList<Jobs>();
		while (input.hasNextInt()) {
			int weight = input.nextInt();
			int length = input.nextInt();
			Jobs newJob = new Jobs(weight, length);
			list.add(newJob);
		}
		int completionTime = 0;
		long product = 0;
		Collections.sort(list);
		Collections.reverse(list);
		for (Jobs j: list) {
			completionTime += j.length;
			product += j.weight * completionTime;
		}
		System.out.println(product);
	}

	
	

}
