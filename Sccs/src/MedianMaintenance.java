import java.util.*;
import java.io.*;

public class MedianMaintenance {
	public static ArrayList<Integer> median = new ArrayList<Integer>();
	public static Queue<Integer> hLow = new PriorityQueue<Integer>(Collections.reverseOrder());
	public static Queue<Integer> hHigh = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		BufferedReader in = new BufferedReader(new FileReader("/Users/caoxiaolin/Downloads/Median.txt"));
		String str, str1, str2;
		Scanner sca, sca1, sca2;
		
		str1 = in.readLine();
		sca1 = new Scanner(str1);
		int first = sca1.nextInt();
		hLow.add(first);
		median.add(hLow.peek());
		
		
		
		str2 = in.readLine();
		sca2 = new Scanner(str2);
		int second = sca2.nextInt();
		
		if (first < second) {
			hHigh.add(second);
			median.add(hLow.peek());
		} else {
			hHigh.add(hLow.remove());
			hLow.add(second);
			median.add(hLow.peek());
		}
		
		int total = 2;
		
		
		sca1.close();
		sca2.close();
		
		while ((str = in.readLine()) != null) {
			sca = new Scanner(str);
			int num = sca.nextInt();
			total++;
			median.add(getMedian(num, total));
		}
		in.close();
		int sum = 0;
		for (int i = 0; i < median.size(); i++) {
			sum += median.get(i);
		}
		
		System.out.println("The size of the arraylist is:" + median.size());
		System.out.println("The last four digits are:" + sum % 10000);
	}
	
	public static int getMedian(int num, int total) {
		
		if (num > hLow.peek() && num < hHigh.peek()) {
			hLow.add(num);
		}
		
		
		if (num < hLow.peek()) {
			hLow.add(num);
		}
		
		if (num > hHigh.peek()) {
			hHigh.add(num);
		}
		
		if (total % 2 == 0 && hLow.size() != hHigh.size()) {
			if (hLow.size() < hHigh.size()) {
				hLow.add(hHigh.remove());
			}
			
			if (hLow.size() > hHigh.size()) {
				hHigh.add(hLow.remove());
			}
		}
		
		if (total % 2 != 0) {
			if (hLow.size() == (total + 1) / 2) return hLow.peek();
			if (hHigh.size() == (total + 1) / 2) return hHigh.peek();
		}
		
		return hLow.peek();
	
	}
}
