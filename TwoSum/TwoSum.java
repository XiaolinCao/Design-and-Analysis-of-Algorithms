import java.io.*;
import java.util.*;
import java.math.*;


public class TwoSum {
	private HashMap<BigInteger, Integer> map = new HashMap<BigInteger, Integer>();
	
	public TwoSum(String testfile) {
		BufferedReader in = null;
		
		try {
			String line;
			in = new BufferedReader(new FileReader(testfile));
			while ((line = in.readLine()) != null) {
				map.put(new BigInteger(line), null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public int findSums(int min, int max) {
		int count = 0;
		for (int i = min; i <= max; i++) {
			Iterator<BigInteger> it = map.keySet().iterator();
			BigInteger ib = new BigInteger("" + i);
			while (it.hasNext()) {
				BigInteger n = it.next();
				if (!n.equals(ib.subtract(n)) && map.containsKey(ib.subtract(n))) {
					count++;
					break;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		String textfile = "/Users/caoxiaolin/Downloads/2sum.txt";
		TwoSum ts = new TwoSum(textfile);
		int sums = ts.findSums(-10000, 10000);
		System.out.println(sums);
	}
}
