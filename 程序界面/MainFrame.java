package getMailnfo;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

public class MainFrame extends JFrame {
	
	private Frame jf;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					//moveFile("D:\\mail\\a.txt","D:\\Test\\");//第二个参数可以确定好
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblNewLabel = new JLabel("\u667A\u80FD\u53CD\u5783\u573E\u90AE\u4EF6\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(308, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(297))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JTextArea textArea = new JTextArea();
		
		JTextArea textArea_1 = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		
		JLabel label = new JLabel("\u5783\u573E\u90AE\u4EF6");
		
		JLabel label_1 = new JLabel("\u5408\u6CD5\u90AE\u4EF6");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(86)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
					.addComponent(label_1)
					.addGap(100))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_1))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
		);
		
		
		scrollPane_1.setViewportView(textArea_1);
		
		
		scrollPane.setViewportView(textArea);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnNewButton = new JButton("\u8BAD\u7EC3");
		
		JButton btnNewButton_1 = new JButton("\u5206\u6790\u90AE\u4EF6");
		
		
		JButton btnNewButton_2 = new JButton("\u7B5B\u9009\u90AE\u4EF6\u96C6");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileDialog fdopen = new FileDialog(jf, "打开", FileDialog.LOAD);
				fdopen.setVisible(true);
				System.out.println(fdopen.getDirectory() );//+ fdopen.getFile()
				
				File file = new File(fdopen.getDirectory());

				File[] listFiles = file.listFiles();

				for (File file2 : listFiles) {//迭代出来的都是文件的绝对路径

					System.out.println(file2);

				}
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u5237\u65B0");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(btnNewButton)
					.addGap(26)
					.addComponent(btnNewButton_1)
					.addGap(32)
					.addComponent(btnNewButton_2)
					.addGap(54)
					.addComponent(btnNewButton_3)
					.addContainerGap(162, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileOpen fp;
				try {
					fp = FileOpen.selectMail();
					AnalyFrame form=new AnalyFrame(fp);
					form.setVisible(true);
					
					

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public static void moveFile(String oldPath,String NewPath)
	{
		try {  
            File afile = new File(oldPath);  
            if (afile.renameTo(new File(NewPath + afile.getName()))) {  
                System.out.println("File is moved successful!");  
            } else {  
                System.out.println("File is failed to move!");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  

	}
}
