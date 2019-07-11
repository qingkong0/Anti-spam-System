import java.util.*;
import java.io.*;
public class EmailHashMap {
	HashMap<String,Double> eh1;//�����ʼ��ʵ�
	HashMap<String,Double> eh2;//�����ʼ��ʵ�
	String[] blackNames;//������
	String[] whiteNames;//������
	int bn;//����������
	int wn;//����������
	 public EmailHashMap(String fileName1,String fileName2)
	    {
	            eh1 = new<String,Double> HashMap();
	            eh1.putAll(Practice.d.hm);
	            eh2 = new<String,Double> HashMap();
	            eh2.putAll(Practice.d.hm);
	            try
	            {
	                    BufferedReader in1=
	                            new BufferedReader(
	                                    new FileReader(fileName1) );
	                    BufferedReader in2=
	                            new BufferedReader(
	                                    new FileReader(fileName2) );
	                    String s1;
	                    String s2;
	                    String []word1;
	                    String []word2;
	                    while((s1 = in1.readLine()) != null)
	                    {
	                    	word1=s1.split(":");	          
	                        eh1.put(word1[0],Double.parseDouble(word1[1]));
	                    }
	                    while((s2 = in2.readLine()) != null)
	                    {
	                    	word2=s2.split(":");	          
	                        eh2.put(word2[0],Double.parseDouble(word2[1]));
	                    }
	                    in1.close();
	                    in2.close();
	            }
	            catch(IOException e)
	            {
	                    System.out.println("Error: " + e);
	            }
	    }
	 public EmailHashMap() {
		 blackNames=new String[1000];
		 whiteNames=new String[1000];
		 bn=0;
		 wn=0;
		 try
         {
                 BufferedReader in1=
                         new BufferedReader(
                                 new FileReader(LoginEmail.EmailUserName+"\\黑白名单\\黑名单.txt") );
                 BufferedReader in2=
                         new BufferedReader(
                                 new FileReader(LoginEmail.EmailUserName+"\\黑白名单\\白名单.txt") );
                 String s1;
                 String s2;
                 while((s1 = in1.readLine()) != null)
                 {
                	 blackNames[bn]=s1;
                	 bn++;
                 }
                 while((s2 = in2.readLine()) != null)
                 {
                	 whiteNames[wn]=s2;
                	 wn++;
                 }
                in1.close();
                in2.close();
         }
         catch(IOException e)
         {
                 System.out.println("Error: " + e);
         }
	}
}
