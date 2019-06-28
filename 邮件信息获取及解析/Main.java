import javax.mail.Flags;

public class Main {
    public static void main(String []args)throws Exception
    {
        String recipientAccout = "103654741@qq.com";
        String recipientPassword = "zwdeugruuhmdbjbi";
        Connect connect=new Connect();
        connect.setConnect(recipientAccout,recipientPassword);
        connect.getConnect();

        Meil []meils = new Meil[600];
        for(int i=0;i<connect.messages.length;i++)
        {
            Meil meil = new Meil(connect.messages[i],i);
            meils[i] = meil;
        }
        MeilMessage meilMessage = new MeilMessage();

        System.out.println(connect.messages.length);
        meils[0].message.setFlag(Flags.Flag.DELETED,true);
        for(int i=0;i<connect.messages.length;i++)
        {
            //meils[i].outMessage(meilMessage);
        }

        connect.closeConnect();
    }
}
