import java.io.*;
import java.util.*;


class Jobs implements Comparable<Jobs> {
	int weight;
	int length;
	
	public Jobs(int w, int l) {
		this.weight = w;
		this.length = l;
	}
	
	public double ratio() {
		return (double)weight / (double)length;
	}
	
	public int compareTo(Jobs other) {
		final int BEFORE = 1;
		final int EQUAL = 0;
		final int AFTER = -1;
		
		if (this.ratio() == other.ratio()) {
			return(this.weight > other.weight?(BEFORE) : (AFTER));
		}
		return (this.ratio() > other.ratio()? (BEFORE) : (AFTER));
	}
 }
public class GreedyScheduleRatio {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
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
