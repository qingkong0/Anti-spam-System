
public class test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		/*Dictionary a=new Dictionary("D:\\360安全浏览器下载\\www.NewXing.com\\百度分词词库.txt");
		email e=new email("C:\\Users\\Administrator\\Desktop\\大二下\\实训\\a.txt",a);
		System.out.println(e.title);
		System.out.println(e.sender);
		System.out.println(e.date);
		System.out.println(e.receiever);
		System.out.println(e.content);
		for (String key : e.wordTable.keySet()) {
			if(e.wordTable.get(key)>0)
              System.out.println(key+":"+e.wordTable.get(key));
        }*/
		/*Practice p=new Practice();
		p.p1();
		p.p2();
		System.out.println("垃圾邮件概率:");
		for (String key : p.t1.keySet()) {
			if(p.t1.get(key)>0.1)
              System.out.println(key+":"+p.t1.get(key));
        }
		System.out.println("正常邮件概率:");
		for (String key : p.t2.keySet()) {
			if(p.t2.get(key)>0.1)
              System.out.println(key+":"+p.t2.get(key));
        }*/
		/*String s1="E:\\eclipse-workspace\\anti-sample\\训练词典\\垃圾邮件词典.txt";
		String s2="E:\\eclipse-workspace\\anti-sample\\训练词典\\正常邮件词典.txt";
		emailHashMap ehm=new emailHashMap(s1,s2);
		System.out.println("垃圾邮件:");
		for (String key : ehm.eh1.keySet()) {
			if(ehm.eh1.get(key)>0.0001)
              System.out.println(key+":"+ehm.eh1.get(key));
        }
		System.out.println("正常邮件:");
		for (String key : ehm.eh2.keySet()) {
			if(ehm.eh2.get(key)>0.0001)
              System.out.println(key+":"+ehm.eh2.get(key));
        }*/
		new LoginEmail();
	}
}
