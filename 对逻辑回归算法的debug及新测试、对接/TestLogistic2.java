package mysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLogistic2 {
	static int i = 0;
	static int j = 0;
	Dictionary dic;

	public static double sigmoid(double sum) {
		double sigmoid = 1.0 / (1.0 + Math.exp(-sum));
		return sigmoid;
	}
	
	public static void updateTxt(Dictionary dic,String filepath) {
		// 将训练的得到的词典存入txt
				String line = System.getProperty("line.separator");
				StringBuffer str = new StringBuffer();
				FileWriter fw;
				try {
					File file = new File(filepath);
					if (file.exists()) {
						file.delete();
					}
					fw = new FileWriter(filepath, true);
					for (java.util.Map.Entry<String, Double> entry : dic.hm.entrySet()) {
						str.append(entry.getKey() + " : " + entry.getValue()).append(line);
					}
					fw.write(str.toString());
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	public static boolean judge(String filepack, Dictionary dic) {// 通过训练后的dic判断filepack里面的邮件类型

		ArrayList<String> filearr = new ArrayList<String>();
		ArrayList<Double> weights = new ArrayList<Double>();
		double probability;
		FilenameFilter select1 = new FileListFilter("txt");// 仅"*.txt"
		File[] contents1;
		File myDir = new File(filepack);
		contents1 = myDir.listFiles(select1);
		if (contents1 != null) {
			for (File file : contents1) {// 这里可以取出文件夹里的*.txt文件
				String s = file.getPath();
				try {
					// System.out.println(file.getName());
					@SuppressWarnings("resource")
					BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
					String line;
					while ((line = in.readLine()) != null) {
						// 执行分词算法存到filarr和weights
						email myEmail = new email(filearr, weights, s, dic);
					}
					double temp = 0.0;
					for (int k = 0; k < weights.size(); k++) {
						temp = temp + weights.get(k);
					}
					probability = sigmoid(temp);
					if (probability >= 0.5) {
						i++;
					} else if (probability < 0.5) {
						j++;
					}
					filearr.clear();
					weights.clear();
				} catch (IOException e) {
					System.out.println("Error: " + e);
				}
			}
		}
		return false;
	}

	public void learn(email myEmail, int lable) {
		dic = new Dictionary("D:\\邮件\\词典.txt");// 词典txt地址
		TestLogistic2 logic = new TestLogistic2();
		for (int i = 1; i < 8; i++) {
			logic.train(myEmail.filepath, dic, i, lable);
		}
		updateTxt(dic,"D:\\邮件\\词典.txt");
	}

	public static HashMap train(String filepath, Dictionary dic, int nowtimes, double lable) {// 通过逻辑回归算法+数据集进行监督训练,存入txt里

		ArrayList<String> filearr = new ArrayList<String>();
		ArrayList<Double> weights = new ArrayList<Double>();
		double probability, error;
		try {
			// System.out.println(file.getName());
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String line;
			while ((line = in.readLine()) != null) {
				// 执行分词算法存到filarr和weights
				email myEmail = new email(filearr, weights, filepath, dic);
			}
			double temp = 0.0;
			for (int k = 0; k < weights.size(); k++) {
				temp = temp + weights.get(k);
			}
			probability = sigmoid(temp);
			error = lable - probability;// 错误系数（分垃圾邮件和合法邮件）
			double tempweight = 0.0;// 加权值
			for (int p = 0; p < weights.size(); p++) {
				tempweight = (double) (20.0 - 0.5 * (double) nowtimes) * error;// alpha * error;
				weights.set(p, weights.get(p) + tempweight);
			}
			// 更改相应的dic
			for (int i = 0; i < filearr.size(); i++) {
				if (dic.hm.containsKey(filearr.get(i))) {
					dic.hm.put(filearr.get(i), weights.get(i));
				}
			}
			updateTxt(dic,"D:\\邮件\\词典.txt");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return dic.hm;
	}

	public static String classifyVector(ArrayList<String> inX, ArrayList<Double> weights) {// sigmod判断函数
		double sum = 0.0;
		for (int i = 0; i < inX.size(); i++) {
			sum = sum + weights.get(i);
			// sum.set(0, sum.get(0) + Double.parseDouble(inX.get(i)) * weights.get(i));
		}
		if (sigmoid(sum) > 0.5)
			return "1";
		else
			return "0";
	}
}
