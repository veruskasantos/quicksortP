package quicksort;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class QuickSortParalelo {
    
	private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int FALLBACK = 2;
    private static Executor pool = Executors.newFixedThreadPool(NUM_THREADS);

    public static void main(String[] args){
		Entrada input = null;
		Saida output = null;
		String[] frases = null;
		int tamanhoParamentros = args.length;

		if (tamanhoParamentros == 1) {
			output = new Saida(args[0]);
		} else if (tamanhoParamentros == 2) {
			input = new Entrada(args[0]);
			output = new Saida(args[1]);
		} else if (tamanhoParamentros > 2) {
			System.out.println("Parametros invalidos");
			System.exit(1);
		}
		if (input == null) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Entrada: ");
			input = new Entrada(scan.nextLine());
			scan.close();
		}

		try {
			frases = input.getLinhas();
		} catch (IOException e) {
			System.out.println("Arquivo de entrada nao existe ou esta aberto.");
			System.exit(1);
		}

		for (int i = 0; i < frases.length; i++) {
			String linha = frases[i];
			String[] palavras = linha.split(" ");
			sort(palavras);
			linha = "";
			for (String palavra : palavras) {
				linha += palavra + " ";
			}
			frases[i] = linha.trim();
		}

		sort(frases);

		if (output == null) {
			System.out.println("Saida: ");
			for (String string : frases) {
				System.out.println(string);
			}
		} else {
			try {
				output.salvarLinhas(frases);
			} catch (IOException e) {
				System.out.println("Arquivo de saida nao existe ou esta aberto.");
				System.exit(1);
			}
		}
	}
    
    public static <T extends Comparable<T>> void sort(T[] input) {
        final AtomicInteger count = new AtomicInteger(1);
        pool.execute(new QuicksortRunnable<T>(input, 0, input.length - 1, count));
        
        try {
            synchronized (count) {
                count.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		// TODO errado com strings misturadas(maiusculas e minusculas)
    }
     

    private static class QuicksortRunnable<T extends Comparable<T>> implements Runnable {
        
    	private final T[] values;
        private final int left;
        private final int right;
        private final AtomicInteger numThreads;

        public QuicksortRunnable(T[] values, int left, int right, AtomicInteger count) {
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
                    pool.execute(new QuicksortRunnable<T>(values, pLeft, storeIndex - 1, numThreads));
                    pool.execute(new QuicksortRunnable<T>(values, storeIndex + 1, pRight, numThreads));
                }
            }
        }

        private int partition(int pLeft, int pRight) {
            T pivotValue = values[pRight];
            int storeIndex = pLeft;
            for (int i = pLeft; i < pRight; i++) {
                if (values[i].compareTo(pivotValue) < 0) {
                    swap(i, storeIndex);
                    storeIndex++;
                }
            }
            swap(storeIndex, pRight);
            return storeIndex;
        }

        private void swap(int left, int right) {
            T temp = values[left];
            values[left] = values[right];
            values[right] = temp;
        }
    }
}