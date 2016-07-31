package quicksort;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class QuickSortSequencial {

	public static final Random RANDOM = new Random();

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

	public static <T extends Comparable<T>> void sort(T[] array) {
		quicksort(array, 0, array.length - 1);
		// TODO errado com strings misturadas(maiusculas e minusculas)
	}

	private static <T extends Comparable<T>> void quicksort(T[] array,
			int start, int end) {
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

	private static <T extends Comparable<T>> int partition(T[] array,
			int start, int end) {
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