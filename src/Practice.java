import java.util.*;


import java.io.*;
import java.nio.channels.FileChannel;
public class Practice {
	static HashMap<String, Double> t1;//ͳ��ѵ���������ʼ��Ĵ������
	static HashMap<String, Double> t2;//ͳ��ѵ���������ʼ��Ĵ������
	static int n1;//ͳ��ѵ���������ʼ�������
	static int n2;//ͳ��ѵ���������ʼ�������
	static FileListFilter s1;
	static FileListFilter s2;
	static Dictionary d=new Dictionary(Dictionary.dicpath);
	static String praPath1="emails\\垃圾邮件(train)";
	static String praPath2="emails\\正常邮件(train)";
	static String fp1="训练词典\\垃圾邮件词典.txt";
	static String fp2="训练词典\\正常邮件词典.txt";
	static String[] email1Title=new String[1000];//�����ʼ�����
	static String[] email1Path=new String[1000];//�����ʼ�·��
	static String[] email1Date=new String[1000];//�����ʼ�ʱ��
	static int[] email1Num=new int[1000];//��¼�����ʼ�
	static String[] email2Title=new String[1000];//�����ʼ�����
	static String[] email2Path=new String[1000];//�����ʼ�·��
	static String[] email2Date=new String[1000];//�����ʼ�ʱ��
	static int[] email2Num=new int[1000];//��¼�����ʼ�
	static int e1;
	static int e2;
	static public EmailHashMap ehm=new EmailHashMap(Practice.fp1,Practice.fp2);
	static public void division() 
	  {
		  email1Title=new String[1000];
		  email1Path=new String[1000];
		  email1Date=new String[1000];
		  email1Num=new int[1000];
		  email2Title=new String[1000];
		  email2Path=new String[1000];
		  email2Date=new String[1000];
		  email2Num=new int[1000];
		  e1=0;
		  e2=0;
		  s1=new FileListFilter("html");
		  s2=new FileListFilter("html");
		  File[] c1;
		  File[] c2;
		  File m1=new File(LoginEmail.EmailUserName+"\\垃圾箱");
		  File m2=new File(LoginEmail.EmailUserName+"\\邮件箱");
		  c1=m1.listFiles(s1);
		  int num1=0;
		  int num2=0;
		  if(c1!=null)
			  for(File file:c1)
			  {
				  String word[]=file.getName().split("-");
				  int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
				  Email email=new Email(file.getPath(), d);
				  email1Title[id]=email.title;
				  email1Date[id]=email.date;
				  email1Path[id]=file.getPath();
				  email1Num[e1]=id;
				  e1++;
			  }
		  for(int i=0;i<e1;i++)
			  for(int j=i+1;j<e1;j++)
			  {
				  if(email1Num[i]>email1Num[j])
				  {
					  int temp=email1Num[i];
					  email1Num[i]=email1Num[j];
					  email1Num[j]=temp;
				  }
			  }
		  c2=m2.listFiles(s2);
		  if(c2!=null)
			  for(File file:c2)
			  {
				  String word[]=file.getName().split("-");
				  int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
				  Email email=new Email(file.getPath(), d);
				  email2Title[id]=email.title;
				  email2Date[id]=email.date;
				  email2Path[id]=file.getPath();
				  email2Num[e2]=id;
				  e2++;
			  }
		  for(int i=0;i<e2;i++)
			  for(int j=i+1;j<e2;j++)
			  {
				  if(email2Num[i]>email2Num[j])
				  {
					  int temp=email2Num[i];
					  email2Num[i]=email2Num[j];
					  email2Num[j]=temp;
				  }
			  }
		  ProgressBarDemo.EmailsNum=e1+e2;
	  }
	public Practice() {
		// TODO �Զ����ɵĹ��캯�����
		p1();
		p2();
	}
	public void p1()//��ʼ�������ʼ��ʵ�
	{		
		n1=0;
		t1=new HashMap<String, Double>();
		t1.putAll(d.hm);
		s1=new FileListFilter("txt");
		File[] c1;
		File m1=new File(praPath1);
		c1=m1.listFiles(s1);
		if(c1!=null)
			for(File file:c1)
			{
			   Email e1=new Email
				 (file.getPath(),d);
		        for (String key : e1.wordTable.keySet()) {
			    if(e1.wordTable.get(key)>0)
			    	t1.put(key, t1.get(key)+1);
		        }
		        n1++;
		        System.out.println(n1);
			}
		for (String key : t1.keySet())//�������
		{
			if(t1.get(key)==0)
				t1.put(key, 0.0001);
			else
				t1.put(key, t1.get(key)/n1);
		}
		updateF1();
	}
	public void p2()//��ʼ�������ʼ��ʵ�
	{
		n2=0;
		t2=new HashMap<String, Double>();
		t2.putAll(d.hm);
		s2=new FileListFilter("txt");
		File[] c2;
		File m2=new File(praPath2);
		c2=m2.listFiles(s2);
		if(c2!=null)
			for(File file:c2)
		{
			Email e1=new Email
				(file.getPath(),d);
		for (String key : e1.wordTable.keySet()) {
			if(e1.wordTable.get(key)>0)
				t2.put(key, t2.get(key)+1);
        }
		n2++;
		System.out.println(n2);
		}
		for (String key : t2.keySet())//�������
		{
			if(t2.get(key)==0)
				t2.put(key, 0.0001);
			else
				t2.put(key, t2.get(key)/n2);
		}
		updateF2();
	}
	static public void learn1(Email email)//ѧϰ�������ʼ��ı�ʵ����
	{
		n1=0;
		s1=new FileListFilter("txt");
		File[] c1;
		File m1=new File(praPath1);
		c1=m1.listFiles(s1);
		if(c1!=null)
			for(File file:c1)
			{
		        n1++;
			}
		n1++;
		for (String key : email.wordTable.keySet()) 
		{
			if(email.wordTable.get(key)>0)
			{
				if(ehm.eh1.get(key)==0.0001)
					ehm.eh1.put(key, ehm.eh1.get(key)*10000/n1);
				else
					ehm.eh1.put(key, (ehm.eh1.get(key)*(n1-1)+1)/n1);	
			}
			else if(email.wordTable.get(key)==0&&ehm.eh1.get(key)>0.0001)
				ehm.eh1.put(key,ehm.eh1.get(key)*(n1-1)/n1);
        }	
		updateF1();
	}
	public static void learn2(Email email)//ѧϰ�������ʼ��ı�ʵ����
	{
		n2=0;
		s2=new FileListFilter("txt");
		File[] c2;
		File m2=new File(praPath2);
		c2=m2.listFiles(s2);
		if(c2!=null)
			for(File file:c2)
			{
		        n2++;
			}
		n2++;
		for (String key : email.wordTable.keySet()) {
			if(email.wordTable.get(key)>0)
			{
				if(ehm.eh2.get(key)==0.0001)
					ehm.eh2.put(key, ehm.eh2.get(key)*10000/n2);
				else
					ehm.eh2.put(key, (ehm.eh2.get(key)*(n2-1)+1)/n2);
			}
			else if(email.wordTable.get(key)==0)
			{
				if(ehm.eh2.get(key)>0.0001)
				{
					ehm.eh2.put(key,ehm.eh2.get(key)*(n2-1)/n2);
				}
			}				
        }	
		updateF2();
	}
    public static void updateF1() 
    {
    	String line1 = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw;
		try {
			File file = new File(fp1);
			if (file.exists())
			{
			file.delete();
			file.createNewFile();
			}
			fw = new FileWriter(fp1, true);		
	    	for(Map.Entry<String, Double> entry : ehm.eh1.entrySet()) {
	    		str.append(entry.getKey()+":"+entry.getValue()).append(line1);
	    }
	    	fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public static void updateF2()
    {
    	String line2 = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw;
		try {
			File file = new File(fp2);
			if (file.exists())
			{
			file.delete();
			file.createNewFile();
			}
			fw = new FileWriter(fp2, true);		
	    	for(Map.Entry<String, Double> entry : ehm.eh2.entrySet()) {
	    		str.append(entry.getKey()+":"+entry.getValue()).append(line2);
	    }
	    	fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

   public static void copyFileUsingFileChannels(File source, File dest) throws IOException {    
        FileChannel inputChannel = null;    
        FileChannel outputChannel = null;    
    try {
        inputChannel = new FileInputStream(source).getChannel();
        outputChannel = new FileOutputStream(dest).getChannel();
        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
    } finally {
        inputChannel.close();
        outputChannel.close();
    }
}
}
