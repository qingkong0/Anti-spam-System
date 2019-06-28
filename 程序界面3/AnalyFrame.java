package getMailnfo;

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
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

public class AnalyFrame extends JFrame {

	private JPanel contentPane;
	public JTextArea textArea;
	public  String title="";
    public String sender="";
    public String sendTime="";
    public String receiver="";
    public StringBuffer content;
    private Frame jf;
    public boolean hasChooseFile=false;
    

	/**
	 * Launch the application.
	 */
	
	
	

	/**
	 * Create the frame.
	 */
	public AnalyFrame() {
		content=new StringBuffer("");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 827, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		open();
		
		JLabel lblNewLabel = new JLabel("\u667A\u80FD\u53CD\u5783\u573E\u90AE\u4EF6\u8BC6\u522B\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
JLabel lblNewLabel_1 = new JLabel("\u53D1\u4EF6\u4EBA\uFF1A");
lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2 = new JLabel("\u65F6\u95F4\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_3 = new JLabel("\u5185\u5BB9\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JLabel lblNewLabel_4 = new JLabel("\u6807\u9898\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JLabel lblNewLabel_5 = new JLabel("\u6536\u4EF6\u4EBA\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		textArea = new JTextArea();
		
		textArea.setFont(new Font("宋体", Font.PLAIN, 18));
		textArea.setLineWrap(true);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		
		
		
		scrollPane.setViewportView(textArea);
		
		JLabel label = new JLabel("\u90AE\u4EF6\u7C7B\u578B\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(36, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(50)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(18)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_5))))
					.addGap(67))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(lblNewLabel_4)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblNewLabel_2)
							.addGap(18)
							.addComponent(lblNewLabel_5)
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addComponent(label)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		lblNewLabel_4.setText("标题："+title);
		lblNewLabel_1.setText("发件人："+sender);
		lblNewLabel_2.setText("时间："+sendTime);
		lblNewLabel_5.setText("收件人："+receiver);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setText(String.valueOf(content));
	}
	
	
	public void open()  {
    	try 
    	{
		
   		FileDialog fdopen = new FileDialog(jf);
    		fdopen.show();
    		if(fdopen.getFile()!=null)
    		{
    			hasChooseFile=true;
    			 BufferedReader in = new BufferedReader(new FileReader(fdopen.getDirectory() + fdopen.getFile()));
    	            String str = null;
    	            int row=0;
    	            while((str = in.readLine()) != null) {
    	                switch(row)
    	                {
    	                case 0:title=str; row++;break;
    	                case 1:sender=str;row++;break;
    	                case 2:sendTime=str;row++;break;
    	                case 3:receiver=str;row++;break;
    	                default:
    	                	{
    	                		if(str.startsWith("　　"))
    	                		{
    	                        	content.append("\n");
    	                        }
    	                		content.append(str);
    	                	}
    	                	
    	                }
    	            }
    	            in.close();
    		}
          
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
        
    }
}
