import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;

public class AnalyFrame extends JFrame {

	private JPanel contentPane;
	public JTextArea textArea;
	public  String title="";
    public String sender="";
    public String sendTime="";
    public String receiver="";
    public StringBuffer content;
    public boolean isSpamEmail=false;
   
class closeWin extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				Window w=e.getWindow();
				w.dispose();
			}
		}
	public AnalyFrame(String filePath) {
		setTitle("邮件详细信息");
		addWindowListener(new closeWin());
		setVisible(true);
		content=new StringBuffer("");
		setBounds(100, 100, 827, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			open(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
JLabel lblNewLabel_1 = new JLabel("\u53D1\u4EF6\u4EBA\uFF1A");
lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2 = new JLabel("\u65F6\u95F4\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3 = new JLabel("\u5185  \u5BB9\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_4 = new JLabel("\u6807\u9898\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.BOLD, 22));
		textArea = new JTextArea();
		
		textArea.setFont(new Font("宋体", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(textArea);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(6)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 661, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_4))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_4)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		lblNewLabel_4.setText("\u6807 \u9898\uFF1A"+title);
		lblNewLabel_1.setText("发件人："+sender);
		lblNewLabel_2.setText("\u65F6  \u95F4\uFF1A"+sendTime);
		
		textArea.setLineWrap(true);
		textArea.setText(String.valueOf(content));
		textArea.setEditable(false);
	}
	
	
	public void open(String filePath) throws IOException  {
		
		 BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(filePath));
			String str = null;
	           int row=0;
	           while((str = in.readLine()) != null) {
	               switch(row)
	               {
	               case 0:sendTime=str; row++;break;
	               case 1:sender=str;row++;break;
	               case 2:title=str;row++;break;
	               default:
	               	{

	               		content.append(str);
	               	}
	               	
	               }
	           }
	           in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
        
    }
}
