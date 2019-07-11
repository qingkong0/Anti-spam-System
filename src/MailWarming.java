import java.io.File;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class MailWarming extends Thread{
	boolean exit=false;
	boolean destroy=false;
    public void run()//.start()运行
    {
        while(true)
        {
        	try {
        		if(destroy)
        		{
        			break;
        		}
        		Thread.sleep(5000);
        		System.out.println("执行");
        		if(!exit)
        		{
        			isNewMail();
        		}         		
        	}catch (Exception e) {
                System.out.println(0);
        	}            
        }
    }
    public void isNewMail()throws Exception  //����ʹ�ö��߳�
    {       

        Connect connect = new Connect();
        connect.Conn(LoginEmail.EmailUserName,LoginEmail.EmailUserLiscen);
        int a=ProgressBarDemo.EmailsNum;//�����ļ����ʼ�����
        int b=connect.messages.length;//�����ʼ�����
        System.out.println("当前文件夹邮件数量"+a);
        System.out.println("现有邮件数量"+b);
        if(a<b)
        {
        	for(int i=0;i<b-a;i++)
        	{
        		Meil meil = new Meil(connect,connect.messages[a+i],a+i);
        		MeilMessage meilMessage = new MeilMessage((MimeMessage)meil.message);
        		meil.outMessage(meilMessage,a+i);
        		if(Meil.predict==0)
        		{
        			//TrashCanFrame.initTable();
        			if(MailboxSettingFrame.select[1]==1)//����������ʾ
        			{
        				if(MailboxSettingFrame.select[0]==0)
        				{
        					addTrashCanFrameTable(meil);
        					new NewEmailFrame(meil,1);//���յ������ʼ����ŵ�������
        					//JOptionPane.showMessageDialog(null, "�յ�һ�������ʼ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
        				} 
        				else{
        					new NewEmailFrame(meil,2);//���յ������ʼ�ֱ��ɾ��
        				}
        			}        			   			
        		}
        		else if(Meil.predict==1)
        		{
        			addMainFrameTable(meil);
        			//MainFrame.initTable();
        			new NewEmailFrame(meil,0);//���յ������ʼ�
        			//JOptionPane.showMessageDialog(null, "�յ�һ�������ʼ�", "", JOptionPane.ERROR_MESSAGE);        			
        		}
        	}         
        }
    }
    static public void addMainFrameTable(Meil meil)
	{
		int id=meil.meilID;
		File file=new File(LoginEmail.EmailUserName+"\\邮件箱\\"+LoginEmail.EmailUserName+"-"+meil.meilID+".html");
		Email email=new Email(file.getPath(), Practice.d);
		Practice.email2Title[id]=email.title;
		Practice.email2Date[id]=email.date;
		Practice.email2Path[id]=file.getPath();
		Practice.email2Num[Practice.e2]=id;
		Practice.e2++;
		Object[] newinfor=new Object[3];
		newinfor[0]=false;
		newinfor[1]=email.title;
		newinfor[2]=email.date;
		MainFrame.tableModel.addRow(newinfor);
	}
    static public void addTrashCanFrameTable(Meil meil)
	{
		int id=meil.meilID;
		File file=new File(LoginEmail.EmailUserName+"\\垃圾箱\\"+LoginEmail.EmailUserName+"-"+meil.meilID+".html");
		Email email=new Email(file.getPath(), Practice.d);
		Practice.email1Title[id]=email.title;
		Practice.email1Date[id]=email.date;
		Practice.email1Path[id]=file.getPath();
		Practice.email1Num[Practice.e1]=id;
		Practice.e1++;
		Object[] newinfor=new Object[3];
		newinfor[0]=false;
		newinfor[1]=email.title;
		newinfor[2]=email.date;
		TrashCanFrame.tableModel.addRow(newinfor);
	}
}
