package quicksort;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
	static ArrayList<String> teste;

	public static void main(String[] args) {

		teste = new ArrayList<>();
		preencheLista();
		String[] sortIt = teste.toArray(new String[]{});
		QuickSortSequencial.sort(sortIt);
		System.out.println(printResult(sortIt));

		
		teste = new ArrayList<>();
		preencheLista();
		sortIt = teste.toArray(new String[]{});
		QuickSortParalelo.sort(sortIt);
		System.out.println("PARALELO ----" + printResult(sortIt));
				
		System.exit(0);

	}

	private static void preencheLista() {

		teste.add("a");
		teste.add("b");
		teste.add("c");
		teste.add("f");
		teste.add("g");
		teste.add("i");
		teste.add("h");
		teste.add("e");
		teste.add("d");
		teste.add("dd");
		teste.add("");
		teste.add("1");
		teste.add("z");
		teste.add("w");
		teste.add("v");
		teste.add("xxx");
		teste.add("dda");
		teste.add("s");
		teste.add("s");
		teste.add("tr");
		teste.add("tew");
	}
	
	public static String printResult(String[] array){
		return Arrays.toString(array);
	}
}
