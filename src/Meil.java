import java.util.HashMap;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

public class Meil {
    public Message message;
    public Connect connect;
    public int meilID;
    static public int isBlack=2;//�ж��Ƿ�Ϊ�ڰ�����
    static public int predict=2;//Ԥ����ʼ�����
    public String address1;//����
    public String address2;//��ַ
    public String title;
    public String from;
    public String data;
    public String content;
    public void saveFile(String path) //保存附件 必须要使用pop3协议，否则下载速度很慢
    {
    	try {
			MeilMessage meilMessage = new MeilMessage();
        if(meilMessage.isContainAttach((Part)message))
        {
            meilMessage.setMimeMessage((MimeMessage) message);
            meilMessage.setAttachPath(path);
            meilMessage.saveAttachMent((Part)message);
        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}       
    }

    public Meil(Connect connect,Message message,int meilID)
    {
    	this.connect=connect;
        this.message=message;
        this.meilID=meilID;
    }

    public void outMessage(MeilMessage meilMessage,int n)throws Exception
    {   
    	isBlack=2;
    	predict=2;
    	try {
			meilMessage.setMimeMessage((MimeMessage)message);
        meilMessage.getMailContent((Part)message);
        StringBuffer sb = new StringBuffer();
        sb.append("<p>");
        data=meilMessage.getSentDate();
        sb.append(data); 
        sb.append("</p>");
        sb.append("\r\n");
        sb.append("<p>");
        from=meilMessage.getFrom();
        sb.append(from);
        String word1[]=from.split("@");
        String word2[]=from.split("<");
        address1=word1[1].substring(0, word1[1].length()-1);
        address2=word2[1].substring(0, word2[1].length()-1);
        for(int i=0;i<LoginEmail.BW.bn;i++)
        {
        	if(address1.equals(LoginEmail.BW.blackNames[i])||address2.equals(LoginEmail.BW.blackNames[i]))
        	{
        		isBlack=0;
        	}
        }
        if(isBlack==2)
        {
        	for(int i=0;i<LoginEmail.BW.wn;i++)
            {
            	if(address1.equals(LoginEmail.BW.whiteNames[i])||address2.equals(LoginEmail.BW.whiteNames[i]))
            	{
            		isBlack=1;
            	}
            }
        }
        sb.append("</p>");
        sb.append("\r\n");
        sb.append("<p>");
        title=meilMessage.getSubject();
        sb.append(title);
        sb.append("</p>");
        sb.append("\r\n");
        sb.append("<html>\r\n" + 
        		"<head>\r\n" + 
        		"<meta>\r\n" + 
        		"<title></title>\r\n" + 
        		"</head>\r\n" + 
        		"<script text=\"javaScript\">\r\n" + 
        		"function welcome(){\r\n" + 
        		"alert(\"测试button按钮\");\r\n" + 
        		"}\r\n" + 
        		"</script>\r\n" + 
        		"<body>\r\n" + 
        		"<input type=\"button\" value=\"点击我\" onclick=\"welcome();\">\r\n" + 
        		"</body>\r\n" + 
        		"</html>");
        if(meilMessage.isContainAttach((Part)message))//如果有附件
        {
        	
        }
        String html = meilMessage.getBodyText().replaceAll("gb2312", "utf-8");
        sb.append(html);
        AnalysisHtml analysisHtml = new AnalysisHtml();
        content=analysisHtml.Html2Text(html).replaceAll("&nbsp"," ");        
		Email email=new Email(content, Practice.d, 0);
		if(isBlack==0)//�������ʼ�
		{
			MainFrame.mw.exit=true;
			connect.deleteMail(n);
			connect.closeConnect();
			MainFrame.mw.exit=false;
		}
		else if(isBlack==1)//�������ʼ�
		{
			//System.out.println("白名单------------");
			FileManage.creatFile(LoginEmail.EmailUserName+"\\邮件箱",LoginEmail.EmailUserName+"-"+n, sb.toString());
			ProgressBarDemo.EmailsNum++;
			predict=1;
		}
		else
		{
			double j=Judge.judgeMail(Judge.mailWordList(email), Judge.createSpamProbabilityMap(LoginEmail.ehm.eh1, LoginEmail.ehm.eh2));
			if(j>0.5)
			{
				if(MailboxSettingFrame.select[0]==1)
				{
					MainFrame.mw.exit=true;
					connect.deleteMail(n);
					connect.closeConnect();
					MainFrame.mw.exit=false;
				}
				else {
					FileManage.creatFile(LoginEmail.EmailUserName+"\\垃圾箱", LoginEmail.EmailUserName+"-"+n, sb.toString());
			        ProgressBarDemo.EmailsNum++;
				}			
				predict=0;
			}
			else
			{
			FileManage.creatFile(LoginEmail.EmailUserName+"\\邮件箱",LoginEmail.EmailUserName+"-"+n, sb.toString());
			ProgressBarDemo.EmailsNum++;
			predict=1;
			}
		}		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			//FileManage.creatFile(LoginEmail.EmailUserName+"\\������", LoginEmail.EmailUserName+"-"+n, "");
		}
    }

}