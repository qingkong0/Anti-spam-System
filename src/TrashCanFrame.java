import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TrashCanFrame extends JFrame {

	private JPanel contentPane;
	
	static public JTable table;
	
	static public DefaultTableModel tableModel;
	public static Object [][] infor;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	class closeWin extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Window w=e.getWindow();
			w.dispose();
			MainFrame.hasTrashCanFrame=0;
		}
	}
	public TrashCanFrame(Connect conn) {
		setTitle(LoginEmail.EmailUserName+"的垃圾箱");
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		addWindowListener(new closeWin());
		setBounds(100, 100, 684, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		
		
				
		Vector columnHeads =new Vector<String>();
		columnHeads.addElement("����");
		tableModel = new DefaultTableModel(null,columnHeads)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};		
	
		Practice.division();
		for (int i=0;i< Practice.e1;i++) {
			Vector currentRow = new Vector<String>();				
		    currentRow.addElement(Practice.email1Title[i]);
		    tableModel.addRow(currentRow);
		}	    
	    table = new JTable(tableModel);
	    table .getTableHeader().setReorderingAllowed(false);
	    initTable();


		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("打开邮件");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.mw.exit=true;
				int count=0;
				for(int i=0;i<Practice.e1;i++)
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
					for(int i=0;i<Practice.e1;i++)
					{
						if((boolean) tableModel.getValueAt(i, 0))
						{	
							try {
								Desktop.getDesktop().open(new File(Practice.email1Path[Practice.email1Num[i]]));
							} catch (IOException e1) {
								// TODO �Զ����ɵ� catch ��
								e1.printStackTrace();
							}
							//AnalyFrame af = new AnalyFrame(Practice.email1Path[i]);
							tableModel.setValueAt(false, i, 0);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "请至少选择一个邮件", "", JOptionPane.ERROR_MESSAGE);
				}
				MainFrame.mw.exit=false;
			}
		});
		
		JButton btnNewButton_1 = new JButton("删除邮件");
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.mw.exit=true;
				conn.Conn(LoginEmail.EmailUserName, LoginEmail.EmailUserLiscen);
				int count=0;
				for(int i=0;i<Practice.e1;i++)
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
					try {for(int i=Practice.e1-1;i>=0;i--)
					{
						if((boolean) tableModel.getValueAt(i, 0))
						{	
								{
									String a=Practice.email1Path[Practice.email1Num[i]];
									File file=new File(a);			
									Email email=new Email(a, Practice.d);
									AnalysisHtml analysisHtml = new AnalysisHtml();
							        String content=analysisHtml.Html2Text(email.content).replaceAll("&nbsp"," ");        
									Email email1=new Email(content, Practice.d, 0);    
									conn.deleteMail(Practice.email1Num[i]);
									tableModel.removeRow(i);	
									deleteFile(a);	
									Practice.learn1(email1);		  
							        try {
										FileManage.creatFile(Practice.praPath2+"\\"+file.getName().substring(0,file.getName().length()-5), content);
									} catch (Exception e1) {
										// TODO 自动生成的 catch 块
										e1.printStackTrace();
									}
									MainFrame.changeFileId(Practice.email1Num[i]);
									updateDivision(i);												
								}
						}
					}
					conn.closeConnect();
					MainFrame.mw.exit=false;
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
		
		JButton btnNewButton_2 = new JButton("清空");
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.mw.exit=true;
				System.out.println(table.getRowCount());
				conn.Conn(LoginEmail.EmailUserName, LoginEmail.EmailUserLiscen);
				int rowcount = table.getRowCount();
				try {
					while (rowcount > 0) {	
					rowcount--;
					String a=Practice.email1Path[Practice.email1Num[rowcount]];
					File file=new File(a);			
					Email email=new Email(a, Practice.d);
					AnalysisHtml analysisHtml = new AnalysisHtml();
			        String content=analysisHtml.Html2Text(email.content).replaceAll("&nbsp"," ");        
					Email email1=new Email(content, Practice.d, 0); 
			        conn.deleteMail(Practice.email1Num[rowcount]);	
					deleteFile(a);
					tableModel.removeRow(rowcount);	
					Practice.learn1(email1);		  
			        try {
						FileManage.creatFile(Practice.praPath2+"\\"+file.getName().substring(0,file.getName().length()-5), content);
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					MainFrame.changeFileId(Practice.email1Num[rowcount]);							
					}
				Practice.division();	
				conn.closeConnect();
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					JOptionPane.showMessageDialog(null, "删除异常!", "", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}	
				MainFrame.mw.exit=false;
			}
		});
		
		JButton btnNewButton_3 = new JButton("还原");
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.mw.exit=true;
				int count=0;
				for(int i=0;i<Practice.e1;i++)
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
					for(int i=Practice.e1-1;i>=0;i--)
					{
						if((boolean) tableModel.getValueAt(i, 0))	
								{
									String a=Practice.email1Path[Practice.email1Num[i]];
									String b=LoginEmail.EmailUserName+"\\邮件箱";
									tableModel.removeRow(i);
									File newfile=new File(a);
									String filename=newfile.getName();
									Email email=new Email(a, Practice.d);
									AnalysisHtml analysisHtml = new AnalysisHtml();
							        String content=analysisHtml.Html2Text(email.content).replaceAll("&nbsp"," "); 
							        content=content.substring(4, content.length());
									Email email1=new Email(content, Practice.d, 0);
							        Practice.learn2(email1);		  
							        try {
										FileManage.creatFile(Practice.praPath2+"\\"+filename.substring(0,filename.length()-5), content);
									} catch (Exception e1) {
										// TODO 自动生成的 catch 块
										e1.printStackTrace();
									}
							        MainFrame.moveFile(a,b);									
								}
					}
					MainFrame.initTable();
				}
				else {
					JOptionPane.showMessageDialog(null, "请至少选择一个邮件", "", JOptionPane.ERROR_MESSAGE);
				}
				MainFrame.mw.exit=false;			
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addComponent(btnNewButton)
					.addGap(37)
					.addComponent(btnNewButton_1)
					.addGap(44)
					.addComponent(btnNewButton_2)
					.addGap(44)
					.addComponent(btnNewButton_3)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setResizable(false);
	}
	static public void updateDivision(int nowrow)
	{
		for(int i=0;i<Practice.e2;i++)
		{
			if(Practice.email2Num[i]>Practice.email1Num[nowrow])
			{
				Practice.email2Path[Practice.email2Num[i]]=LoginEmail.EmailUserName+"\\邮件箱\\"+LoginEmail.EmailUserName+"-"+(Practice.email2Num[i]-1)+".html";
			}				
		}
		for(int i=nowrow;i<table.getRowCount();i++)
		{
			System.out.println(i+"行改id之前"+Practice.email1Num[i]);
			Practice.email1Num[i]=Practice.email1Num[i+1]-1;
			System.out.println(i+"行改id之后"+Practice.email1Num[i]);
			Practice.email1Title[Practice.email1Num[i]]=Practice.email1Title[Practice.email1Num[i+1]];
			Practice.email1Date[Practice.email1Num[i]]=Practice.email1Date[Practice.email1Num[i+1]];
			Practice.email1Path[Practice.email1Num[i]]=LoginEmail.EmailUserName+"\\垃圾箱\\"+LoginEmail.EmailUserName+"-"+Practice.email1Num[i]+".html";	
		}
		Practice.e1--;
	}
	public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        File filecopy= new File(Practice.praPath1+"\\"+file.getName());
        try {
			Practice.copyFileUsingFileChannels(file, filecopy);
			Practice.learn1(new Email(filePath, Practice.d));
			ProgressBarDemo.EmailsNum--;
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //System.out.println("ɾ�������ļ�" + filePath + "�ɹ���");
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
		try {
			int rowcount = table.getRowCount();
		while (rowcount > 0) {	
			rowcount--;
			tableModel.removeRow(rowcount);		
		}				
		Practice.division();			
		infor=new Object[Practice.e1][3];
		for (int i=0;i< Practice.e1;i++) {
			infor[i][0]=false;
			infor[i][1]=Practice.email1Title[Practice.email1Num[i]];
			infor[i][2]=Practice.email1Date[Practice.email1Num[i]];//ʱ��
//			System.out.println(infor[i][0]);
//			System.out.println(infor[i][1]);
//			System.out.println(infor[i][2]);
		}
		
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
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
