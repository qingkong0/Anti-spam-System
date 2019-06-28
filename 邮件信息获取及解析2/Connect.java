import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Folder;
import javax.mail.search.FlagTerm;
import javax.mail.Flags;
public class Connect {
    private String recipientAccout;
    private String recipientPassword;
    private Folder folder;
    private Folder junkFolder;
    private Store store;
    private Properties props;
    public Message messages[];
    public Message junkmessages[];

    public Connect(){}

    public Connect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }

    public void setConnect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }

    public void getConnect()throws Exception//POP3协议
    {
        props = new Properties();
        props.setProperty("mail.store.port","995");
        props.setProperty("mail.pop3.host","pop.qq.com");
        props.setProperty("mail.pop3.socketFactory.class","javax.netssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback","true");
        props.setProperty("mail.pop3.socketFactory.pory","995");
        Session session = Session.getDefaultInstance(props,null);
        store = session.getStore("pop3");
        store.connect("pop.qq.com",recipientAccout,recipientPassword);
        folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        messages=folder.getMessages();
    }

    public void getConnectbyImap()throws Exception
    {
        props = new Properties();
        props.setProperty("mail.store.port","993");
        props.setProperty("mail.imap.host","imap.qq.com");
        props.setProperty("mail.imap.socketFactory.class","javax.netssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback","true");
        props.setProperty("mail.imap.socketFactory.pory","993");
        Session session = Session.getDefaultInstance(props,null);
        store = session.getStore("imap");
        store.connect("imap.qq.com",recipientAccout,recipientPassword);


        folder = store.getFolder("inbox");
        junkFolder = store.getFolder("JUNK");

        folder.open(Folder.READ_WRITE);
        junkFolder.open(Folder.READ_WRITE);

        junkmessages=junkFolder.getMessages();
        messages=folder.getMessages();
    }

    public void deleteMail(int mailid)throws Exception //根据mailid把邮件移动到垃圾箱
    {
        Message deMessages[] = new Message[1];
        deMessages[0] = messages[mailid];
        folder.copyMessages(deMessages, junkFolder);//复制到新文件夹
        messages[mailid].setFlag(Flags.Flag.DELETED,true);
    }

    public void cancelDeleteMail(int mailid) throws Exception
    {
        Message deMessages[] = new Message[1];
        deMessages[0]=junkmessages[mailid];
        junkFolder.copyMessages(deMessages,folder);
        junkmessages[mailid].setFlag(Flags.Flag.DELETED,true);
    }

    public void closeConnect()throws Exception
    {
        junkFolder.close(true);
        folder.close(true);
        store.close();
    }
}
