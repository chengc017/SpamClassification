package data;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import constant.Constant;

import util.FileReader;
import util.FileWriter;
import util.TweetPreprocess;
import feature.WordBag;

public class ArffConstructer {

	/**
	 * @param args
	 */
	
	public static void constructWordBagModel(){
		//Generate Word Bag
		WordBag wordBag = new WordBag();
		FileReader fileReader = new FileReader(Constant.POSITIVE_PATH);
		String content = null;
		int count = 0;
		while((content = fileReader.read())!=null && count++<Constant.DATA_SIZE){
			String textString = content.trim();
			String splitString = fileReader.read().trim();
			wordBag.addTweet(splitString, true);
		}
		fileReader.close();
		fileReader = new FileReader(Constant.NEGETIVE_PATH);
		content = null;
		count = 0;
		while((content = fileReader.read())!=null && count++<Constant.DATA_SIZE){
			String textString = content.trim();
			String splitString = fileReader.read().trim();
			wordBag.addTweet(splitString, true);
		}
		fileReader.close();
		//filter low frequency word
		wordBag.filtBag();
		//Generate Name
		FileWriter fileWriter = new FileWriter(Constant.FEATURE_PATH);
		//name
		fileWriter.write("@relation termfrequency-document\r\n\r\n");
		//attribute
		//fileWriter.write("@attribute ID string\r\n");
		for(int i=0;i<wordBag.filteredMap.size();i++) { 
			String word = wordBag.filteredMap.get(i).getKey();
			//fileWriter.write("@attribute "+word+" numeric\r\n");
			fileWriter.write("@attribute "+"word"+i+" numeric\r\n");
		}
		//weibo feature
		fileWriter.write("@attribute " + "TAG" + " numeric\r\n");
		fileWriter.write("@attribute " + "ATUSER" + " numeric\r\n");
		fileWriter.write("@attribute " + "URL" + " numeric\r\n");
		fileWriter.write("@attribute class {1, -1}");
		//data
		fileWriter.write("\r\n@data\r\n");
		fileReader = new FileReader(Constant.POSITIVE_PATH);
		content = null;
		count = 0;
		while((content = fileReader.read())!=null && count++<Constant.DATA_SIZE){
			String textString = content.trim();
			String splitString = fileReader.read().trim();
			fileWriter.writeline(wordBag.tweet2Vector(splitString, true)+","+TweetPreprocess.weiboFeature(textString)+","+"1");
		}
		fileReader.close();
		fileReader = new FileReader(Constant.NEGETIVE_PATH);
		content = null;
		count = 0;
		while((content = fileReader.read())!=null && count++<Constant.DATA_SIZE){
			String textString = content.trim();
			String splitString = fileReader.read().trim();
			fileWriter.writeline(wordBag.tweet2Vector(splitString, true)+","+TweetPreprocess.weiboFeature(textString)+","+"-1");
		}
		fileReader.close();
		fileWriter.close();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		constructWordBagModel();
	}

}
