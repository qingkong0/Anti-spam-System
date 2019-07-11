import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.*;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Message.RecipientType;
import javax.swing.JFrame;
import javax.swing.UIManager;



import libsvm.*;
public class test {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//Dictionary a=Practice.d;
		//System.out.println(a.hm);
		//Judge j=new Judge();
		/*String s1="E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt";
		String s2="E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt";
		EmailHashMap ehm=new EmailHashMap(s1,s2);
		FileListFilter s=new FileListFilter("txt");
		File[] c1;
		File m1=new File("E:\\eclipse-workspace\\anti-sample\\emails\\�����ʼ�(train)\\");
		c1=m1.listFiles(s);
		if(c1!=null)
			for(File file:c1)
			{
			   Email e=new Email
				 (file.getPath(),a);
			   System.out.println(file.getName()+"�������ʼ��ĸ���:"+
						j.judgeMail(j.mailWordList(e), j.createSpamProbabilityMap(ehm.eh1, ehm.eh2)));
			}
		File[] c2;
		File m2=new File("E:\\eclipse-workspace\\anti-sample\\emails\\�����ʼ�(train)\\");
		c2=m2.listFiles(s);
		if(c2!=null)
			for(File file:c2)
			{
			   Email e=new Email
				 (file.getPath(),a);
			   System.out.println(file.getName()+"�������ʼ��ĸ���:"+
						j.judgeMail(j.mailWordList(e), j.createSpamProbabilityMap(ehm.eh1, ehm.eh2)));
			}*/
		/*System.out.println("�����ʼ�����:");
		for (String key : p.t1.keySet()) {
			if(p.t1.get(key)>0.1)
              System.out.println(key+":"+p.t1.get(key));
        }
		System.out.println("�����ʼ�����:");
		for (String key : p.t2.keySet()) {
			if(p.t2.get(key)>0.1)
              System.out.println(key+":"+p.t2.get(key));
        }*/
//		String s1="E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt";
//		String s2="E:\\eclipse-workspace\\anti-sample\\ѵ���ʵ�\\�����ʼ��ʵ�.txt";
//		EmailHashMap ehm1=new EmailHashMap(Practice.fp1,Practice.fp2);
//		for (String key : ehm1.eh1.keySet()) {
//			if(ehm1.eh1.get(key)>0.0001) 
//			{
//				System.out.println(key+":"+ehm1.eh1.get(key));
//			}					
//        }
//		System.out.println("-------------------------------");
//		for (String key : ehm1.eh2.keySet()) {
//			if(ehm1.eh2.get(key)>0.0001) 
//			{
//				System.out.println(key+":"+ehm1.eh2.get(key));
//
//			}					
//        }
//		Email testE=new Email("D:\\360��ȫ���������\\���������ʼ�����������ʾ\\����\\ѩ51.txt", a);		
//		if(j.judgeMail(j.mailWordList(testE), j.createSpamProbabilityMap(ehm1.eh1, ehm1.eh2))>0.5)
//		{
//			p.learn1(testE);
//			System.out.println("-----------------------------");			
//			EmailHashMap ehm2=new EmailHashMap(s1,s2);
//			for (String key : ehm2.eh1.keySet()) {
//				if(ehm2.eh1.get(key)>0.0001) 
//				{
//					System.out.println(key+":"+ehm1.eh1.get(key));
//					System.out.println(key+":"+ehm2.eh1.get(key));
//				}					
//	        }
//		}
//		else
//		{
//			System.out.println("-----------------------------");
//			System.out.println("ѩ51�������ʼ�");
//			p.learn2(testE);
//			EmailHashMap ehm2=new EmailHashMap(s1,s2);
//			for (String key : testE.wordTable.keySet()) {
//				if(testE.wordTable.get(key)>0)
//	              System.out.println(key+":"+ehm2.eh2.get(key));
//	        }
//		}
//		Practice.division();
		//Practice practice=new Practice();		

			
			try {
				//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
				
				javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
				new LoginEmail();
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
			}
			//nightshine_lyz Lyz991226
//		try {
		//877423589@qq.com swhkplawjnbcbcga
		//2649278834@qq.com yyqravcfpzptecfd
		//nightshine_lyz@163.com   Lyz991226
		//2502823040@qq.com
//				String recipientAccout = "103654741@qq.com";
//        String recipientPassword = "zwdeugruuhmdbjbi";
//		ugfaezmnnfdfecdc
//        Connect connect=new Connect();
//        connect.setConnect(recipientAccout,recipientPassword);
//        //connect.getConnect();//POP3Э��
//        connect.getConnectbyImap();//IMAPЭ��
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		{Practice.n1=0;
//		Practice.t1=new HashMap<String, Double>();
//		Practice.t1.putAll(Practice.d.hm);
		/*Dictionary d=new Dictionary();
		FileListFilter s=new FileListFilter("txt");
		File[] c1;
		File m1=new File("emails\\�����ʼ�(train)");
		c1=m1.listFiles(s);
		File[] c2;
		File m2=new File("emails\\�����ʼ�(train)");
		c2=m2.listFiles(s);
		try
		{
			String line1 = System.getProperty("line.separator");
			FileWriter fw;
			File file = new File("svm�ʵ�\\svmѵ����.txt");
			fw = new FileWriter("svm�ʵ�\\svmѵ����.txt", true);
			if (file.exists())
			{
				file.delete();
				file.createNewFile();
			}	
			if(c1!=null)
				for(File file1:c1)
				{
					StringBuffer str = new StringBuffer();
					Email e1=new Email(file1.getPath());
					str.append("-1");
					ArrayList<String> list=Judge.mailWordList(e1);										 				 	
					for (String key : list) {    
						str.append(" "+d.hm1.get(key)+":"+1); 
					} 
					 str.append(line1);
					 System.out.println(str);
					 fw.write(str.toString());
				}					 
			if(c2!=null)
				for(File file2:c2)
				{
					StringBuffer str = new StringBuffer();
					Email e2=new Email(file2.getPath());
					str.append("+1");				 	
					ArrayList<String> list=Judge.mailWordList(e2);
					for (String key : list) {    
						str.append(" "+d.hm1.get(key)+":"+1);
					}    	
					str.append(line1);
					System.out.println(str);
					fw.write(str.toString());				        
				}
			fw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		File[] c;
		File m=new File("emails\\�ʼ�(test)");
		c=m.listFiles(s);
		try 
		{
		String line1 = System.getProperty("line.separator");		
		FileWriter fw;	
		File file1 = new File("svm�ʵ�\\svm���Լ�.txt");
		fw = new FileWriter("svm�ʵ�\\svm���Լ�.txt", true);
		if (file1.exists())
		{
			file1.delete();
			file1.createNewFile();
		}
		if(c!=null)
			for(File file:c)
			{
				StringBuffer str = new StringBuffer();
				Email e=new Email(file.getPath());		 				
				if(file.getName().substring(0, 2).equals("����"))
				{	
					str.append("-1");
				}
				else
				{
					str.append("+1");						
				}	
				ArrayList<String> list=Judge.mailWordList(e);
				for (String key : list) {    
					str.append(" "+d.hm1.get(key)+":"+1);
				}  
				str.append(line1);
				System.out.println(str);
				fw.write(str.toString());
			}
		fw.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		String[] arg = { "svm�ʵ�\\svmѵ����.txt", //ѵ����
				"svm�ʵ�\\svmѵ�����.txt" };
		String[] parg = { "svm�ʵ�\\svm���Լ�.txt", //��������
				"svm�ʵ�\\svmѵ�����.txt", // ����ѵ��ģ��
				"svm�ʵ�\\svmԤ����.txt" }; //Ԥ����
System.out.println("........SVM���п�ʼ..........");
long start=System.currentTimeMillis(); 
try {
	svm_train.main(arg); //ѵ��
    System.out.println("��ʱ:"+(System.currentTimeMillis()-start)); 
    //Ԥ��
    svm_predict.main(parg); 
} catch (Exception e) {
	System.out.println("����!"); 
	// TODO: handle exception
}*/
//		for (String key : Practice.t1.keySet())//�������
//		{
//			if(Practice.t1.get(key)==0)
//				Practice.t1.put(key, 0.0001);
//			else
//				Practice.t1.put(key,Practice.t1.get(key)/Practice.n1);
//		}
//		Practice.updateF1();
//		}
//		{Practice.n2=0;
//		Practice.t2=new HashMap<String, Double>();
//		Practice.t2.putAll(Practice.d.hm);		
//		for (String key : Practice.t2.keySet())//�������
//		{
//			if(Practice.t2.get(key)==0)
//				Practice.t2.put(key, 0.0001);
//			else
//				Practice.t2.put(key,Practice.t2.get(key)/Practice.n2);
//		}
//		Practice.updateF2();
//		}		
//		EmailHashMap ehm=new EmailHashMap(Practice.fp1,Practice.fp2);
//		int n1=0;
//		int n2=0;
//		int a1=0;
//		int a2=0;
//				 if(file.getName().substring(0, 2).equals("����"))
//				 {					 
//					 n1++;
//					 double g=Judge.judgeMail(Judge.mailWordList(e), Judge.createSpamProbabilityMap(ehm.eh1, ehm.eh2));
//					 System.out.println(file.getName()+"�������ʼ��ĸ���Ϊ:"+g);
//					 if(g>0.5)
//					 {
//						 a1++;
//					 }
//				 }
//				 else{
//					 n2++;
//					 double g=Judge.judgeMail(Judge.mailWordList(e), Judge.createSpamProbabilityMap(ehm.eh1, ehm.eh2));
//					 System.out.println(file.getName()+"�������ʼ��ĸ���Ϊ:"+g);
//					 if(g<=0.5)
//					 {
//						 a2++;
//					 }
//				 }
//		double an1=(double)a1/n1;
//		double an2=(double)a2/n2;
//		double an=(double)(a1+a2)/(n1+n2);
//		System.out.println("----------------------");
//		System.out.println("���������ʼ���ȷ��Ϊ"+an1);
//		System.out.println("���������ʼ���ȷ��Ϊ"+an2);
//		System.out.println("�����ʼ�����ȷ��Ϊ"+an);
    }
}
