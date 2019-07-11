

import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

public class NewEmailFrame extends JFrame {

	private JPanel contentPane;
	public String EmailTitle;
	public String EmailFrom;
	public String EmailContent="点击查看邮件正文";
	String AlarmMessage1="青空智能反垃圾邮件过滤系统";
	String AlarmMessage2="已为你拦截并删除一封垃圾邮件";
	public Thread myThread;
	JButton btnNewButton;
	JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	class closeWin extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Window w=e.getWindow();
			w.dispose();
		}
	}//
	public NewEmailFrame(Meil meil,int n) {
		myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                init(meil,n);
            }
        });
		myThread.start();
		
	}
	
	public void init(Meil meil,int n)
	{
		EmailTitle=meil.title;
		EmailFrom=meil.from;
		this.setTitle(LoginEmail.EmailUserName);
		addWindowListener(new closeWin());
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screensize.getWidth();
        int height = (int)screensize.getHeight();
        int FrameWidth=this.getWidth();
        int FrameHeight=this.getHeight();
		setBounds(width-450 ,height-300, 464, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnNewButton_1 = new JButton("删除邮件");
		if(n==0)
		{
			btnNewButton = new JButton("<html><p>"+EmailTitle+"</p><br/><p>"+EmailFrom+"</p><br/><p>"+EmailContent+"</p></html>");
			lblNewLabel_1 = new JLabel("");
		}
		else if(n==1)
		{
			EmailTitle+="(垃圾邮件)";
			btnNewButton = new JButton("<html><p>"+EmailTitle+"</p><br/><p>"+EmailFrom+"</p><br/><p>"+EmailContent+"</p></html>");
			lblNewLabel_1 = new JLabel("");
		}
		else
		{
			lblNewLabel_1 = new JLabel("<html><p>"+AlarmMessage1+"</p><br/><p>"+AlarmMessage2+"</p></html>");
			lblNewLabel_1.setSize(262, 115);
			lblNewLabel_1.setFont(new Font("宋体", Font.ITALIC, 18));
			btnNewButton = new JButton("");
			btnNewButton.setVisible(false);
			btnNewButton_1.setVisible(false);
		}		
		btnNewButton.setFont(new Font("宋体", Font.ITALIC, 18));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(n==0)
					{
						Desktop.getDesktop().open(new File(Practice.email2Path[meil.meilID]));
					}
					else if(n==1)
					{
						Desktop.getDesktop().open(new File(Practice.email1Path[meil.meilID]));
					}				
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		
				
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MainFrame.mw.exit=true;
						dispose();
						try 
						{
							if(n==0)
							{
								for(int i=MainFrame.tableModel.getRowCount()-1;i>=0;i--)
								{
									if(meil.meilID==Practice.email2Num[i])
									{
										MainFrame.tableModel.removeRow(i);
										break;
									}
								}	
								meil.connect.deleteMail(meil.meilID);
								MainFrame.deleteFile(Practice.email2Path[meil.meilID]);								
							}
							else if(n==1)
							{
								for(int i=TrashCanFrame.tableModel.getRowCount()-1;i>=0;i--)
								{
									if(meil.meilID==Practice.email1Num[i])
									{
										TrashCanFrame.tableModel.removeRow(i);
										break;
									}
								}	
								meil.connect.deleteMail(meil.meilID);
								MainFrame.deleteFile(Practice.email1Path[meil.meilID]);
								
							}
							meil.connect.closeConnect();
							MainFrame.changeFileId(meil.meilID);
							MainFrame.mw.exit=false;
						} catch ( Exception e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					}
				});
				btnNewButton_1.setBorderPainted(false);
				btnNewButton_1.setFocusPainted(false);
				btnNewButton_1.setBackground(Color.white);
		
		
		ImageIcon icon = new ImageIcon("image\\邮件标志.png");
		icon.setImage(icon.getImage().getScaledInstance(100,75,Image.SCALE_DEFAULT));
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(icon);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 50));
		
		btnNewButton_2 = new JButton("确定");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 125, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
							.addComponent(btnNewButton_2)))
					.addGap(49))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton))))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		this.setResizable(false);
		setUndecorated(true);
		setVisible(true);
		try {
			Thread.sleep(5000);
			for(int i=0;i<16;i++)
			{
				AWTUtilities.setWindowOpacity(this, (float) (0.9-0.05*i));
				Thread.sleep(100);
			}			
			this.dispose();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.gray);
		g.drawLine(0, 185, 400,185);
		
	}

}
