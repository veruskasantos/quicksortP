package quicksort;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class QuickSortParalelo {
    
	private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int FALLBACK = 2;
    private static Executor pool = Executors.newFixedThreadPool(NUM_THREADS);
    
    public static void sort(String[] input) {
        final AtomicInteger count = new AtomicInteger(1);
        pool.execute(new QuicksortRunnable(input, 0, input.length - 1, count));
        
        try {
            synchronized (count) {
                count.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     

    private static class QuicksortRunnable implements Runnable {
        
    	private final String[] values;
        private final int left;
        private final int right;
        private final AtomicInteger numThreads;

        public QuicksortRunnable(String[] values, int left, int right, AtomicInteger count) {
            this.values = values;
            this.left = left;
            this.right = right;
            this.numThreads = count;
        }

        @Override
        public void run() {
            quicksort(left, right);
            synchronized (numThreads) {    
                if (numThreads.getAndDecrement() == 1)
                    numThreads.notify();
            }
        }

        private void quicksort(int pLeft, int pRight) {
            if (pLeft < pRight) {
                int storeIndex = partition(pLeft, pRight);
                if (numThreads.get() >= FALLBACK * NUM_THREADS) {
                    quicksort(pLeft, storeIndex - 1);
                    quicksort(storeIndex + 1, pRight);
                } else {
                    numThreads.getAndAdd(2);
                    pool.execute(new QuicksortRunnable(values, pLeft, storeIndex - 1, numThreads));
                    pool.execute(new QuicksortRunnable(values, storeIndex + 1, pRight, numThreads));
                }
            }
        }

        private int partition(int pLeft, int pRight) {
            String pivotValue = values[pRight];
            int storeIndex = pLeft;
            for (int i = pLeft; i < pRight; i++) {
                if (values[i].compareToIgnoreCase(pivotValue) < 0) {
                    swap(i, storeIndex);
                    storeIndex++;
                }
            }
            swap(storeIndex, pRight);
            return storeIndex;
        }

        private void swap(int left, int right) {
            String temp = values[left];
            values[left] = values[right];
            values[right] = temp;
        }
    }
}