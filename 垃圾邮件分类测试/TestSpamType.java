package mysystem;

import java.util.HashMap;

//���⼸���ʵ�Ĵʼӵ���ʵ�������ѵ��
public class TestSpamType {
	Dictionary violence = new Dictionary("D:\\���������ʿ�\\���ִʿ�.txt");
	Dictionary reactionary = new Dictionary("D:\\���������ʿ�\\�����ʿ�.txt");
	Dictionary livelihood = new Dictionary("D:\\���������ʿ�\\�����ʿ�.txt");
	Dictionary pornographic = new Dictionary("D:\\���������ʿ�\\ɫ��ʿ�.txt");
	Dictionary corrupted = new Dictionary("D:\\���������ʿ�\\̰���ʿ�.txt");
	Dictionary others = new Dictionary("D:\\���������ʿ�\\�����ʿ�.txt");
	int v, r, l, p, c, o;

	public char judge(email spam) {
		v = r = l = p = c = o = 0;
		for (String key : spam.wordTable.keySet()) {
			//if(key.matches("����")) {
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
		Dictionary dic = new Dictionary("D:\\�ٶȷִʴʿ�.txt");
		email mySpam = new email("D:\\���������ʿ�\\����spam.txt", dic);
		// System.out.println(mySpam.wordTable);
		TestSpamType a = new TestSpamType();
		a.judge(mySpam);
	}

}
