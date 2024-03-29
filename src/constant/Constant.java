package constant;

public class Constant {
	
	public static String POSITIVE_PATH = "data/final_positive.txt";
	public static String NEGETIVE_PATH = "data/final_negative.txt";
	public static String FEATURE_PATH = "data/tf.arff";
	
	public static String WORD = "[A-Za-z0-9]+";
	public static String AT_USER = "\\s?@\\s?[^@\\s]+";
	public static String TAG = "#[^#]+#";
	public static String URL = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	public static String EMOTICONS = "(:|\\)|\\(|=|D)";
	
	public static int MIN_WORD_LENGTH = 1;
	public static int MIN_WORD_FREQUENCY = 1;
	public static int DATA_SIZE = 500;
	public static String TEST = "我/rr 转发/v 了/ule 图钉/n 上/f 的/ude1 一/m 张/q 照片/n ///session       （/wkz 来自/v  ";
}
