package mysystem;

import java.io.File;
import javafx.scene.media.AudioClip;

public class MusicPlay {//为窗口添加BGM
	public static void playMusic(String filePath) 
    {
        AudioClip ac;
        ac = new AudioClip(new File(filePath).toURI().toString());
        ac.play();   //开始播放
        //ac.setCycleCount(1000);  //设置循环次数
    }
	
	public static void main(String[] args) {
		MusicPlay my = new MusicPlay();
		my.playMusic("D:\\SWIN-S - 只因你太美.mp3");
		while(true) {
			System.out.println(1);
		}
	}
}
