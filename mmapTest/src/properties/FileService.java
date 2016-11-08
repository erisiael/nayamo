package properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class FileService {

	private String target; 
	private String filename;
	private String extension;
	private final String path = "D:/mmapData";
	
	/**
	 * Constructor for save
	 * @param String to save
	 * @param filename
	 */
	public FileService(String target, String filename, String extension) {
		this.target = target;
		this.filename = filename;
		this.extension = extension;
	}
	
	/**
	 * Constructor for load
	 * @param filename what want to load
	 */
	public FileService(String filename, String extension) {
		this.filename = filename;
		this.extension = extension;
	}
	
	
	public boolean saveFile() {
		boolean result = false;
		
		File dir = new File(path + "/" + filename);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File copyto = new File(dir.getAbsolutePath() + "/" + filename + extension);
		StringReader sr = null;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(copyto, "UTF-8");
			pw.print(target);
			pw.flush();
			result = true;
			System.out.println(result);
			System.out.println("파일시스템 target : " + target);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sr != null) { sr.close(); }
			if (pw != null) { pw.close(); }
		}
		return result;
	}
	
	public String loadFile() {
		String result = null;
		
		File dir = new File(path + "/" + filename);
		File copyfrom = new File(dir.getAbsolutePath() + "/" + filename + extension);
		StringWriter sw = null;
		FileReader fr = null;
		
		try {
			sw = new StringWriter();
			fr = new FileReader(copyfrom);
			
			
			int data = 0;
			while (data != -1) {
				data = fr.read();
				if (data != -1) {
					sw.write(data);
				}
			}
			result = sw.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sw != null) { sw.close(); }
				if (fr != null) { fr.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
