import java.util.*;
import java.io.*;
import java.lang.*;
public class Dictionary {
	HashMap<String,Double> hm;		//a word set
    public Dictionary()
    {
            hm = new<String,Double> HashMap();
    }
    public Dictionary(String fileName)
    {
            hm = new<String,Double> HashMap();
            Load(fileName);
    }
    public void Load(String fileName)    //◊∞‘ÿ∫∫”Ô¥ µ‰
    {
            try
            {
                    BufferedReader in=
                            new BufferedReader(
                                    new FileReader(fileName) );

                    String s;
                    while((s = in.readLine()) != null)
                    {
                            hm.put(s.substring(2, s.length()),(double) 0);
                    }
            }
            catch(IOException e)
            {
                    System.out.println("Error: " + e);
            }
    }
    public boolean Find(String word)    //¥”◊÷µ‰¿Ô≤È—Ø¥ 
    {
            return hm.containsKey(word);
    }
}
