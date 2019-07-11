import java.util.*;
import java.io.*;
public class Judge {
	/** 
     * 建立map,<str,rate> 邮件中出现ti时,该邮件为垃圾邮件的概率 
     * P( Spam|ti) =P2(ti )/((P1 (ti ) +P2 ( ti )) 
     */
	static public Map<String, Double> createSpamProbabilityMap(Map<String, Double> spammap,  
            Map<String, Double> okmap) {  
        Map<String, Double> ratemap = new HashMap<String, Double>();  
        for (String key : spammap.keySet()) {     
            double rate = spammap.get(key);  
            double allRate = rate;  
            if (okmap.containsKey(key)) {  
                allRate += okmap.get(key);  
            }  
            ratemap.put(key, rate / allRate);  
        }  
        return ratemap;  
    }  
	 /** 
     * 给定邮件,分词,根据分词结果判断是垃圾邮件的概率  
     * P(Spam|t1,t2,t3……tn)=（P1*P2*……PN）/(P1*P2*……PN+(1-P1)*(1-P2)*……(1-PN)) 
     */  
	static public double judgeMail(ArrayList<String> list, Map<String, Double> ratemap) {  
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
	static public ArrayList<String> mailWordList(Email email)
	{
		ArrayList<String> list = new ArrayList<String>(15);
		String[] words = new String[3000];
		Double[] wordss = new Double[3000];
		int n=0;
		for (String key : email.wordTable.keySet()) {
			if(email.wordTable.get(key)>0)
			{
				words[n]=key;
				wordss[n]=email.wordTable.get(key);
				n++;
			}
        }	
		for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++)
				if(wordss[i]<wordss[j])
				{
					double dtemp=wordss[i];
					String stemp=words[i];
					words[i]=words[j];
					wordss[i]=wordss[j];
					words[j]=stemp;
					wordss[j]=dtemp;
				}
		for(int i=0;i<((n<15)?n:15);i++)
			list.add(words[i]);
		return list;
	}
}
