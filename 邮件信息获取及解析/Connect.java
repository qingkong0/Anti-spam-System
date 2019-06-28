import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class Connect {
    private String recipientAccout;
    private String recipientPassword;
    private Folder folder;
    private Store store;
    private Properties props;
    public Message messages[];

    public Connect(){}

    public Connect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }

    public void setConnect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }

    public void getConnect()throws Exception
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

    public void closeConnect()throws Exception
    {
        folder.close(true);
        store.close();
    }
}
