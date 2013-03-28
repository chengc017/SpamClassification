package util;

import java.awt.Frame;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constant.Constant;

/**
 * @author wanghaoyu
 *
 */
public class TweetPreprocess {
	
	/**
	 * 
	 * eliminate #,@,URL
	 * 
	 * @param tweet
	 * @param type
	 * @return	
	 */
	public static String preprocess(String tweet, int type){
		String result = tweet;
		//remove tag
		result = purgeContnet(result, Constant.TAG);
		result = purgeContnet(result, Constant.AT_USER);
		result = purgeContnet(result, Constant.URL);
		
		return result;
	}
	
	/**
	 * 
	 * @param tweet
	 * @param reg
	 * @return number of that feature appear in this tweet
	 */
	public static int numberOfFeature(String tweet, String reg){
		int result = 0;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(tweet);
		while(m.find()){
			result++;
		}
		return result;
	}
	
	/**
	 * purge specific content
	 * @param content
	 * @param reg
	 * @return
	 */
	public static String purgeContnet(String content, String reg){
		String purgedcontent = content;
		//use regular expression to match
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(purgedcontent);
		while(m.find()){
			purgedcontent = m.replaceAll("");
		}
		return purgedcontent;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test API
//		String testInput = "#【淘宝电子书阅读地图】要夺回钓鱼岛，更要拯救中国阅读#45555(来自http://t.cn/zlpJmQm )";
//		System.out.println(testInput);
//		System.out.println(preprocess(testInput, 0));
//		System.out.println("Number of @:" + numberOfFeature(testInput, Constant.AT_USER));
		//Purge data
//		FileReader fileReader = new FileReader("data/positive.txt");
//		FileWriter fileWriter = new FileWriter("result/positive.txt");
//		HashMap<String, Integer> duplicateHashMap = new HashMap<String, Integer>();
//		String tweetString = null;
//		while((tweetString = fileReader.read())!=null){
//			System.out.println(tweetString);
//			String preprocessed = preprocess(tweetString, 0);
//			if(!duplicateHashMap.containsKey(preprocessed)){
//				duplicateHashMap.put(preprocessed, 1);
//				fileWriter.writeline(tweetString);
//			}
//			tweetString = fileReader.read();
//		}
//		
//		fileReader.close();
//		fileWriter.close();
		
		FileReader fileReader = new FileReader("result/positive.txt");
		FileWriter fileWriter = new FileWriter("re/sult/positive1.txt");
		HashMap<String, Integer> duplicateHashMap = new HashMap<String, Integer>();
		String tweetString = null;
		while((tweetString = fileReader.read())!=null){
			String preprocessed = preprocess(tweetString, 0);
			fileWriter.writeline(tweetString);
			fileWriter.writeline(preprocessed);
			tweetString = fileReader.read();
		}
		
		fileReader.close();
		fileWriter.close();
		
	}

}
