import java.util.Properties;

import javax.mail.Address;
import javax.mail.Part;
import javax.mail.Folder;
import javax.mail.Multipart;
import javax.mail.Message;

import javax.mail.Session;

import javax.mail.Store;
import javax.mail.internet.MimeMessage;

public class get {
    public static String recipientAddress = "103654741@qq.com";
    public static String recipientAccout = "103654741@qq.com";
    public static String recipientPassword = "zwdeugruuhmdbjbi";
    public StringBuffer bodytext = new StringBuffer();
    public static void main(String [] args)throws  Exception
    {
        Properties props = new Properties();
        props.setProperty("mail.store.port","995");
        props.setProperty("mail.pop3.host","pop.qq.com");
        props.setProperty("mail.pop3.socketFactory.class","javax.netssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback","true");
        props.setProperty("mail.pop3.socketFactory.pory","995");

        Session session = Session.getDefaultInstance(props,null);
        Store store = session.getStore("pop3");
        store.connect("pop.qq.com",recipientAccout,recipientPassword);

        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_ONLY);


        Message []messages = folder.getMessages();
        System.out.println("邮件数量" +messages.length);


        for (int i=0;i<messages.length;i++)
        {
            //System.out.println(messages[i].getFrom());
            //System.out.println(messages[i].getSubject());
            fun(messages[i]);
        }

        folder.close(true);
        store.close();
    }

    public static void fun(Message message) throws Exception
    {
        MailImfo re = new MailImfo();
        re.setMimeMessage((MimeMessage) message);
        re.getFrom();
        //re.getMailContent((Part)message);
        //System.out.println(re.getBodyText());
    }

}
