import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JTable;

public class MainFrame extends JFrame {
	
	class closeWin extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Window w=e.getWindow();
			w.dispose();
		}
	}
	private JPanel contentPane;
	static public JTable table;
    static public DefaultTableModel tableModel;
    static public MailWarming mw;
    public static Object [][] infor;
    public static int hasTrashCanFrame;
    TrashCanFrame frame;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainFrame(String name,Connect conn) {
		hasTrashCanFrame=0;
		mw=new MailWarming();
		setTitle("欢迎"+name);
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("\u667A\u80FD\u53CD\u5783\u573E\u90AE\u4EF6\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton_3 = new JButton("设置");
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBackground(Color.white);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MailboxSettingFrame();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(324, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(159)
					.addComponent(btnNewButton_3)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNewLabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnNewButton_3))
					.addContainerGap())
		);
		
		table = new JTable();
		table .getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

		
		JButton btnNewButton_1 = new JButton("打开邮件");
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.exit=true;
				int count=0;
				for(int i=0;i<Practice.e2;i++)
				{
					if((boolean)tableModel.getValueAt(i, 0) )
					{
						count++;
						break;
					}					
				}
				if(count>0)
				{
					//Practice.division();
					for(int i=0;i<Practice.e2;i++)
					{
						if((boolean) tableModel.getValueAt(i, 0))
						{	
							try {
								Desktop.getDesktop().open(new File(Practice.email2Path[Practice.email2Num[i]]));
							} catch (IOException e1) {
								// TODO �Զ����ɵ� catch ��
								e1.printStackTrace();
							}
							//AnalyFrame af = new AnalyFrame(Practice.email2Path[i]);
							tableModel.setValueAt(false, i, 0);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "请至少选择一个邮件", "", JOptionPane.ERROR_MESSAGE);
				}
				mw.exit=false;
			}
		});
		
		JButton btnNewButton_2 = new JButton("放入垃圾箱");
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.exit=true;
				int count=0;
				for(int i=0;i<Practice.e2;i++)
				{
					if((boolean)tableModel.getValueAt(i, 0) )
					{
						count++;
						break;
					}					
				}
				if(count>0)
				{
					//Practice.division();
					for(int i=Practice.e2-1;i>=0;i--)
					{
						if((boolean) tableModel.getValueAt(i, 0))	
						{
							String a=Practice.email2Path[Practice.email2Num[i]];
							String b=LoginEmail.EmailUserName+"\\垃圾箱";
							tableModel.removeRow(i);
							//System.out.println(1);
							moveFile(a,b);
							//System.out.println(2);
						}
					}
					TrashCanFrame.initTable();
				}
				else {
					JOptionPane.showMessageDialog(null, "请至少选择一个邮件", "", JOptionPane.ERROR_MESSAGE);
				}
				mw.exit=false;				
			}
		});
		
		JButton button_1 = new JButton("打开垃圾箱");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hasTrashCanFrame==1)
				{
					frame.dispose();
				}
				frame=new TrashCanFrame(conn);
				frame.setVisible(true);
				hasTrashCanFrame=1;
			}
		});
		button_1.setFocusPainted(false);
		
		JButton button = new JButton("切换用户");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mw.destroy=true;
				if(hasTrashCanFrame==1)
				{
					frame.dispose();
				}
				dispose();
				new LoginEmail();
			}
		});
		button.setFocusPainted(false);
		
		JButton btnNewButton = new JButton("删除邮件");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mw.exit=true;
				conn.Conn(LoginEmail.EmailUserName, LoginEmail.EmailUserLiscen);
				int count=0;
				for(int i=0;i<Practice.e2;i++)
				{
					if((boolean)tableModel.getValueAt(i, 0) )
					{
						count++;
						break;
					}					
				}
				if(count>0)
				{
					//Practice.division();
					try {for(int i=Practice.e2-1;i>=0;i--)
					{
						if((boolean) tableModel.getValueAt(i, 0))
						{	
//							if(tableModel.getValueAt(i, 1).equals(Practice.email2Title[i]))
//								if(tableModel.getValueAt(i, 2).equals(Practice.email2Date[i]))
								{
									conn.deleteMail(Practice.email2Num[i]);
									deleteFile(Practice.email2Path[Practice.email2Num[i]]);
									tableModel.removeRow(i);
									changeFileId(Practice.email2Num[i]);
									updateDivision(i);													
								}
						}
					}
					conn.closeConnect();
					mw.exit=false;
					} catch (Exception e1) {
						// TODO �Զ����ɵ� catch ��
						JOptionPane.showMessageDialog(null, "删除异常!", "", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "请至少选择一个邮件", "", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(btnNewButton_1)
					.addGap(45)
					.addComponent(btnNewButton)
					.addGap(50)
					.addComponent(btnNewButton_2)
					.addGap(47)
					.addComponent(button_1)
					.addGap(47)
					.addComponent(button)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);		
		initTable();
		this.setResizable(false); 
		mw.start();
	}
	
	public static void moveFile(String oldPath,String NewPath)
	{
		try {  
            File afile = new File(oldPath);  
            if (!NewPath.endsWith(File.separator))
            	NewPath = NewPath + File.separator;
            if (afile.renameTo(new File(NewPath + afile.getName()))) {  
                System.out.println("File is moved successful!");  
            } else {  
                System.out.println("File is failed to move!");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  

	}
	static public void updateDivision(int nowrow)
	{
		System.out.println(nowrow);
		for(int i=0;i<Practice.e1;i++)
		{
			if(Practice.email1Num[i]>Practice.email2Num[nowrow])
			{
				Practice.email1Path[Practice.email1Num[i]]=LoginEmail.EmailUserName+"\\垃圾箱\\"+LoginEmail.EmailUserName+"-"+(Practice.email1Num[i]-1)+".html";
			}				
		}
		for(int i=nowrow;i<table.getRowCount();i++)
		{
			System.out.println(i+"行改id之前"+Practice.email2Num[i]);
			Practice.email2Num[i]=Practice.email2Num[i+1]-1;
			System.out.println(i+"行改id之后"+Practice.email2Num[i]);
			Practice.email2Title[Practice.email2Num[i]]=Practice.email2Title[Practice.email2Num[i+1]];
			Practice.email2Date[Practice.email2Num[i]]=Practice.email2Date[Practice.email2Num[i+1]];
			Practice.email2Path[Practice.email2Num[i]]=LoginEmail.EmailUserName+"\\邮件箱\\"+LoginEmail.EmailUserName+"-"+Practice.email2Num[i]+".html";	
		}
		Practice.e2--;
	}
	static public void changeFileId(int nowid) {
		System.out.println("删除的邮件id为"+nowid);
		for(int i=0;i<Practice.e1;i++)
		{
			if(Practice.email1Num[i]>nowid)
			{				
				System.out.println("垃圾箱应改的路径为"+Practice.email1Path[Practice.email1Num[i]]);
				File file=new File(Practice.email1Path[Practice.email1Num[i]]);				
				String word[]=file.getName().split("-");
				int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
				System.out.println("垃圾箱应改的id为"+id);
				id--;
				file.renameTo(new File(LoginEmail.EmailUserName+"\\垃圾箱\\"+word[0]+"-"+id+".html"));
			}				
		}
		for(int i=0;i<Practice.e2;i++)
		{
			if(Practice.email2Num[i]>nowid)
			{			
				System.out.println("垃圾箱应改的路径为"+Practice.email2Path[Practice.email2Num[i]]);
				File file=new File(Practice.email2Path[Practice.email2Num[i]]);
				String word[]=file.getName().split("-");
				int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
				System.out.println("邮件箱应改的id为"+id);
				id--;
				file.renameTo(new File(LoginEmail.EmailUserName+"\\邮件箱\\"+word[0]+"-"+id+".html"));
			}				
		}
//		FileListFilter s=new FileListFilter("html");
//		File[] c1;
//		File m1=new File(LoginEmail.EmailUserName+"\\邮件箱");
//		c1=m1.listFiles(s);
//		if(c1!=null)
//			for(File file:c1)
//			{
//				String word[]=file.getName().split("-");
//				int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
//				if(id>nowid)
//				{
//					System.out.println("邮件箱应改的id为"+id);
//					id--;
//					file.renameTo(new File(LoginEmail.EmailUserName+"\\邮件箱\\"+word[0]+"-"+id+".html"));
//				}
//			}
//		File[] c2;
//		File m2=new File(LoginEmail.EmailUserName+"\\垃圾箱");
//		c2=m2.listFiles(s);
//		if(c2!=null)
//			for(File file:c2)
//			{
//				String word[]=file.getName().split("-");
//				int id=Integer.parseInt(word[1].substring(0, word[1].length()-5));
//				if(id>nowid)
//				{
//					System.out.println("垃圾箱应改的id为"+id);
//					id--;
//					file.renameTo(new File(LoginEmail.EmailUserName+"\\垃圾箱\\"+word[0]+"-"+id+".html"));
//				}
//			}
	}
	public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //System.out.println("ɾ�������ļ�" + filePath + "�ɹ���");
                ProgressBarDemo.EmailsNum--;
                return true;
            } else {
               //System.out.println("ɾ�������ļ�" + filePath + "ʧ�ܣ�");
                return false;
            }
        } else {
            //System.out.println("ɾ�������ļ�ʧ�ܣ�" + filePath + "�����ڣ�");
            return false;
        }
    }
	static public void initTable()
	{		
		int rowcount = table.getRowCount();
		while (rowcount > 0) {	
			rowcount--;
			tableModel.removeRow(rowcount);		
		}				
		Practice.division();			
		infor=new Object[Practice.e2][3];
		for (int i=0;i< Practice.e2;i++) {
			infor[i][0]=false;
			infor[i][1]=Practice.email2Title[Practice.email2Num[i]];//����
			infor[i][2]=Practice.email2Date[Practice.email2Num[i]];//ʱ��
//			System.out.println(infor[i][0]);
//			System.out.println(infor[i][1]);
//			System.out.println(infor[i][2]);
		}
		//System.out.println("�ʼ���������"+Practice.e2);
		String[] title = {"选中","标题","时间"};
		tableModel=new DefaultTableModel(infor, title)
			{
			public boolean isCellEditable(int row, int column)
			{
				if(column==0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			};
		
		table.setModel(tableModel);		
		TableColumn tc = table.getColumnModel().getColumn(0);
		tc.setMaxWidth(100);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}

}
