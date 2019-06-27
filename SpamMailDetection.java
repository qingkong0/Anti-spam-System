package Socket;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

public class SpamMailDetection {

	
   

    
    

    /** 
     * 给定邮件,分词,根据分词结果判断是垃圾邮件的概率  
     * P(Spam|t1,t2,t3……tn)=（P1*P2*……PN）/(P1*P2*……PN+(1-P1)*(1-P2)*……(1-PN)) 
     */  
    public double judgeMail(String emailPath, Map<String, Double> ratemap) {  
    	ArrayList<String> list = segment(readFile(emailPath),map);  //第二个参数是词典得到的map
        double rate = 1.0;  
        double tempRate = 1.0;  
        for (String str : list) {  
            if (ratemap.containsKey(str)) {  
                double tmp = ratemap.get(str);  
                tempRate *= 1 - tmp;  
                rate *= tmp;  
            }  
        }  
        return rate / (rate + tempRate);  
    }  

    /** 
     * 建立map,<str,rate> 邮件中出现ti时,该邮件为垃圾邮件的概率 
     * P( Spam|ti) =P2(ti )/((P1 (ti ) +P2 ( ti )) 
     */  
    public Map<String, Double> createSpamProbabilityMap(Map<String, Double> spammap,  
            Map<String, Double> okmap) {  
        Map<String, Double> retmap = new HashMap<String, Double>();  
        for (String key : spammap.keySet()) {     
            double rate = spammap.get(key);  
            double allRate = rate;  
            if (okmap.containsKey(key)) {  
                allRate += okmap.get(key);  
            }  
            retmap.put(key, rate / allRate);  
        }  
        return retmap;  
    }  



  

    /** 
     * 中文分词 
     */  
    public ArrayList<String> segment(String str,Map<String,Integer> map) {  
        ArrayList<String> list = new ArrayList<String>();  
        int len = str.length();  
        String term;  
        int maxSize = 6;  
        int i = 0, j = 0;  
        while (i < len) {  
            int n = i + maxSize < len ? i + maxSize : len + 1;  
            boolean findFlag = false;  
            for (j = n - 1; j > i; j--) {  
                term = str.substring(i, j);  
                if (map.containsKey(term)) {  
                    list.add(term);  
                    findFlag = true;  
                    i = j;  
                    break;  
                }  
            }  
            if (findFlag == false)  
                i = j + 1;  
        }  
        return list;  
    }  

 

    /** 
     * 读文件 
     */  
    public String readFile(String filePath) {  
        String str = "";  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(new File(filePath))));  
            String tmp = "";  
            while ((tmp = br.readLine()) != null)  
                str += tmp;  
            br.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  


	
	
}
