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
        String html = meilMessage.getBodyText();
        AnalysisHtml analysisHtml = new AnalysisHtml();

        //String body =analysisHtml.StripHT(html);
        String body = analysisHtml.Html2Text(html);

        String meilTxt =meilMessage.getSentDate() + "\r\n" + meilMessage.getFrom() + "\r\n" +meilMessage.getSubject() + "\r\n" + body;
        FileManage fileManage = new FileManage();
        fileManage.autoNewFile("黄俊毅",meilID,"C:\\Users\\10365\\Desktop\\2019实训\\LoginMail",meilTxt);

    }


}
