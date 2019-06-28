package mysystem;
import java.util.*;
import java.io.*;
public class email {
	  Dictionary dic;
	  HashMap<String,Double> wordTable;//统计单个邮件的词语频率
	  String title;//邮件标题
	  String sender;//邮件发件人
	  String date;//邮件发送时间
	  String receiever;//邮件收件人  
	  String content;//邮件内容
	  public email(String fileName,Dictionary dictionary) {
		  wordTable=new HashMap<String,Double>();
		  dic = dictionary;
		  wordTable.putAll(dictionary.hm);//把所有的分词结果复制到wordTable
          try
          {
                  BufferedReader in=
                          new BufferedReader(
                                  new FileReader(fileName) );
                  int i=0;//记录读取到的行数
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
	  public int wordSegment(String Sentence) { //中文分词--贝叶斯算法
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
	               //统计每个单词在文本中出现的次数 
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
	  public email(ArrayList<String> filearr,ArrayList<Double> weights,String fileName,Dictionary dictionary) {//通过dic分词存储到filearr，weights
		  dic = dictionary;
          try
          {
                  BufferedReader in=
                          new BufferedReader(
                                  new FileReader(fileName) );
                  int i=0;//记录读取到的行数
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
	  public void wordSegment2(String Sentence,ArrayList<String> filearr,ArrayList<Double> weights) { //中文分词--逻辑回归算法
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
