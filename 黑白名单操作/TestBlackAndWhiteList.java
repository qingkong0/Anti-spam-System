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

	public TestBlackAndWhiteList(String filePath) {// ��ʼ����/�������ʵ�
		dic = new Dictionary(filePath);
	}

	public char judge(email myEmail) {// �ж��ʼ��Ƿ��ںڰ�������
		for (String key : myEmail.wordTable.keySet()) {
			while (dic.Find(key)) {
				return 'T';
			}
		}
		return 'F';
	}

	boolean[] select;

	public void initFromFile(String filePath) {// ͨ���ļ���ʼ�����õ�ѡ��ڰ�����������
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

	public void changeFile(String filePath) {//�û������������棬�޸������ļ�
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

	public void add(String sentence, String type) {// ���û�ָ���ľ�����ӵ�����ڰ�����txt��typeָ���Ǻڰ���������
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

	public void delete(int index[], String type) {// �Ժڰ�����ִ��ɾ��������typeָ���Ǻڰ���������
		String filePath = "D:\\" + type + ".txt";
		int i = 0;// ɨ�赽�ڼ���
		int j = 0;// ѡ�еĵڼ���
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
			// ��ɾ���������txt
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
		// a.delete(index,"������");
	}
}
