package mysystem;

import java.util.*;
import java.io.*;

public class Dictionary
{
        HashMap<String, Double> hm;		//a word set

        public Dictionary()
        {
                hm = new HashMap<>();
        }

        public Dictionary(String fileName)
        {
                hm = new HashMap<>();
                Load(fileName);
        }

        public void Load(String fileName)    //×°ÔØººÓï×Öµä
        {
                try
                {
                        @SuppressWarnings("resource")
						BufferedReader in=
                                new BufferedReader(
                                        new FileReader(fileName) );

                        String s;
                        String []words;
                        while((s = in.readLine()) != null)
                        {
                                words = s.split(":");
                                hm.put(words[0],new Double(0.0));
                                //System.out.println(words[0]+"fen"+hm.get(words[0]));
                        }
                }
                catch(IOException e)
                {
                        System.out.println("Error: " + e);
                }
        }

        public boolean Find(String word)    //´Ó×ÖµäÀï²éÑ¯´Ê
        {
                return hm.containsKey(word);
        }


}

