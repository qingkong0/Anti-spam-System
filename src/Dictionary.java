import java.util.*;
import java.io.*;
import java.lang.*;
public class Dictionary {
	HashMap<String,Double> hm;		//a word set
	HashMap<String,Integer> hm1;		//a word set
	static String dicpath="训练词典\\分词词库.txt";
    public Dictionary()
    {
            hm1 = new<String,Integer> HashMap();
            Load2();
    }
    public Dictionary(String fileName)
    {
            hm = new<String,Double> HashMap();
            Load1(fileName);
    }
    public void Load1(String fileName)    //װ�غ���ʵ�
    {
            try
            {
                    BufferedReader in=
                            new BufferedReader(
                                    new FileReader(fileName) );

                    String s;
                    while((s = in.readLine()) != null)
                    {
                            hm.put(s.substring(2, s.length()),(double) 0);
                    }
                    in.close();
            }
            catch(IOException e)
            {
                    System.out.println("Error: " + e);
            }
    }
    public void Load2()    //װ�غ���ʵ�
    {
            try
            {
                    BufferedReader in=
                            new BufferedReader(
                                    new FileReader(dicpath) );
                    String s;
                    int n=0;
                    while((s = in.readLine()) != null)
                    {
                            hm1.put(s.substring(2, s.length()),n);
                            n++;
                    }
                    in.close();
            }
            catch(IOException e)
            {
                    System.out.println("Error: " + e);
            }
    }
    public boolean Find(String word)    //���ֵ����ѯ��
    {
            return hm.containsKey(word);
    }
}
