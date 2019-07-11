import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.Random;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ProgressBarDemo extends JFrame implements ActionListener, 
PropertyChangeListener {

	private JPanel contentPane;
	JProgressBar progressBar;
	private Task task;
	int progress = 0;
	private JLabel lblNewLabel;
	JLabel lblNewLabel_1;
	public static int UserGetFile;
	public static int FinishFile=0;
	public static int CurrentFinishFile;
	private JPanel panel_2;
	static public Connect connect;
	static public int EmailsNum;
	static public int lock=0;
	class Task extends SwingWorker<Void, Void>
	{        
	        @Override
	        public
	        Void doInBackground() {	
	        		        
	        setProgress(0);	        
	        try {					    					    
			    FileManage.createUserMkdir(LoginEmail.EmailUserName);
			    LoginEmail.BW=new EmailHashMap();								
				MailboxSettingFrame.initFromFile();
			    int allEmailLength=connect.messages.length;
		        for(int i=UserGetFile;i<connect.messages.length;i++)
		        {
		        	System.out.println("i="+i);
		            AnalysisMail am=new AnalysisMail(i);
		            am.start();
		        }
		        if((allEmailLength-UserGetFile)!=0)
				{
		        	while(progress<100)
			        {					
		        		if(CurrentFinishFile==FinishFile)
		        		{
		        			lock++;
		        			System.out.println(lock+"次");
		        			//
		        		}
		        		else
		        		{
		        			lock=0;
		        		}
		        		progress=(int)(FinishFile*100/(allEmailLength-UserGetFile));
						lblNewLabel_1.setText("已获取"+(FinishFile)+"/"+(allEmailLength-UserGetFile));
						CurrentFinishFile=FinishFile;
						setProgress(progress);
						if(progress==100||lock==1000000)
						{
							progress=100;
							setProgress(progress);
							lblNewLabel.setText("获取完毕!");			  
							dispose();
							MainFrame mf =new MainFrame(LoginEmail.EmailUserName,connect);
							break;
						}																												
			        }		
				}
		        else
		        {
		        	dispose();
					MainFrame mf =new MainFrame(LoginEmail.EmailUserName,connect);
					
		        }
		        
				        
		        connect.closeConnect();
	        	} catch (Exception e2) {
	        		System.out.println(e2);
	        		// TODO: handle exception
	        	}      
	        return null;	
	        }
	
	        @Override
	        public void done() {	//�����������������          
	            setCursor(null);
	            
	        }
	 }
	
    public void actionPerformed(ActionEvent evt) {
    	
    	lblNewLabel.setText("获取邮件中请耐心等待...");
    	
    	runTask();
        
    }

	
	 public void propertyChange(PropertyChangeEvent evt) 
	 {
	 
		 if("progress" == evt.getPropertyName())	 
	     {
			 int progress = (Integer) evt.getNewValue();
			 progressBar.setValue(progress);
			 //System.out.println(progress);	             
	     }
	 }
	

	 class closeWin extends WindowAdapter
		{
			public void windowClosing(WindowEvent e)
			{
				Window w=e.getWindow();
				w.dispose();
			}
		}
	public ProgressBarDemo (String EmailUserName,Connect conn) {
		this.setTitle("获取邮件");
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		connect=conn;
		setVisible(true);
		addWindowListener(new closeWin());
		setBounds(100, 100, 415, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		progressBar = new JProgressBar();
		progressBar.setValue(0);
        progressBar.setStringPainted(true);
		
		JPanel panel_1 = new JPanel();
		
		lblNewLabel_1 = new JLabel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(26))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(4)
					.addComponent(lblNewLabel_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		this.setResizable(false); 
		lblNewLabel = new JLabel("获取邮件中请耐心等待...");
		panel_2.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
		UserGetFile=getEmailsNum();
		runTask();		
	}
	
	public void runTask()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
        
	}
	
	public static int getEmailsNum() {
		Practice.division();
		EmailsNum=Practice.e1+Practice.e2;
		return EmailsNum;
	}
}
