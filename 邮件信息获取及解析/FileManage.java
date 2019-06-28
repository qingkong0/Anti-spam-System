import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileManage {

    public void autoNewFile(String receiver,int id,String path,String txt)throws Exception
    {
        String filenames = creatMkdir(path,receiver);
        String meilname = receiver + id + ".txt";
        creatFile(filenames,meilname,txt);
    }

    public String creatMkdir(String path,String name) //创建文件夹
    {
        String filePN = path +"\\" + name;
        File file = new File(filePN);
        if(!file.exists())
        {
            file.mkdir();
        }
        return filePN;
    }

    public String creatFile(String path,String name,String txt)throws Exception
    {
        FileWriter fw =null;
        String filePN = path +"\\" + name;
        File file = new File(filePN);
        if(!file.exists())
        {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(txt,0,txt.length()-1);
        out.close();
        return filePN;
    }

}
