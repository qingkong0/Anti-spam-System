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
		//JButton jb = new JButton("����");
		//this.add(jb);

		// ���ñ���ͼ
		ImageIcon background = new ImageIcon("D:\\��̬��Ƶ\\���4.gif");
		JLabel bkLabel = new JLabel(background);
		bkLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		//bkLabel.action(null, what);
		//this.setSize(background.getIconWidth(), background.getIconHeight());
		this.setSize(1270, 700);
		this.getLayeredPane().add(bkLabel, new Integer(Integer.MIN_VALUE));
		JPanel ctPanel = (JPanel) this.getContentPane();
		ctPanel.setOpaque(false);

		// ���ð�ť��ʽ
//		jb.setPreferredSize(new Dimension(100, 40));// ���ô�С
//		jb.setBackground(new Color(118, 238, 0));// ���ñ���ɫ
//		jb.setForeground(Color.WHITE);// ����ǰ��ɫ
//		jb.setFont(new java.awt.Font("΢����", 1, 20)); // ����������ʽ

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}

}
