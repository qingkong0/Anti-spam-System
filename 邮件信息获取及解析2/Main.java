import javax.mail.Flags;

public class Main {
    public static void main(String []args)throws Exception
    {
        String recipientAccout = "103654741@qq.com";
        String recipientPassword = "zwdeugruuhmdbjbi";
        Connect connect=new Connect();
        connect.setConnect(recipientAccout,recipientPassword);
        //connect.getConnect();//POP3协议
        connect.getConnectbyImap();//IMAP协议

        Meil []meils = new Meil[600];
        for(int i=0;i<connect.messages.length;i++)
        {
            Meil meil = new Meil(connect.messages[i],i);
            meils[i] = meil;
        }
        MeilMessage meilMessage = new MeilMessage();

        System.out.println(connect.messages.length);

        for(int i=0;i<connect.messages.length;i++)
        {
            meils[i].outMessage(meilMessage);
        }

        //connect.deleteMail(connect.messages.length-1);
        //connect.cancelDeleteMail(0);
        connect.closeConnect();
    }
}
