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
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(purgedcontent);
		while(m.find()){
			purgedcontent = m.replaceAll("");
		}
		return purgedcontent;
	}
	
	public static boolean hasContent(String content, String reg){
		String purgedcontent = content;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(purgedcontent);
		while(m.find()){
			return true;
		}
		return false;
	}
	
	/**
	 * 1.tag
	 * 2.at
	 * 3.url
	 */
	public static String weiboFeature(String content){
		String result = "";
		if(hasContent(content, Constant.TAG)){
			result = result + "1,";
		}
		else{
			result = result + "0,";
		}
		if(hasContent(content, Constant.AT_USER)){
			result = result + "1,";
		}
		else{
			result = result + "0,";
		}
		if(hasContent(content, Constant.URL)){
			result = result + "1";
		}
		else{
			result = result + "0";
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test API
		String testInput = "#【淘宝电子书阅读地图】要夺回钓鱼岛，更要拯救中国阅读#45555(来自http://t.cn/zlpJmQm )";
		System.out.println(weiboFeature(testInput));
		
	}

}
