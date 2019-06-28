package mysystem;
import java.util.*;
import java.io.*;
public class email {
	  Dictionary dic;
	  HashMap<String,Double> wordTable;//ͳ�Ƶ����ʼ��Ĵ���Ƶ��
	  String title;//�ʼ�����
	  String sender;//�ʼ�������
	  String date;//�ʼ�����ʱ��
	  String receiever;//�ʼ��ռ���  
	  String content;//�ʼ�����
	  public email(String fileName,Dictionary dictionary) {
		  wordTable=new HashMap<String,Double>();
		  dic = dictionary;
		  wordTable.putAll(dictionary.hm);//�����еķִʽ�����Ƶ�wordTable
          try
          {
                  BufferedReader in=
                          new BufferedReader(
                                  new FileReader(fileName) );
                  int i=0;//��¼��ȡ��������
                  String s;
                  while((s = in.readLine()) != null)
                  {
                	  if(i==0)
                		  title=s;
                	  else if (i==1) 
                		  sender=s;
                	  else if(i==2)
                		  date=s;
                	  else if(i==3)
                		  receiever=s;
                	  else 
                	  {
                		  content+=s;
                		  wordSegment(s);
                	  }
                	  i++;
                  }
                  in.close();
          }
          catch(IOException e)
          {
                  System.out.println("Error: " + e);
          }
	  }
	  public int wordSegment(String Sentence) { //���ķִ�--��Ҷ˹�㷨
	    int senLen = Sentence.length();
	    int i = 0, j = 0;
	    int M = 12;
	    String word;
	    int flag = 0;
	    while (i < senLen) {
	        int N = i + M < senLen ? i + M : senLen + 1;
	        flag = 0;
	        for (j = N - 1; j > i; j--) 
	        {
	          word = Sentence.substring(i, j);
	              if (j > i + 1&&wordTable.containsKey(word)) 
	              {
	                wordTable.put(word,wordTable.get(word)+1);
	               //ͳ��ÿ���������ı��г��ֵĴ��� 
	                flag = 1;
	                i = j;
	                break;
	              }
	        }
	        if (flag == 0) {
	          i = j + 1;
	        }
	    }
	    return 1;
	  }
	  public email(ArrayList<String> filearr,ArrayList<Double> weights,String fileName,Dictionary dictionary) {//ͨ��dic�ִʴ洢��filearr��weights
		  dic = dictionary;
          try
          {
                  BufferedReader in=
                          new BufferedReader(
                                  new FileReader(fileName) );
                  int i=0;//��¼��ȡ��������
                  String s;
                  while((s = in.readLine()) != null)
                  {
                	  if(i==0)
                		  title=s;
                	  else if (i==1) 
                		  sender=s;
                	  else if(i==2)
                		  date=s;
                	  else if(i==3)
                		  receiever=s;
                	  else 
                	  {
                		  content+=s;
                		  wordSegment2(s,filearr,weights);
                	  }
                	  i++;
                  }
                  in.close();
          }
          catch(IOException e)
          {
                  System.out.println("Error: " + e);
          }
	  }
	  public void wordSegment2(String Sentence,ArrayList<String> filearr,ArrayList<Double> weights) { //���ķִ�--�߼��ع��㷨
		    int senLen = Sentence.length();
		    int i = 0, j = 0;
		    int M = 12;
		    String word;
		    int flag = 0;
		    while (i < senLen) {
		        int N = i + M < senLen ? i + M : senLen + 1;
		        flag = 0;
		        for (j = N - 1; j > i; j--) 
		        {
		          word = Sentence.substring(i, j);
		              if (j > i + 1&&dic.hm.containsKey(word)) 
		              {
		            	  filearr.add(word);
		            	  weights.add(dic.hm.get(word));
		                flag = 1;
		                i = j;
		                break;
		              }
		        }
		        if (flag == 0) {
		          i = j + 1;
		        }
		    }
	}
}
