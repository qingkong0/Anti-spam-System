package mysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class TestBlackAndWhiteList {

	Dictionary dic;

	public TestBlackAndWhiteList(String filePath) {// 初始化黑/白名单词典
		dic = new Dictionary(filePath);
	}

	public char judge(email myEmail) {// 判断邮件是否在黑白名单中
		for (String key : myEmail.wordTable.keySet()) {
			while (dic.Find(key)) {
				return 'T';
			}
		}
		return 'F';
	}

	boolean[] select;

	public void initFromFile(String filePath) {// 通过文件初始化设置的选项（黑白名单操作）
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String s;
			int i = 0;
			while ((s = in.readLine()) != null) {
				switch (s) {
				case "0":
					select[i] = false;
					i++;
					break;
				case "1":
					select[i] = true;
					i++;
					break;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void changeFile(String filePath) {//用户设置完点击保存，修改配置文件
		try {
			String line = System.getProperty("line.separator");
			StringBuffer str = new StringBuffer();
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			FileWriter fw;
			fw = new FileWriter(filePath, true);
			for (int i=0;i<select.length;i++) {
				str.append(select[i]?1:0).append(line);
			}
			fw.write(str.toString());
			fw.close();
		} catch (

		IOException e) {
			System.out.println("Error: " + e);
		}
	}

	public void add(String sentence, String type) {// 将用户指定的句子添加到存入黑白名单txt，type指的是黑白名单类型
		String filePath = "D:\\" + type + ".txt";
		String line = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw;
		try {
			fw = new FileWriter(filePath, true);
			str.append(sentence).append(line);
			fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(int index[], String type) {// 对黑白名单执行删除操作，type指的是黑白名单类型
		String filePath = "D:\\" + type + ".txt";
		int i = 0;// 扫描到第几行
		int j = 0;// 选中的第几个
		HashMap<String, Integer> hm = new LinkedHashMap();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String s;
			while ((s = in.readLine()) != null) {
				if (j < index.length && i == index[j]) {
					i++;
					j++;
				} else {
					hm.put(s, i);
					i++;
				}
			}
			in.close();
			// 将删除结果存入txt
			String line = System.getProperty("line.separator");
			StringBuffer str = new StringBuffer();
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			FileWriter fw;
			fw = new FileWriter(filePath, true);
			for (Entry<String, Integer> entry : hm.entrySet()) {
				str.append(entry.getKey()).append(line);
			}
			fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

	public static void main(String[] args) {
		// TestBlackAndWhiteList a =new TestBlackAndWhiteList();
		int index = 2;
		// System.out.println(index);
		// a.delete(index,"黑名单");
	}
}
