import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class LoginEmail extends JFrame implements ActionListener{
	public JButton login;
	public JLabel user;
	public JLabel license;
	public JTextField getuser;
	public JTextField getlicense;
	public LoginEmail() {
		login=new JButton("登录");
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			   dispose();
			}
		});
		login.addActionListener(this);
		login.setBounds(180, 200, 200, 30);
		user=new JLabel("账户:");
		user.setBounds(170, 100, 40, 30);
		getuser=new JTextField("请输入你的邮箱账户");
		getuser.setBounds(230, 100, 160, 30);
		license=new JLabel("验证码:");
		license.setBounds(170, 150, 60, 30);
		getlicense=new JTextField("请输入你的邮箱验证码");
		getlicense.setBounds(230, 150, 160, 30);
		add(login);
		add(user);
		add(getuser);
		add(license);
		add(getlicense);
		setLayout(null);
		setTitle("垃圾邮箱过滤系统登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560,480);
		setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}
