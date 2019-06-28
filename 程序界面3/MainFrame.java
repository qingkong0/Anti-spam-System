package getMailnfo;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
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
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.Color;
import javax.swing.JTable;

public class MainFrame extends JFrame {
	  private Frame jf;
	
	class JF extends JFrame{
		public JF() {
			addWindowListener(new closeWin());
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
	
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

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
		
		JTextArea textArea_1 = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane();
		
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
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		
		scrollPane_1.setViewportView(textArea_1);
		
		table_1 = new JTable();
		scrollPane_1.setColumnHeaderView(table_1);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnNewButton = new JButton("\u5BFC\u5165\u90AE\u4EF6");
		btnNewButton.setFocusPainted(false);

		btnNewButton.setBackground(Color.LIGHT_GRAY);
		
		JButton btnNewButton_1 = new JButton("\u6253\u5F00\u90AE\u4EF6");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFocusPainted(false);
		
		
		JButton btnNewButton_2 = new JButton("\u653E\u5165\u5783\u573E\u7BB1");
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					UIManager.setLookAndFeel(UIManager
					        .getSystemLookAndFeelClassName());
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(2);
				    int returnVal = chooser.showOpenDialog(null);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            chooser.getSelectedFile().getName());
				    }
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			    
			    
//				FileDialog  fdopen = new FileDialog(jf);
//				//fdopen.set
//	    		fdopen.show();
//	    		
//	    		if(fdopen.getFile()!=null)
//	    		{
//	    			fdopen.getDirectory();
//	    		}
				
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u67E5\u770B\u5783\u573E\u7BB1");
		
		JButton button = new JButton("\u5207\u6362\u7528\u6237");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(btnNewButton)
					.addGap(49)
					.addComponent(btnNewButton_1)
					.addGap(46)
					.addComponent(btnNewButton_2)
					.addGap(48)
					.addComponent(btnNewButton_3)
					.addGap(45)
					.addComponent(button)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					AnalyFrame form=new AnalyFrame();
					if(form.hasChooseFile)
					{
						form.textArea.setEditable(false);
						form.setVisible(true);
					}
					
					
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
