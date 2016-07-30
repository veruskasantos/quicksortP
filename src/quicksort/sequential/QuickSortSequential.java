package quicksort.sequential;

import java.util.Arrays;
import java.util.Random;

public class QuickSortSequential {

	public static final Random RANDOM = new Random();

	public <E extends Comparable<? super E>> void sort(E[] array) {
		qsort(array, 0, array.length - 1);
	}
	
	private <E extends Comparable<? super E>> void qsort(E[] array, int begin, int end) {
		if (end > begin) {
			int index = partition(array, begin, end);
			qsort(array, begin, index - 1);
			qsort(array, index + 1, end);
		}
	}

	private static void swap(Object[] array, int i, int j) {
		Object tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private <E extends Comparable<? super E>> int partition(E[] array, int begin, int end) {
		int index = begin + RANDOM.nextInt(end - begin + 1);
		E pivot = array[index];
		swap(array, index, end);
		for (int i = index = begin; i < end; ++i) {
			if (array[i].compareTo(pivot) <= 0) {
				swap(array, index++, i);
			}
		}
		swap(array, index, end);
		return (index);
	}
	
	public String printOrderlyArray(Object[] array){
		return Arrays.toString(array);
	}
}