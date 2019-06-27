package getMailnfo;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class FileOpen {

	private Frame jf;
    private PrintStream jtext;
    public  String title="";
    public String sender="";
    public String sendTime="";
    public String receiver="";
    public StringBuffer content;
    private static FileOpen hj;

    public static FileOpen selectMail() throws Exception {
    	
        hj = new FileOpen();
        hj.content=new StringBuffer("");
        hj.open();
        return hj;
        

    }

    

    public void open() throws Exception {
        FileDialog fdopen = new FileDialog(jf, "´ò¿ª", FileDialog.LOAD);

        fdopen.setVisible(true);

        BufferedReader in = new BufferedReader(new FileReader(fdopen.getDirectory() + fdopen.getFile()));

        String str = null;
        int row=0;
        while((str = in.readLine()) != null) {
            switch(row)
            {
            case 0:title=str; row++;break;
            case 1:sender=str;row++;break;
            case 2:sendTime=str;row++;break;
            case 3:receiver=str;row++;break;
            default:
            	{
            		if(str.startsWith("¡¡¡¡"))
            		{
                    	content.append("\n");
                    }
            		content.append(str);
            	}
            	
            }
        }
        in.close();
    }
	
}
