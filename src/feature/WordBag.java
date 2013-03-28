package feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import constant.Constant;

import util.FileReader;

public class WordBag {

	/**
	 * word,frequency
	 */
	public HashMap<String, Integer> wordMap = null;
	public ArrayList<Entry<String, Integer>> filteredMap = null;
	
	public WordBag(){
		 this.wordMap = new HashMap<String, Integer>();
	}
	
	/**
	 * add a tweet to the total data set and refresh the word bag
	 * @param text(?/? ?/? ?/?)
	 * @return
	 */
	public int addTweet(String text, boolean tag){
		String split[] = text.split(" ");
		
		for(String word : split){
			if(word.length()<Constant.MIN_WORD_LENGTH){
				continue;
			}
			if(tag){
				if(word.contains("/")){
					word = word.substring(0, word.lastIndexOf("/"));
				}
			}
			if(this.wordMap.containsKey(word)){
				int frequency = this.wordMap.get(word);
				this.wordMap.remove(word);
				this.wordMap.put(word, frequency+1);
			}
			else{
				this.wordMap.put(word, 1);
			}
		}
		return wordMap.size();
	}
	
	/**
	 * filter out low frequency word
	 * @return
	 */
	public ArrayList<Entry<String, Integer>> filtBag(){
		this.filteredMap = new ArrayList<Entry<String, Integer>>(this.wordMap.entrySet());

		Collections.sort(this.filteredMap, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		
		//filter low frequency word
		for(int i=0;i<this.filteredMap.size();i++){
			if(this.filteredMap.get(i).getValue()<Constant.MIN_WORD_FREQUENCY){
				this.filteredMap.remove(i);
			}
		}
		
		return this.filteredMap;
	}
	
	public String tweet2Vector(String text, boolean tag){
		String resultString = "";
		if(this.filteredMap == null){
			this.filteredMap = this.filtBag();
		}
		String split[] = text.split(" ");
		
		for(int i=0;i<this.filteredMap.size();i++){
			boolean hit = false;
			for(String word : split){
				if(tag){
					if(word.contains("/")){
						word = word.substring(0, word.lastIndexOf("/"));
					}
				}
				if(this.filteredMap.get(i).getKey().equals(word)){
					resultString = resultString+"1"+",";
					hit = true;
					break;
				}
			}
			if(!hit){
				resultString = resultString+"0"+",";
			}	
		}
		resultString = resultString.substring(0, resultString.length()-1);
		return resultString;
	}
	
	public void checkBag(){
		for(Entry<String, Integer> entry : this.wordMap.entrySet()){
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordBag wordBag = new WordBag();
		FileReader fileReader = new FileReader("data/final_positive.txt");
		
		String content = null;
		int count = 0;
		while((content = fileReader.read())!=null && count++<5){
			String textString = content.trim();
			String splitString = fileReader.read().trim();
			//System.out.println(splitString);
			wordBag.addTweet(splitString, true);
		}
		System.out.println(wordBag.wordMap.size());
		//System.out.println(wordBag.filtBag().size());
		
		System.out.println(wordBag.tweet2Vector(Constant.TEST, true));
		
		//wordBag.checkBag();
		fileReader.close();
	}

}
