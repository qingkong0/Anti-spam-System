package mysystem;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class Test {

	public static void main(String[] args) {
		Dictionary dic = new Dictionary("D:\\�߼��ع�\\b.txt");
		HashMap<String,Double> a = dic.hm;
		TestLogistic2 logic = new TestLogistic2();
		HashMap<String,Double> b = logic.train("D:\\�߼��ع�\\�����ʼ�train", dic, 1, 0);
        
        String line = System.getProperty("line.separator");
    	StringBuffer str = new StringBuffer();
    	FileWriter fw;
    	try {
    		File file = new File("D:\\�߼��ع�\\c.txt");
    		if (true){
    			file.delete();
    		}
    		fw = new FileWriter("D:\\�߼��ع�\\c.txt", true);
        	for(java.util.Map.Entry<String, Double> entry : b.entrySet()) {
        		str.append(entry.getKey()+" : "+entry.getValue()).append(line);
        }
        	fw.write(str.toString());
    		fw.close();
    		

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}

}
