package mysystem;

import java.io.File;
import javafx.scene.media.AudioClip;

public class MusicPlay {//Ϊ�������BGM
	public static void playMusic(String filePath) 
    {
        AudioClip ac;
        ac = new AudioClip(new File(filePath).toURI().toString());
        ac.play();   //��ʼ����
        //ac.setCycleCount(1000);  //����ѭ������
    }
	
	public static void main(String[] args) {
		MusicPlay my = new MusicPlay();
		my.playMusic("D:\\SWIN-S - ֻ����̫��.mp3");
		while(true) {
			System.out.println(1);
		}
	}
}
