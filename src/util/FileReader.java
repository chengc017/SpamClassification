package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
	File datafile = null;
	FileInputStream is = null;
	InputStreamReader ir = null;
	BufferedReader in = null;
	
	public FileReader(String name){
		try {
			this.datafile = new File(name);
			this.is = new FileInputStream(datafile);
			this.ir = new InputStreamReader(is);
			this.in = new BufferedReader(ir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String read(){
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void close(){
		try {
			this.in.close();
			this.ir.close();
			this.is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
