package quicksort;

import java.util.Random;

public class QuickSortSequencial {

	public static final Random RANDOM = new Random();

	public static <T extends Comparable<T>> void sort(T[] array) {
		quicksort(array, 0, array.length - 1);
	}
	
	private static <T extends Comparable<T>> void quicksort(T[] array, int start, int end) {
		if (end > start) {
			int index = partition(array, start, end);
			quicksort(array, start, index - 1);
			quicksort(array, index + 1, end);
		}
	}

	private static <T extends Comparable<T>> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
		int index = start + RANDOM.nextInt(end - start + 1);
		T pivot = array[index];
		
		swap(array, index, end);
		for (int i = index = start; i < end; ++i) {
			if (array[i].compareTo(pivot) <= 0) {
				swap(array, index++, i);
			}
		}
		
		swap(array, index, end);
		return (index);
	}
}