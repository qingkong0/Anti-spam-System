import java.util.*;
import java.io.*;
public class emailHashMap {
	HashMap<String,Double> eh1;//À¬»øÓÊ¼þ´Êµä
	HashMap<String,Double> eh2;//Õý³£ÓÊ¼þ´Êµä
	 public emailHashMap(String fileName1,String fileName2)
	    {
	            eh1 = new<String,Double> HashMap();
	            eh2 = new<String,Double> HashMap();
	            try
	            {
	                    BufferedReader in1=
	                            new BufferedReader(
	                                    new FileReader(fileName1) );
	                    BufferedReader in2=
	                            new BufferedReader(
	                                    new FileReader(fileName2) );
	                    String s1;
	                    String s2;
	                    String []word1;
	                    String []word2;
	                    while((s1 = in1.readLine()) != null)
	                    {
	                    	word1=s1.split(":");	          
	                        eh1.put(word1[0],Double.parseDouble(word1[1]));
	                    }
	                    while((s2 = in2.readLine()) != null)
	                    {
	                    	word2=s2.split(":");	          
	                        eh2.put(word2[0],Double.parseDouble(word2[1]));
	                    }
	            }
	            catch(IOException e)
	            {
	                    System.out.println("Error: " + e);
	            }
	    }
}
