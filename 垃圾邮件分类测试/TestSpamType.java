package mysystem;

import java.util.HashMap;

//将这几个词典的词加到大词典中重新训练
public class TestSpamType {
	Dictionary violence = new Dictionary("D:\\各类垃圾词库\\暴恐词库.txt");
	Dictionary reactionary = new Dictionary("D:\\各类垃圾词库\\反动词库.txt");
	Dictionary livelihood = new Dictionary("D:\\各类垃圾词库\\民生词库.txt");
	Dictionary pornographic = new Dictionary("D:\\各类垃圾词库\\色情词库.txt");
	Dictionary corrupted = new Dictionary("D:\\各类垃圾词库\\贪腐词库.txt");
	Dictionary others = new Dictionary("D:\\各类垃圾词库\\其他词库.txt");
	int v, r, l, p, c, o;

	public char judge(email spam) {
		v = r = l = p = c = o = 0;
		for (String key : spam.wordTable.keySet()) {
			//if(key.matches("西藏")) {
			//System.out.println(key+spam.wordTable.get(key));}
			if(spam.wordTable.get(key)>0.0) {
				while (violence.Find(key)) {
					v++;
					break;
				}
				while (reactionary.Find(key)) {
					r++;
					break;
				}
				while (livelihood.Find(key)) {
					l++;
					break;
				}
				while (pornographic.Find(key)) {
					p++;
					break;
				}
				while (corrupted.Find(key)) {
					c++;
					break;
				}
				while (others.Find(key)) {
					System.out.println(key);
					o++;
					break;
				}
			}
		}
		System.out.println(v);
		System.out.println(r);
		System.out.println(l);
		System.out.println(p);
		System.out.println(c);
		System.out.println(o);
		int max = Math.max(v, Math.max(r, Math.max(l, Math.max(p, Math.max(c, o)))));
		while (max == v)
			return 'v';
		while (max == r)
			return 'r';
		while (max == l)
			return 'l';
		while (max == p)
			return 'p';
		while (max == c)
			return 'c';
		while (max == o)
			return 'o';
		return ' ';
	}

	public static void main(String[] args) {
		Dictionary dic = new Dictionary("D:\\百度分词词库.txt");
		email mySpam = new email("D:\\各类垃圾词库\\测试spam.txt", dic);
		// System.out.println(mySpam.wordTable);
		TestSpamType a = new TestSpamType();
		a.judge(mySpam);
	}

}
