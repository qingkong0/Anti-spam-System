

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.UnsupportedLookAndFeelException;

public class MailboxSettingFrame extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	static public int[] select= new int[2];
	class closeWin extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Window w=e.getWindow();
			w.dispose();
		}
	}
	public MailboxSettingFrame() {
		this.setTitle("邮箱设置");
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		addWindowListener(new closeWin());
		setBounds(100, 100, 740, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel label_4 = new JLabel("邮件过滤提示");
		label_4.setFont(new Font("宋体", Font.BOLD, 24));
		if(select[1]==1)
		{
			radioButton_1 = new JRadioButton("关闭");
			radioButton_2 = new JRadioButton("启用",true);
		}
		else
		{
			radioButton_1 = new JRadioButton("关闭",true);
			radioButton_2 = new JRadioButton("启用");
		}
		radioButton_1.setFocusPainted(false);
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_6 = new JLabel("(不弹任何提示)");
		label_6.setForeground(Color.GRAY);
		label_6.setFont(new Font("宋体", Font.PLAIN, 18));
		
		
		radioButton_2.setFocusPainted(false);
		radioButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		ButtonGroup buttonGroup2=new ButtonGroup();
		buttonGroup2.add(radioButton_1);
		buttonGroup2.add(radioButton_2);
		
		radioButton_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(radioButton_1.isSelected())
				{
					radioButton_1.setSelected(true);
					//�޸Ķ�Ӧboolean��ֵ
				}
			}
		});
		
		radioButton_2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(radioButton_2.isSelected())
				{
					radioButton_2.setSelected(true);
					//�޸Ķ�Ӧboolean��ֵ
				}
			}
		});
		
		
		JLabel label_7 = new JLabel("(有发给我的邮件被过滤时提示)");
		label_7.setForeground(Color.GRAY);
		label_7.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(36)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(199)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(radioButton_1)
								.addComponent(radioButton_2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(2)
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_6)))))
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton_1)
						.addComponent(label_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_7)
						.addComponent(radioButton_2))
					.addGap(18))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 722, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 722, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 721, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 721, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 719, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton_1 = new JButton("保存更改");
		btnNewButton_1.setFocusPainted(false);
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(radioButton_1.isSelected())
				{
					select[1]=0;
				}
				else {
					select[1]=1;
				}
				if(rdbtnNewRadioButton.isSelected())
				{
					select[0]=0;
				}
				else {
					select[0]=1;
				}
				changeFile();
				JOptionPane.showMessageDialog(null, "保存成功", "系统提示", JOptionPane.NO_OPTION);
			}
		});
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_2)
					.addContainerGap(467, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel label = new JLabel("反垃圾选项");
		label.setFont(new Font("宋体", Font.BOLD, 24));
		
		JLabel lblNewLabel_2 = new JLabel("垃圾邮件处理:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		if(select[0]==0)
		{
			rdbtnNewRadioButton = new JRadioButton("接收",true);
			radioButton = new JRadioButton("拒绝");
		}
		else
		{
			rdbtnNewRadioButton = new JRadioButton("接收");
			radioButton = new JRadioButton("拒绝",true);
		}
		//��ʼ��
		rdbtnNewRadioButton.setFocusPainted(false);
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 18));
		
				
		radioButton.setFocusPainted(false);
		radioButton.setFont(new Font("宋体", Font.PLAIN, 18));
		ButtonGroup buttonGroup1=new ButtonGroup();
		buttonGroup1.add(rdbtnNewRadioButton);
		buttonGroup1.add(radioButton);
		
		rdbtnNewRadioButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(rdbtnNewRadioButton.isSelected())
				{
					rdbtnNewRadioButton.setSelected(true);
					//�޸Ķ�Ӧboolean��ֵ
				}
			}
		});
		
		radioButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(radioButton.isSelected())
				{
					radioButton.setSelected(true);
					//�޸Ķ�Ӧboolean��ֵ
				}
			}
		});
		
		
		JLabel lblNewLabel_3 = new JLabel("(临时存放在垃圾箱)");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("(直接删除，不放入垃圾箱)");
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(40)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(59)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnNewRadioButton)
								.addComponent(radioButton, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(181, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton)
						.addComponent(label_3))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label_1 = new JLabel("\u767D\u540D\u5355");
		label_1.setFont(new Font("宋体", Font.BOLD, 24));
		
		JButton button_1 = new JButton("设置邮件地址白名单");
		button_1.setFocusPainted(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BlackAndWhiteList frame=new BlackAndWhiteList("白");
//				frame.show();
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel label_2 = new JLabel("确保你能正确收到这些地址或域名的邮件");
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(41)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(54)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(button_1)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label_2)
					.addGap(8))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("\u9ED1\u540D\u5355");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 24));
		
		JButton btnNewButton = new JButton("设置邮件地址黑名单");
		btnNewButton.setFocusPainted(false);
		
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BlackAndWhiteList frame2=new BlackAndWhiteList("黑");
//						frame.show();
					}
				});
				btnNewButton.setFont(new Font("宋体", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("(如果你不希望收到某人的邮件，或者某个域名的邮件)");
		
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(43)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(171)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(75, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		this.setResizable(false); 
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.gray);
		g.drawLine(0, 83, 1000,83);
		g.drawLine(0, 193, 1000,193);
		g.drawLine(0, 298, 1000,298);
		g.drawLine(0, 414, 1000,414);
	}
	static public void initFromFile() {// ͨ���ļ���ʼ�����õ�ѡ��ڰ�����������
		try {
			BufferedReader in = new BufferedReader(new FileReader(LoginEmail.EmailUserName+"\\黑白名单\\过滤设置.txt"));
			String s;
			int i = 0;
			while ((s = in.readLine()) != null) {
				switch (s) {
				case "0":
					select[i] = 0;
					break;
				case "1":
					select[i] = 1;
					break;
				}
				i++;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void changeFile() {//�û������������棬�޸������ļ�
		try {
			String line = System.getProperty("line.separator");
			StringBuffer str = new StringBuffer();
			File file = new File(LoginEmail.EmailUserName+"\\黑白名单\\过滤设置.txt");
			if (file.exists()) {
				file.delete();
			}
			FileWriter fw;
			fw = new FileWriter(LoginEmail.EmailUserName+"\\黑白名单\\过滤设置.txt", true);
			for (int i=0;i<select.length;i++) {
				str.append(select[i]).append(line);
			}
			fw.write(str.toString());
			fw.close();
		} catch (

		IOException e) {
			System.out.println("Error: " + e);
		}
	}
}
