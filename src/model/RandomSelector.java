package model;

public class RandomSelector implements IPivotIndex {

	/**
	 * Select random index from the ar[left,right] as the pivot element.
	 */
	@SuppressWarnings("unchecked")
	public int selectPivotIndex(Comparable[] ar, int left, int right) {
		return (int) left + (int) ((right - left + 1) * Math.random());
	}

}
