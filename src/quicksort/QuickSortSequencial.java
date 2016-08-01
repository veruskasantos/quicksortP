package quicksort;

import java.util.Random;

public class QuickSortSequencial {

	public static final Random RANDOM = new Random();

	public static void sort(String[] array) {
		quicksort(array, 0, array.length - 1);
	}

	private static void quicksort(String[] array, int start, int end) {
		if (end > start) {
			int index = partition(array, start, end);
			quicksort(array, start, index - 1);
			quicksort(array, index + 1, end);
		}
	}

	private static void swap(String[] array, int i, int j) {
		String tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private static int partition(String[] array,
			int start, int end) {
		int index = start + RANDOM.nextInt(end - start + 1);
		String pivot = array[index];

		swap(array, index, end);
		for (int i = index = start; i < end; ++i) {
			if (array[i].compareToIgnoreCase(pivot) <= 0) {
				swap(array, index++, i);
			}
		}

		swap(array, index, end);
		return (index);
	}
}