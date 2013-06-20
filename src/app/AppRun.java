package app;

import util.FileReader;
import util.FileWriter;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AppRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileReader fileReader = new FileReader("data/100000.txt");
		FileWriter fileWriter = new FileWriter("data/tweet.txt");
		String content = null;
		while((content = fileReader.read())!=null){
			fileWriter.writeline(content);
			content = fileReader.read();
		}
		fileReader.close();
		fileReader = new FileReader("data/final_negative.txt");
		while((content = fileReader.read())!=null){
			content = fileReader.read();
			fileWriter.writeline(content);
			
		}
		fileReader.close();
		fileWriter.close();
		
	}

}
