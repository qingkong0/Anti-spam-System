package mysystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLogistic2 {

	public static double sigmoid(double sum) {
		double sigmoid = 1.0 / (1.0 + Math.exp(-sum));
		return sigmoid;
	}
	
	public static boolean judge(String filepack, Dictionary dic) {//ͨ��ѵ�����dic�ж�filepack������ʼ�����
		
		ArrayList<String> filearr = new ArrayList<String>();
		ArrayList<Double> weights = new ArrayList<Double>();
		double probability;
		FilenameFilter select1=new FileListFilter("txt");//��"*.txt"
		File[] contents1;
		File myDir=new File(filepack);
		contents1=myDir.listFiles(select1);
		if(contents1!=null){
			for(File file:contents1){//�������ȡ���ļ������*.txt�ļ�
			    String s=file.getPath();
			    try
                {
			    	//System.out.println(file.getName());
                        @SuppressWarnings("resource")
						BufferedReader in=
                                new BufferedReader(
                                        new FileReader(file.getPath()) );
                        String line;
                        while((line = in.readLine()) != null)
                        {
                              //ִ�зִ��㷨�浽filarr��weights
                        	email myEmail = new email(filearr,weights,s,dic);
                        }
                        double temp = 0.0;
        				for (int k = 0; k < weights.size(); k++) {
        					temp = temp + weights.get(k);
        				}
        				probability = sigmoid(temp);
        				if(probability>=0.5) {
        					System.out.println(file.getName()+"�����ʼ�");
        					//return true;
        				}else if(probability<0.5){
        					System.out.println(file.getName()+"�����ʼ�");
        					//return false;
        				}
        				
                }
                catch(IOException e)
                {
                        System.out.println("Error: " + e);
                }
			}
		}
		System.out.println("���ļ�");
		return false;
	}
	public static HashMap train(String filepack, Dictionary dic, int times,double lable) {//ͨ���߼��ع��㷨+���ݼ����мලѵ��
		
		ArrayList<String> filearr = new ArrayList<String>();
		ArrayList<Double> weights = new ArrayList<Double>();
		double probability,error;
		FilenameFilter select1=new FileListFilter("txt");//��"*.txt"
		File[] contents1;
		File myDir=new File(filepack);
		contents1=myDir.listFiles(select1);
		for(int j=1;j<times;j++) {
		if(contents1!=null){
			for(File file:contents1){//�������ȡ���ļ������*.txt�ļ�
			    String s=file.getPath();
			    try
                {
			    	//System.out.println(file.getName());
                        @SuppressWarnings("resource")
						BufferedReader in=
                                new BufferedReader(
                                        new FileReader(file.getPath()) );
                        if(file.getName().substring(0, 2).matches("����")) {
                        	lable=0.0;
                        }
                        if(file.getName().substring(0, 2).matches("����")) {
                        	lable=1.0;
                        }
                        String line;
                        while((line = in.readLine()) != null)
                        {
                              //ִ�зִ��㷨�浽filarr��weights
                        	email myEmail = new email(filearr,weights,s,dic);
                        }
                        double temp = 0.0;
        				for (int k = 0; k < weights.size(); k++) {
        					temp = temp + weights.get(k);
        				}
        				probability = sigmoid(temp);
        				error = lable - probability;//����ϵ�����������ʼ��ͺϷ��ʼ���
        				double tempweight = 0.0;//��Ȩֵ
        				for (int p = 0; p < weights.size(); p++) {
        					tempweight = ((double)1/j)*error;//alpha * error;
        					weights.set(p, weights.get(p) + tempweight);
        				}
        				//������Ӧ��dic
        				for(int i=0;i<filearr.size();i++) {
        					if (dic.hm.containsKey(filearr.get(i))) 
        		              {
        						dic.hm.put(filearr.get(i),weights.get(i));
        		              }
        				}
        				
                }
                catch(IOException e)
                {
                        System.out.println("Error: " + e);
                }
			}
			//dic���Ϊ�ļ�
		}
		}
				/*alpha = 4 / (1.0 + i + j) + 0.0001;
				randIndex = (int) (Math.random() * dataIndex.size());
				dataIndex.remove(randIndex);
				tempweight = alpha * Double.parseDouble(dataSet.data.get(randIndex).get(p)) * error;*/
		return dic.hm;
	}
	
	public static String classifyVector(ArrayList<String> inX, ArrayList<Double> weights) {//sigmod�жϺ���
		double sum=0.0;
		for (int i = 0; i < inX.size(); i++) {
			sum = sum + weights.get(i);
			//sum.set(0, sum.get(0) + Double.parseDouble(inX.get(i)) * weights.get(i));
		}
		if (sigmoid(sum) > 0.5)
			return "1";
		else
			return "0";
	}
}
