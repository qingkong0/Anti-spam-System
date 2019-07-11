
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MeilMessage {
    private MimeMessage mimeMessage = null;
    private StringBuffer bodyText = new StringBuffer(); // ����ʼ����ݵ�StringBuffer����
    private String dateFormat = "yy-MM-dd HH:mm"; // Ĭ�ϵ���ǰ��ʾ��ʽ
    private String saveAttachPath;
    public MeilMessage() { }

    public MeilMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage)
    {
        this.mimeMessage = mimeMessage;
    }

    public String getFrom()throws Exception//������ַ
    {


        InternetAddress address[] = (InternetAddress[])mimeMessage.getFrom();
        String from = address[0].getAddress();
        if(from==null)
        {
            from = "";
            System.out.println("�޷�֪��������");
        }

        String personal = address[0].getPersonal();
        if(personal == null)
        {
            personal ="";
            System.out.println("�޷�֪������������");
        }

        String fromAddr = null;
        if(personal !=null||from !=null)
        {
            fromAddr = personal + "<" + from + ">";
            //System.out.println("������:" + fromAddr);
        }



        return fromAddr;
    }

    public String getMailAddress(String type) throws Exception //�ռ�����Ϣ
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
                    System.out.println("�ռ��ˣ�"+compositeto);
                    mailAddr += "," + compositeto;
                }
            }
        }else
        {
            System.out.println("�ʼ����ʹ���");
        }
        return mailAddr;
    }

    public String getSubject() throws Exception//����ʼ�����
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
                //System.out.println("���⣺"+subject);
            }
        }catch (Exception ex)
        {

        }


        return subject;
    }

    public String getBodyText()
    {
        String body = bodyText.toString();
        if(body.equals(""))System.out.println("空白内容");
        return body;
    }

    public void getMailContent(Part part)throws Exception//�����ʼ����Ͷ�ȡ��Ӧ���ʼ���������
    {

        String contentType = part.getContentType();//�ʼ�����

        int nameIndex = contentType.indexOf("name");

        boolean conName = false;

        if(nameIndex != -1)//�����ͼƬ�Ļ� conName!=-1
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

    public String getSentDate()throws Exception //��÷�������
    {

        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String strSentDate = format.format(sentDate);
        return strSentDate;
    }

    public String getMessageID()throws Exception
    {
        String messageID = mimeMessage.getMessageID();
        System.out.println("�ʼ�ID"+messageID);
        return messageID;
    }
    public boolean isContainAttach(Part part) throws Exception //是否包含附件
    {
        boolean attachFlag = false;

        if (part.isMimeType("multipart/*"))
        {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++)
            {
                BodyPart mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                    attachFlag = true;
                else if (mPart.isMimeType("multipart/*"))
                {
                    attachFlag = isContainAttach((Part) mPart);
                }
                else
                {
                    String conType = mPart.getContentType();

                    if (conType.toLowerCase().indexOf("application") != -1)
                        attachFlag = true;
                    if (conType.toLowerCase().indexOf("name") != -1)
                        attachFlag = true;
                }
            }
        }
        else if (part.isMimeType("message/rfc822"))
        {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;
    }
    public void saveAttachMent(Part part) throws Exception  //存放附件
    {
        String fileName = "";

        if (part.isMimeType("multipart/*"))
        {
            Multipart mp = (Multipart) part.getContent();

            for (int i = 0; i < mp.getCount(); i++)
            {
                BodyPart mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE))))
                {
                    fileName = mPart.getFileName();
                    //System.out.println(1);
                    if ((fileName != null) && (fileName.toLowerCase().indexOf("gb18030") != -1))
                    {
                        fileName = MimeUtility.decodeText(fileName);
                        //System.out.println(2);
                    }
                    saveFile(fileName, mPart.getInputStream());
                }
                else if (mPart.isMimeType("multipart/*"))
                {
                    saveAttachMent(mPart);
                }
                else
                {
                    fileName = mPart.getFileName();
                    //System.out.println(3);

                    if ((fileName != null) && (fileName.toLowerCase().indexOf("gb18030") != -1))
                    {
                        fileName = MimeUtility.decodeText(fileName);

                       // System.out.println(4);
                        saveFile(fileName, mPart.getInputStream());
                    }
                }
            }
        }
        else if (part.isMimeType("message/rfc822"))
        {
            saveAttachMent((Part) part.getContent());
        }
    }
    public void setAttachPath(String attachPath)
    {
        this.saveAttachPath = attachPath;
    }
    public String getAttachPath()  //获取路径
    {
        return saveAttachPath;
    }
    private void saveFile(String fileName, InputStream in) throws Exception  //设置路径
    {
        String osName = System.getProperty("os.name");
        String storeDir = getAttachPath();
        String separator = "";
        if (osName == null)
        {
            osName = "";
        }
        if (osName.toLowerCase().indexOf("win") != -1)
        {
            separator = "\\";
            if (storeDir == null || storeDir.equals(""))
                storeDir = "c:\\tmp";
        }
        else
        {
            separator = "/";
            storeDir = "/tmp";
        }
        File storeFile = new File(storeDir + separator + fileName);
        System.out.println("附件的保存地址:　" + storeFile.toString());

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;

        try
        {
            bos = new BufferedOutputStream(new FileOutputStream(storeFile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1)
            {
                bos.write(c);
                bos.flush();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw new Exception("文件保存失败!");
        }
        finally
        {
            bos.close();
            bis.close();
        }
    }

}