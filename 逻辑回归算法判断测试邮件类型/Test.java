package mysystem;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class Test {

	public static void main(String[] args) {
		Dictionary dic = new Dictionary("D:\\逻辑回归\\b.txt");
		HashMap<String,Double> a = dic.hm;
		TestLogistic2 logic = new TestLogistic2();
		HashMap<String,Double> b = logic.train("D:\\逻辑回归\\数据集邮件train", dic, 5,1.0);
		/*File file = new File("D:\\逻辑回归\\数据集邮件train\\正常 (1).txt");
		String s;
		if(file.getName().substring(0, 2).matches("正常")) {
			System.out.println(file.getName());
		}*/
        String line = System.getProperty("line.separator");
    	StringBuffer str = new StringBuffer();
    	FileWriter fw;
    	try {
    		File file = new File("D:\\逻辑回归\\c.txt");
    		if (true){
    			file.delete();
    		}
    		fw = new FileWriter("D:\\逻辑回归\\c.txt", true);
        	for(java.util.Map.Entry<String, Double> entry : b.entrySet()) {
        		str.append(entry.getKey()+" : "+entry.getValue()).append(line);
        }
        	fw.write(str.toString());
    		fw.close();
    		logic.judge("D:\\逻辑回归\\垃圾邮件", dic);
    		logic.judge("D:\\逻辑回归\\合法邮件", dic);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}

}
