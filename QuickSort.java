// Week 3 programming assignment 
// first: using the first element to be the pivot
// second: using the last element to be the pivot
// third: using the median of three to be the pivot
import java.io.*;
import java.util.*;


public class QuickSort {

	public static void main(String[] args) 
			throws FileNotFoundException {
		int [] input = new int [10000];
		Scanner inputTxt = new Scanner (new File("QuickSort.txt"));
		for (int i = 0; i < input.length; i++) {
			input[i] = inputTxt.nextInt();
		}
//			int[] input = {4, 1, 3, 6, 2, 5};
			int count = quicksort(input, 0, input.length-1);
			System.out.println(count);
	}
	
	public static int quicksort(int[] array, int lo, int hi) {
		if (hi <= lo) {
			return 0;
		}
		int pivot = partition(array, lo, hi);
		int tmp1 = quicksort(array, lo, pivot-1);
		int tmp2 = quicksort(array, pivot+1, hi);
		return (tmp1+tmp2+(hi-lo));
	}
	
	
	
	public static int partition(int[] input, int left, int right) {
		int index = medianOfThree(input, left, right, (left+right)/2);
		int pivot = input[index];
		input[index] = input[left];
		input[left] = pivot;
//		swap(input[left], input[left]);
		int i = left + 1;
		for (int j = left + 1; j <= right; j++) {
			if (input[j] < pivot) {
//				swap(input[i], input[j]);
				int temp = input[i];
				input[i] = input[j];
				input[j] = temp;
				i++;
			}
		}
//		swap(input[left], input[i-1]);
		int temp2 = input[left];
		input[left] = input[i-1];
		input[i-1] = temp2;
//		System.out.println(Arrays.toString(input));
		return i-1;
	}
	
//	public static void swap(int a, int b) {
//		int temp = a;
//		a = b;
//		b = temp;
//	}
	
	public static int medianOfThree(int[] array, int low, int high, int medium) {
		if (array[low] > Math.max(array[medium], array[high])) {
			if (array[medium] > array[high]) {
				return medium;
			} else {
				return high;
			}
		} else if (array[low] < Math.min(array[medium], array[high])) {
			if (array[medium] > array[high]) {
				return high;
			} else {
				return medium;
			}
		}
		return low;
	}
	

}
