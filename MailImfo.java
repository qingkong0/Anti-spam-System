import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailImfo {
    private MimeMessage mimeMessage = null;
    private String saveAttachPath = ""; // 附件下载后的存放目录
    private StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象
    private String dateFormat = "yy-MM-dd HH:mm"; // 默认的日前显示格式

    public MailImfo()
    {
    }

    public MailImfo(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
        //System.out.println("创建一个ReceiveEmail对象....");
    }

    public void setMimeMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
        //System.out.println("设置一个MimeMessage对象...");
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
            System.out.println("发送者是" + fromAddr);
        }
        return fromAddr;
    }



    public String getBodyText()
    {
        return bodyText.toString();
    }

    /**
     * 　　*　解析邮件，把得到的邮件内容保存到一个StringBuffer对象中，解析邮件
     * 　　*　主要是根据MimeType类型的不同执行不同的操作，一步一步的解析 　　
     */

    public void getMailContent(Part part) throws Exception
    {

        String contentType = part.getContentType();
        // 获得邮件的MimeType类型
        //System.out.println("邮件的MimeType类型: " + contentType);

        int nameIndex = contentType.indexOf("name");

        boolean conName = false;

        if (nameIndex != -1)
        {
            conName = true;
        }

        System.out.println("邮件内容的类型:　" + contentType);

        if (part.isMimeType("text/plain") && conName == false)
        {
            // text/plain 类型
            bodyText.append((String) part.getContent());
        }
        else if (part.isMimeType("text/html") && conName == false)
        {
            // text/html 类型
            bodyText.append((String) part.getContent());
        }
        else if (part.isMimeType("multipart/*"))
        {
            // multipart/*
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++)
            {
                getMailContent(multipart.getBodyPart(i));
            }
        }
        else if (part.isMimeType("message/rfc822"))
        {
            // message/rfc822
            getMailContent((Part) part.getContent());
        }
        else
        {

        }
    }

}
