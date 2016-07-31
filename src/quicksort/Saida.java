package quicksort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saida {
	private String path = "";
	private final String quebraLinha = System.getProperty("line.separator");
	private File file;
	public Saida(String path) {
		this.path = path;
		file = new File(path);
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void salvarLinhas(String[] linhas) throws IOException{
		if(!file.exists())
			file.createNewFile();
		FileWriter fw = new FileWriter(file);

		BufferedWriter bw = new BufferedWriter( fw );
		
		for (String string : linhas) {
			bw.write(string);
			bw.write(quebraLinha);
		}
		bw.close();
		fw.close();
	}
	

}