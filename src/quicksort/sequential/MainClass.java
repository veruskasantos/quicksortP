package quicksort.sequential;

public class MainClass {
	
	public static void main(String[] args){
		
		QuickSortSequential qss = new QuickSortSequential();
		Integer[] array = new Integer[10];
		preencheArray(array);
		qss.sort(array);
		System.out.println(qss.printOrderlyArray(array));
	}
	
	private static void preencheArray(Object[] ar) {

		for (int i = 0; i < ar.length; i++) {
			if (i % 2 == 0) {
				ar[i] = -i;
			} else {
				ar[i] = -i;
			}
		}
	}
}
