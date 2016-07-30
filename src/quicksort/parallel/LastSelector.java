package quicksort.parallel;

public class LastSelector implements IPivotIndex {

	/**
	 * Select rightmost index of ar[left,right] as the pivot index.
	 */
	@SuppressWarnings("unchecked")
	public int selectPivotIndex(Comparable[] ar, int left, int right) {
		return right;
	}

}
