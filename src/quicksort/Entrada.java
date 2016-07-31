package quicksort;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Entrada {
	private String path = "";
	private final String quebraLinha = System.getProperty("line.separator");
	private StringBuilder texto;
	public Entrada(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		
		this.path = path;
	}

	public String[] getLinhas() throws IOException {
		if(texto != null){
			return texto.toString().split(quebraLinha);
		}
		RandomAccessFile aFile = new RandomAccessFile(getPath(), "rw");
		FileChannel inChannel = aFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(72000);

		int bytesRead = inChannel.read(buf);
		texto = new StringBuilder();
		while (bytesRead != -1) {
			buf.flip();
			
			while (buf.hasRemaining()) {
				char caractere = (char) buf.get();
				texto.append(caractere);
			}
			texto.deleteCharAt(texto.length() - 1);
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
		return texto.toString().split(quebraLinha);
	}

}