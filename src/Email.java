import java.util.*;
import java.io.*;
public class Email {
	  Dictionary dic;
	  HashMap<String,Double> wordTable;//统计单个邮件的词语频率
	  String title;//邮件标题
	  String sender;//邮件发件人
	  String date;//邮件发送时间
	  String content;//邮件内容	  
	  public Email(String content,Dictionary dictionary,int n)
	  {
		  this.content=content;
		  wordTable=new HashMap<String,Double>();
		  wordTable.putAll(dictionary.hm);
		  wordSegment(content);
	  }
	  public Email(String fileName)
	  {
		  wordTable=new HashMap<String,Double>();
		  wordTable.putAll(Practice.d.hm);//把所有的分词结果复制到wordTable
		  try
          {
                  BufferedReader in=
                          new BufferedReader(
                                  new FileReader(fileName) );
                  String s;
                  while((s = in.readLine()) != null)
                  {
                		  content+=s;
                		  wordSegment(s);
                  }
                  in.close();
          }
          catch(IOException e)
          {
                  System.out.println("Error: " + e);
          }
		  wordTable=new HashMap<String,Double>();
		  wordTable.putAll(Practice.d.hm);
		  wordSegment(content);
	  }
	  public Email(String fileName,Dictionary dictionary) {
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
                	  {
                		  date=s.substring(3, s.length()-4);
                	  }
                	  else if (i==1) 
                		  sender=s.substring(3, s.length()-4);
                	  else if(i==2)
                	  {
                		  title=s.substring(3, s.length()-4);
                	  }  
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
	  public int wordSegment(String Sentence) { //中文分词
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

	  
}
