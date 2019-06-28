import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

public class Meil {
    public Message message;
    private int meilID;

    public Meil(Message message,int meilID)
    {
        this.message=message;
        this.meilID=meilID;
    }

    public void outMessage(MeilMessage meilMessage)throws Exception
    {
        meilMessage.setMimeMessage((MimeMessage)message);
        meilMessage.getMailContent((Part)message);

        StringBuffer sb = new StringBuffer();

        sb.append(meilMessage.getSentDate());
        sb.append("\r\n");
        sb.append(meilMessage.getFrom());
        sb.append("\r\n");
        sb.append(meilMessage.getSubject());
        sb.append("\r\n");

        String html = meilMessage.getBodyText();
        AnalysisHtml analysisHtml = new AnalysisHtml();

        sb.append(analysisHtml.Html2Text(html));
        //System.out.println(sb);
        //String txt = sb.toString();
        //txt.replace("&nbsp","");

        FileManage fileManage = new FileManage();
        fileManage.autoNewFile("黄俊毅",meilID,"C:\\Users\\10365\\Desktop\\2019实训\\LoginMail",sb.toString());

    }


}
