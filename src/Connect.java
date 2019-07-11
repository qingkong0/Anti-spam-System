import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Folder;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;
import javax.mail.Flags;
public class Connect {
    private String recipientAccout;
    private String recipientPassword;
    private Folder folder;
    private Folder junkFolder;
    private Store store;
    private Properties props;
    public Message messages[];
    public String port;
    public Connect(){}

    public int Conn(String recipientAccout,String recipientPassword) {
		try {
			int n=LoginEmail.portKind;
			setConnect(recipientAccout, recipientPassword);
			if(n==0)
			{
				setPortImap();//正常
				getConnectbyImap();
			}
			else {
				setPortPop();//附件
				getConnectbyPop();
			}
			//System.out.println(port);			
			return 1;
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��/
			e.printStackTrace();
			if(e.getMessage().equals("imap.qq.com"))
			{
				JOptionPane.showMessageDialog(null, "连接超时请检查你的网络", "登录错误", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "你的账户或者授权码输入错误", "登录错误", JOptionPane.ERROR_MESSAGE);
			}
			return 0;
		}
	}
    public Connect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }

    public void setConnect(String recipientAccout,String recipientPassword)
    {
        this.recipientAccout=recipientAccout;this.recipientPassword=recipientPassword;
    }
    public void setPortPop()
    {
        this.port = "pop." + LoginEmail.port;
    }

    public void setPortImap()
    {
        this.port = "imap." + LoginEmail.port;
    }
    public void getConnectbyPop()throws Exception//POP3Э��
    {
        props = new Properties();
        props.setProperty("mail.store.port","995");
        props.setProperty("mail.pop3.host",port);
        props.setProperty("mail.pop3.socketFactory.class","javax.netssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback","true");
        props.setProperty("mail.pop3.socketFactory.pory","995");
        Session session = Session.getDefaultInstance(props,null);
        store = session.getStore("pop3");
        store.connect(port,recipientAccout,recipientPassword);
        folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        messages=folder.getMessages();
    }

    public void getConnectbyImap()throws Exception
    {
        props = new Properties();
        props.setProperty("mail.store.port","993");
        props.setProperty("mail.imap.host",port);
        props.setProperty("mail.imap.socketFactory.class","javax.netssl.SSLSocketFactory");
        props.setProperty("mail.imap.socketFactory.fallback","true");
        props.setProperty("mail.imap.socketFactory.pory","993");
        Session session = Session.getDefaultInstance(props,null);
        store = session.getStore("imap");
        store.connect(port,recipientAccout,recipientPassword);


        folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);
        messages=folder.getMessages();
    }
    public void deleteMail(int mailid)throws Exception //����mailid���ʼ��ƶ���������
    {        
        messages[mailid].setFlag(Flags.Flag.DELETED,true);
    }
    public void closeConnect()throws Exception
    {
        folder.close(true);
        store.close();
    }
}
