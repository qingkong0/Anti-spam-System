import javax.mail.internet.MimeMessage;

public class AnalysisMail extends Thread{
    public Connect connect;
    
    public MeilMessage meilMessage;
    public int id;
    
    public AnalysisMail(int i)
    {
        id = i;
    }
    public void run()
    {
        try {
            connect =new Connect();
            connect.Conn(LoginEmail.EmailUserName, LoginEmail.EmailUserLiscen);
            Analysis();
            //System.out.println("thread");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Analysis()throws Exception
    {
        Meil meil = new Meil(connect,connect.messages[id],id);
        MeilMessage meilMessage=new MeilMessage((MimeMessage)meil.message);
        meil.outMessage(meilMessage,id);
        ProgressBarDemo.FinishFile++;
        connect.closeConnect();
    }
}
