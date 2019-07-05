package mysystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestVideoBack extends JFrame {

	public static void main(String[] args) {
		new TestVideoBack();
	}

	TestVideoBack() {
		this.setTitle("Background");
		this.setLayout(new FlowLayout());
		//JButton jb = new JButton("测试");
		//this.add(jb);

		// 放置背景图
		ImageIcon background = new ImageIcon("D:\\动态视频\\青空4.gif");
		JLabel bkLabel = new JLabel(background);
		bkLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		//bkLabel.action(null, what);
		//this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setSize(1270, 700);
		this.getLayeredPane().add(bkLabel, new Integer(Integer.MIN_VALUE));
		JPanel ctPanel = (JPanel) this.getContentPane();
		ctPanel.setOpaque(false);

		// 设置按钮样式
//		jb.setPreferredSize(new Dimension(100, 40));// 设置大小
//		jb.setBackground(new Color(118, 238, 0));// 设置背景色
//		jb.setForeground(Color.WHITE);// 设置前景色
//		jb.setFont(new java.awt.Font("微软楷体", 1, 20)); // 设置字体样式

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

}
