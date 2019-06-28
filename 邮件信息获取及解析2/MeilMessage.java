import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MeilMessage {
    private MimeMessage mimeMessage = null;
    private StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象
    private String dateFormat = "yy-MM-dd HH:mm"; // 默认的日前显示格式

    public MeilMessage() { }

    public MeilMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
    }

    public String getFrom()throws Exception//解析地址
    {


        InternetAddress address[] = (InternetAddress[])mimeMessage.getFrom();
        String from = address[0].getAddress();
        if(from==null)
        {
            from = "";
            System.out.println("无法知道发送者");
        }

        String personal = address[0].getPersonal();
        if(personal == null)
        {
            personal ="";
            System.out.println("无法知道发送者名字");
        }

        String fromAddr = null;
        if(personal !=null||from !=null)
        {
            fromAddr = personal + "<" + from + ">";
            //System.out.println("发送者:" + fromAddr);
        }



        return fromAddr;
    }

    public String getMailAddress(String type) throws Exception //收件人信息
    {
        String mailAddr = "";
        String addType = type.toUpperCase();

        InternetAddress address[] = null;
        if (addType.equals("TO")||addType.equals("CC")||addType.equals("BCC"))
        {
            if(addType.equals("TO"))
            {
                address = (InternetAddress[])mimeMessage.getRecipients(Message.RecipientType.TO);
            }
            else if(addType.equals("CC"))
            {
                address = (InternetAddress[])mimeMessage.getRecipients(Message.RecipientType.CC);
            }
            else if(addType.equals("BCC"))
            {
                address = (InternetAddress[])mimeMessage.getRecipients(Message.RecipientType.BCC);
            }

            if(address!=null)
            {
                for(int i =0;i<address.length;i++)
                {
                    String emailAddr = address[i].getAddress();
                    if(emailAddr == null)
                    {
                        emailAddr = "";
                    }
                    else {

                        emailAddr = MimeUtility.decodeText(emailAddr);
                        ;
                    }
                    String personal = address[i].getPersonal();
                    if(personal == null)
                    {
                        personal = "";
                    }
                    else {

                        personal = MimeUtility.decodeText(personal);

                    }
                    String compositeto = personal + "<" + emailAddr + ">";
                    System.out.println("收件人："+compositeto);
                    mailAddr += "," + compositeto;
                }
            }
        }else
        {
            System.out.println("邮件类型错误");
        }
        return mailAddr;
    }

    public String getSubject() throws Exception//获得邮件主题
    {

        String subject ="";
        try
        {
            subject=MimeUtility.decodeText(mimeMessage.getSubject());

            if(subject == null)
            {
                subject="";
            }else
            {
                //System.out.println("主题："+subject);
            }
        }catch (Exception ex)
        {

        }


        return subject;
    }

    public String getBodyText()
    {
        String body = bodyText.toString();
        if(body.equals(""))System.out.println("无正文内容");
        return body;
    }

    public void getMailContent(Part part)throws Exception//根据邮件类型读取相应的邮件正文内容
    {

        String contentType = part.getContentType();//邮件类型

        int nameIndex = contentType.indexOf("name");

        boolean conName = false;

        if(nameIndex != -1)//如果有图片的话 conName!=-1
        {
            conName = true;
        }

        if(part.isMimeType("text/plain") && conName ==false)
        {
            bodyText=new StringBuffer();
            bodyText.append((String)part.getContent());
        }else if(part.isMimeType("text/html")&&conName==false)
        {
            bodyText=new StringBuffer();
            bodyText.append((String)part.getContent());
        }else if(part.isMimeType("multipart/*"))
        {
            bodyText=new StringBuffer();
            Multipart multipart = (Multipart)part.getContent();
            int count = multipart.getCount();
            for(int i=0;i<count;i++)
            {
                getMailContent(multipart.getBodyPart(i));
            }
        }else if(part.isMimeType("message/rfc822"))
        {
            bodyText=new StringBuffer();
            getMailContent((Part)part.getContent());
        }



    }

    public boolean outTxt(Part part)throws Exception
    {
        if(part.isMimeType("text/plain"))
        {
            bodyText=new StringBuffer();
            //isOut=true;
            bodyText.append((String)part.getContent());
            return true;
        }
        return false;
    }

    public boolean outHtml(Part part)throws Exception
    {
        if(part.isMimeType("txt/html"))
        {
            bodyText=new StringBuffer();
            //isOut=true;
            bodyText.append((String)part.getContent());
            return true;
        }
        return false;
    }

    public String getSentDate()throws Exception //获得发件日期
    {

        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String strSentDate = format.format(sentDate);
        //System.out.println(strSentDate);


        return strSentDate;
    }

    public String getMessageID()throws Exception
    {
        String messageID = mimeMessage.getMessageID();
        System.out.println("邮件ID"+messageID);
        return messageID;
    }

}
