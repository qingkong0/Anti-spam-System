import java.util.*;
import java.io.*;
public class Practice {
	HashMap<String, Double> t1;//ͳ��ѵ���������ʼ��Ĵ������
	HashMap<String, Double> t2;//ͳ��ѵ���������ʼ��Ĵ������
	int n1;//ͳ��ѵ���������ʼ�������
	int n2;//ͳ��ѵ���������ʼ�������
	FileListFilter s1;
	FileListFilter s2;
	Dictionary d=new Dictionary("D:\\360��ȫ���������\\www.NewXing.com\\�ٶȷִʴʿ�.txt");
	public void p1()
	{		
		n1=0;
		t1=new HashMap<String, Double>();
		t1.putAll(d.hm);
		s1=new FileListFilter("txt");
		File[] c1;
		File m1=new File("E:\\eclipse-workspace\\anti-sample\\emails\\�����ʼ�(train)");
		c1=m1.listFiles(s1);
		if(c1!=null)
			for(File file:c1)
			{
			   email e1=new email
				 (file.getPath(),d);
		        for (String key : e1.wordTable.keySet()) {
			    if(e1.wordTable.get(key)>0)
			    	t1.put(key, t1.get(key)+1);
		        }
		        n1++;
			}
		for (String key : t1.keySet())//�������
		{
			if(t1.get(key)==0)
				t1.put(key, 0.0001);
			else
				t1.put(key, t1.get(key)/n1);
		}
		String line1 = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw;
		try {
			File file = new File("E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt");
			if (file.exists())
			{
			file.delete();
			file.createNewFile();
			}
			fw = new FileWriter("E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt", true);		
	    	for(Map.Entry<String, Double> entry : t1.entrySet()) {
	    		str.append(entry.getKey()+":"+entry.getValue()).append(line1);
	    }
	    	fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void p2()
	{
		n2=0;
		t2=new HashMap<String, Double>();
		t2.putAll(d.hm);
		s2=new FileListFilter("txt");
		File[] c2;
		File m2=new File("E:\\eclipse-workspace\\anti-sample\\emails\\�����ʼ�(train)");
		c2=m2.listFiles(s2);
		if(c2!=null)
			for(File file:c2)
		{
			email e1=new email
				(file.getPath(),d);
		for (String key : e1.wordTable.keySet()) {
			if(e1.wordTable.get(key)>0)
				t2.put(key, t2.get(key)+1);
        }
		n2++;
		}
		for (String key : t2.keySet())//�������
		{
			if(t2.get(key)==0)
				t2.put(key, 0.0001);
			else
				t2.put(key, t2.get(key)/n2);
		}
		String line2 = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		FileWriter fw;
		try {
			File file = new File("E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt");
			if (file.exists())
			{
			file.delete();
			file.createNewFile();
			}
			fw = new FileWriter("E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt", true);		
	    	for(Map.Entry<String, Double> entry : t2.entrySet()) {
	    		str.append(entry.getKey()+":"+entry.getValue()).append(line2);
	    }
	    	fw.write(str.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
