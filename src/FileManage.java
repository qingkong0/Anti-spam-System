import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManage {

    static public String creatMkdir(String name) //�����ļ���
    {
        File file = new File(name);
        if(!file.exists())
        {
            file.mkdir();
        }
        return name;
    }

    static public String creatFile(String path,String name,String txt)throws Exception
    {
        FileWriter fw =null;
        String filePN = path +"\\" + name+".html";
        File file = new File(filePN);
        if(!file.exists())
        {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(txt,0,txt.length());
        out.close();
        return filePN;
    }
    static public String creatFile(String path,String txt)
    {
    	String filePN = path+".txt";
    	try {
			FileWriter fw =null;
        
        File file = new File(filePN);
        if(!file.exists())
        {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(txt,0,txt.length());
        out.close();
        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return filePN;
    }
    public static String createUserMkdir(String reciever)throws Exception
    {
        creatMkdir(reciever);
        String inFile = reciever;
        creatMkdir(reciever+"\\邮件箱");
        creatMkdir(reciever+"\\垃圾箱");
        creatMkdir(reciever+"\\黑白名单");
        File file1 = new File(reciever+"\\黑白名单\\黑名单.txt");
        if(!file1.exists())
        {
            file1.createNewFile();
        }
        File file2 = new File(reciever+"\\黑白名单\\白名单.txt");
        if(!file2.exists())
        {
            file2.createNewFile();
        }
        File file3 = new File(reciever+"\\黑白名单\\过滤设置.txt");
        if(!file3.exists())
        {
            file3.createNewFile();
            try {
			String line = System.getProperty("line.separator");
			StringBuffer str = new StringBuffer();
			FileWriter fw;
			fw = new FileWriter(file3.getPath(), true);
			str.append(0).append(line);
			str.append(1).append(line);
			fw.write(str.toString());
			fw.close();
			} catch (IOException e) {
			System.out.println("Error: " + e);
			}
        }       
        return inFile;
    }

}