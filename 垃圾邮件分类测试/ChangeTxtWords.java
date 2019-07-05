package mysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ChangeTxtWords {

	public static void main(String[] args) {
//		HashMap<String,Double> hm= new HashMap();
//		String filePath = "D:\\¸÷ÀàÀ¬»ø´Ê¿â\\Ì°¸¯´Ê¿â.txt";
//        String s;
//		String line = System.getProperty("line.separator");
//		StringBuffer str = new StringBuffer();
//		FileWriter fw;
//		try {
//			BufferedReader in=
//	                new BufferedReader(
//	                        new FileReader(filePath) );
//	        while((s = in.readLine()) != null)
//	        {
//	                hm.put(s,0.0);
//	        }
//	        in.close();
//			File file = new File(filePath);
//			if (file.exists()) {
//				file.delete();
//			}
//			fw = new FileWriter(filePath, true);
//			for (java.util.Map.Entry<String, Double> entry : hm.entrySet()) {
//				str.append(entry.getKey() + " : " + entry.getValue()).append(line);
//			}
//			fw.write(str.toString());
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//°×°å´Ê¿â³õÊ¼»¯
		Dictionary dic = new Dictionary("D:\\°Ù¶È·Ö´Ê´Ê¿â.txt");
		String line = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		try {
			File file = new File("D:\\°Ù¶È·Ö´Ê´Ê¿â.txt");
			if (file.exists()) {
				file.delete();
			}
			FileWriter fw;
			fw = new FileWriter("D:\\°Ù¶È·Ö´Ê´Ê¿â.txt", true);
			for (java.util.Map.Entry<String, Double> entry : dic.hm.entrySet()) {
				str.append(entry.getKey() + " : " + entry.getValue()).append(line);
			}
			fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
