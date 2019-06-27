import java.io.ObjectInputStream.GetField;

import java.util.Date;

import java.util.Properties;



import javax.mail.Address;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.AddressException;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import javax.swing.text.html.MinimalHTMLWriter;

public class Main {
    public static String senderAddress = "103654741@qq.com";
    public static String recipientAddress = "103654741@qq.com";
    public static String senderAccout = "103654741@qq.com";
    public static String senderPassword = "zwdeugruuhmdbjbi";

    public static void main1(String []args)throws Exception
    {
        Properties props = new Properties();

        props.setProperty("mail.smtp.auth","true");
        props.setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.host","smtp.qq.com");

        Session session = Session.getInstance(props);
        //session.setDebug(true);

        Message msg = getMimeMessage(session);

        Transport transport = session.getTransport();
        transport.connect(senderAccout,senderPassword);
        transport.sendMessage(msg,msg.getAllRecipients());
        transport.close();
    }

    /**

     * 获得创建一封邮件的实例对象

     * @param session

     * @return

     * @throws MessagingException

     * @throws AddressException

     */
    public static MimeMessage getMimeMessage(Session session)throws Exception
    {
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(senderAddress));
        /**

         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行

         * MimeMessage.RecipientType.TO:发送

         * MimeMessage.RecipientType.CC：抄送

         * MimeMessage.RecipientType.BCC：密送

         */
        //收件人
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(recipientAddress));
        msg.setSubject("主题","UTF-8");
        msg.setContent("正文","text/html;charset=UTF-8");
        msg.setSentDate(new Date());

        return msg;
    }
}
