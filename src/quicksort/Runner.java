package quicksort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
	static ArrayList<String> teste;

	public static void main(String[] args) {
		
		String algoritmo = args[0];
		String caminhoEntrada = args.length > 1 ? args[1] : "";
		String caminhoSaida = args.length > 2 ? args[2] : "output.txt";
		
		String[] dadosEntrada =  getEntrada(caminhoEntrada);
		
		switch(algoritmo){
		case "seq":
			QuickSortSequencial.sort(dadosEntrada);
			escreveSaida(dadosEntrada, caminhoSaida);
			break;
		case "par":
			QuickSortParalelo.sort(dadosEntrada);
			escreveSaida(dadosEntrada, caminhoSaida);
			break;
		}
		System.exit(0);

	}
	
	private static String[] getEntrada(String caminho) {
		Scanner leitor = null;
		File entrada = new File(caminho);
		ArrayList<String> palavras = new ArrayList<>();
		
		if(!entrada.exists()){
			System.out.println("Arquivo de entrada nao encontrado. Gerando strings");
			palavras.add("Projeto");
			palavras.add("de");
			palavras.add("Metodologia");
		} else {
			try {
				leitor = new Scanner(new BufferedReader(new FileReader(entrada)));
				
				while(leitor.hasNextLine()) {
					palavras.add(leitor.nextLine());
				}			
				
			} catch (Exception e){
				e.printStackTrace();
				
			} finally {
				if(leitor != null)
					leitor.close();
			}
		}
		
		return palavras.toArray(new String[]{});		
	}
	
	
	private static void escreveSaida(String[] dados, String caminho) {
		PrintWriter writer = null;
		File saida = new File(caminho);
				
		try {
			writer = new PrintWriter(new FileWriter(saida));
			for(String palavra: dados){
				writer.println(palavra);
			}
			writer.flush();
		
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(writer != null) 
				writer.close();
		}
	}
}
