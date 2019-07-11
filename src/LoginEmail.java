import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class LoginEmail extends JFrame implements ActionListener{
	public JButton login;
	public JButton help;
	public JButton mm;
	public JLabel user;
	public JLabel license;
	JLabel BG;
	JPanel jp;
	public JTextField hh;
	public JTextField getuser;
	public JTextField getlicense;
	static public String EmailUserName;
	static public String EmailUserLiscen;
	static public int temp=0;
	static public EmailHashMap ehm =new EmailHashMap(Practice.fp1, Practice.fp2);
	static public EmailHashMap BW;
	boolean isCtrl=false;
	boolean isV=false;
	JComboBox comboBox;
	ComboBoxModel comboBoxModel;
	static public String port;
	static public int portKind;
	String emType[] = {"@qq.com","@163.com","@126.com","@sina.com","@sohu.com","@aliyun.com"};
	public LoginEmail() {
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(680, 380, 560,360);	
		setback();
		jp=(JPanel)this.getContentPane();//ǿ������ת��
		jp.setOpaque(false);
		jp.setLayout(null);
		hh=new JTextField();
		hh.setBounds(0, 0, 0, 0);
		jp.add(hh);
//		mm=new JButton();
//		mm.setBounds(0, 0, 0, 0);
//		jp.add(mm);
		//mm.setVisible(false);
		login=new JButton("登录");
		login.setBackground(new Color(0,191,255));//������
		login.setForeground(Color.WHITE); 
		login.setFont(new Font("宋体",Font.BOLD,22));
		/*login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			   dispose();
			}
		});*/
		login.addActionListener(this);
		login.setBounds(170, 200, 230, 30);
		user=new JLabel("账户");
		user.setFont(new Font("宋体",Font.BOLD,20));
		user.setForeground(Color.WHITE);
		user.setBounds(170, 100, 50, 30);
		getuser=new JTextField("请输入你的邮箱账户"){
//			@Override
//		    public void copy() {
//		        System.out.println("Copy");
//		    }
//		    @Override
//		    public void paste() {
//		        System.out.println("Paste");
//		    }
//		    @Override
//		    public void cut() {
//		        System.out.println("Cut");
//		    }
		};
		getuser.setFont(new Font("宋体",Font.PLAIN,15));
		getuser.setForeground(Color.GRAY);
		getuser.setBounds(240, 100, 160, 30);
		getuser.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				int temp=e.getKeyChar();
				if (temp==33||temp==34||temp==35||temp==36||temp==37||temp==38||temp==39||temp==40||temp==41||temp==42||temp==43||temp==44||temp==45||temp==46||temp==47||temp==58||temp==59||temp==60||temp==61||temp==62||temp==63||temp==64||temp==94||temp==96||temp==91||temp==92||temp==93||temp==123||temp==124||temp==125||temp==126)
				{
					
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
		
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根

			}
		});
		getuser.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                // TODO Auto-generated method stub
                if(getuser.getText().equals("请输入你的邮箱账户"))               	
                {
                	getuser.setText("");    
                	getuser.setForeground(Color.BLACK);
                }
                else if(getuser.getText().equals(""))
                {
                	getuser.setText("请输入你的邮箱账户");
                	getuser.setForeground(Color.GRAY);
                }
                else {
                	getuser.setForeground(Color.BLACK);
                	getuser.selectAll();
				} 
                user.setForeground(new Color(0,191,255));
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                // TODO Auto-generated method stub
                if(getuser.getText().equals(""))
                {
                	getuser.setText("请输入你的邮箱账户");
                	getuser.setForeground(Color.GRAY);
                }
                else
                {
                	getuser.setForeground(Color.BLACK);
                }
                user.setForeground(Color.WHITE);
            }

        });
		license=new JLabel("授权码");
		license.setBounds(170, 150, 70, 30);
		license.setFont(new Font("宋体",Font.BOLD,20));
		license.setForeground(Color.WHITE);
		getlicense=new JTextField("请输入你的邮箱授权码"){
//			@Override
//		    public void copy() {
//		        System.out.println("Copy");
//		    }
//		    @Override
//		    public void paste() {
//		        System.out.println("Paste");
//		    }
//		    @Override
//		    public void cut() {
//		        System.out.println("Cut");
//		    }
		};
		getlicense.setFont(new Font("宋体",Font.PLAIN,15));
		getlicense.setForeground(Color.GRAY);
		getlicense.setBounds(240, 150, 160, 30);
		getlicense.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				int temp=e.getKeyChar();
				if (temp==33||temp==34||temp==35||temp==36||temp==37||temp==38||temp==39||temp==40||temp==41||temp==42||temp==43||temp==44||temp==45||temp==46||temp==47||temp==58||temp==59||temp==60||temp==61||temp==62||temp==63||temp==64||temp==94||temp==96||temp==91||temp==92||temp==93||temp==123||temp==124||temp==125||temp==126)
				{
					
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
		
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根

			}
		});
		getlicense.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent arg0) {
                // TODO Auto-generated method stub
                if(getlicense.getText().equals("请输入你的邮箱授权码"))
                {
                	getlicense.setText("");
                	getlicense.setForeground(Color.BLACK);
                }
                else if(getlicense.getText().equals(""))
                {
                	getlicense.setText("请输入你的邮箱授权码");
                	getlicense.setForeground(Color.GRAY);
                }
                else {
					getlicense.setForeground(Color.BLACK);
					getlicense.selectAll();
				}               
                license.setForeground(new Color(0,191,255));
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                // TODO Auto-generated method stub
            	if(getlicense.getText().equals(""))
                {
            		getlicense.setText("请输入你的邮箱授权码");
            		getlicense.setForeground(Color.GRAY);
                }
                else
                {
                	getlicense.setForeground(Color.BLACK);
                }
                license.setForeground(Color.WHITE);
            }

        });
		help=new JButton("如何获取授权码");
		help.setBackground(new Color(0,191,255));//������
		help.setForeground(Color.WHITE); 
		help.setFont(new Font("宋体",Font.BOLD,20));
		help.setBounds(170, 250, 230, 30);
		help.addActionListener(this);
		jp.add(help);
		jp.add(login);
		jp.add(user);
		jp.add(getuser);
		jp.add(license);
		jp.add(getlicense);		
		login.setFocusPainted(false);
		help.setFocusPainted(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		comboBox = new JComboBox();
		comboBoxModel = new DefaultComboBoxModel<>(emType);
		//comboBox.setFont(new Font("宋体",Font.PLAIN,12));
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(410, 100, 100, 30);
		jp.add(comboBox);
		setVisible(true);
		this.setResizable(false); 
	}
	public void setback() {
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		ImageIcon background = new ImageIcon("image\\青空.gif");
		background.setImage(background.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		BG = new JLabel(background);
		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		this.getLayeredPane().add(BG, new Integer(Integer.MIN_VALUE));		
		BG.setBounds(0, 0, this.getWidth(), this.getHeight());
		BG.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
				getuser.setFocusable(true);
				getlicense.setFocusable(true);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				getuser.setFocusable(false);
				getlicense.setFocusable(false);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO �Զ����ɵķ������				
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==login)
		{			
			try {
				Connect con =new Connect();
				int index=comboBox.getSelectedIndex();
				EmailUserName=getuser.getText()+emType[index];
				port=emType[index].substring(1, emType[index].length());
			    EmailUserLiscen=getlicense.getText();
			    portKind=1;
			    //portKind=0;
			if(con.Conn(EmailUserName, EmailUserLiscen)==1)//���ӳɹ�
			{				
				dispose();
				new ProgressBarDemo(EmailUserName,con);	
			}
			else {				
				con.closeConnect();
			}			
			} catch (Exception e2) {
				System.out.println(e2);
				// TODO: handle exception
			}	
		}	
		else if(e.getSource()==help)
		{
			Desktop desktop = Desktop.getDesktop();   
			URI uri;
			try {
				uri = new URI("http://xinzhi.wenda.so.com/a/1520685748614216");
				desktop.browse(uri);
			} catch (URISyntaxException | IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} //����URIͳһ��Դ��ʶ��			
		}
	}
}
