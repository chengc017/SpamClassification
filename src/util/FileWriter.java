package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {

	File datafile = null;
	FileOutputStream os = null;
	OutputStreamWriter ow = null;
	BufferedWriter out = null;
	
	public FileWriter(String name){
		try {
			this.datafile = new File(name);
			this.os = new FileOutputStream(datafile);
			this.ow = new OutputStreamWriter(os);
			this.out = new BufferedWriter(ow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void write(String s){
		try {
			this.out.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeline(String s){
		try {
			this.out.write(s + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			this.out.close();
			this.ow.close();
			this.os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
