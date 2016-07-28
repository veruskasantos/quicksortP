package main;

import model.QuickSort;
import model.RandomSelector;

public class TestMain {

	private static Integer[] ar = {1,2,3,4,5,6,7};
	private static Integer[] ar1 = {7,6,5,4,3,2,1,0};
	private static Integer[] ar2 = {0,-8,-7,-3,-5,-4,-6,-2,-1,-9};
	private static Integer[] ar3 = {1,2,3,7,6,5,4};
	private static Integer[] ar4 = {5,2,7,6,5,6,7};
	private static Integer[] ar5 = {1,-2,3,-4,5,6,7};
	private static Integer[] ar6 = {-1,-2,-3,-4,-5,-6,-7};
	private static Integer[] ar7 = new Integer[100];
	
	private static RandomSelector random = new RandomSelector();
	public static void main(String[] args){ 
		//preencheArray(ar7);
		QuickSort qs = new QuickSort(ar4);
		qs.setPivotMethod(random);
		//System.out.println("antes: ");
		//printArray(ar7);
		qs.qsort(0, ar4.length-1);
		printArray(ar4);
	}
	
	private static void printArray(Integer[] ar){
		String array = "";
		for(int i : ar){
			array += i + ",";
		}
		System.out.println(array);
	}
	
	private static void preencheArray(Integer[] ar) {
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				ar[i] = i;
			} else {
				ar[i] = -i;
			}
		}
	}
	
}
