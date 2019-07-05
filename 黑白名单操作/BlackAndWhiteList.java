package mysystem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class BlackAndWhiteList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static public DefaultTableModel tableModel;
	private String BlackOrWhite;
	public static Object [][] infor;

	class closeWin extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Window w=e.getWindow();
			w.dispose();
		}
	}
	public BlackAndWhiteList(String BlackOrWhite) {
		this.BlackOrWhite=BlackOrWhite;		
		this.setTitle("设置");
		addWindowListener(new closeWin());
		setBounds(100, 100, 771, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
		);
		
		JPanel panel_2 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addContainerGap())
		);
				
		table = new JTable();
		initTable();
	
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("添加到"+BlackOrWhite+"名单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.addRow(new Vector());
			}
		});
		btnNewButton.setFocusPainted(false);

		
		JButton btnNewButton_1 = new JButton("从"+BlackOrWhite+"名单中删除");
		btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.removeRow(table.getSelectedRow());
			}
		});
		
		JButton btnNewButton_2 = new JButton("清空"+BlackOrWhite+"名单");
		btnNewButton_2.setFocusPainted(false);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<=tableModel.getRowCount();i++) {
					tableModel.removeRow(i);
				}
			}
		});
		
		JButton button = new JButton("保存");
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = "D:\\"+BlackOrWhite+".txt";//黑白名单文件地址
				String line = System.getProperty("line.separator");
				StringBuffer str = new StringBuffer();
				FileWriter fw;
				try {
					for(int i=0;i<=tableModel.getRowCount();i++) {
						fw = new FileWriter(filePath, true);
						str.append(tableModel.getValueAt(i, 0)).append(line);
						fw.write(str.toString());
						fw.close();
					}
				} catch (IOException error) {
					error.printStackTrace();
				}
			}
		});
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_2.add(btnNewButton);
		panel_2.add(btnNewButton_1);
		panel_2.add(btnNewButton_2);
		panel_2.add(button);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("设置地址"+BlackOrWhite+"名单");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 24));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(534, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		this.setResizable(false); 
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.gray);
		g.drawLine(0, 100, 1000,100);
		
	}
	
	public void initTable()//从文件中获取信息
	{
		
		int rowcount = table.getRowCount();		
		while (rowcount > 0) {	
			rowcount--;
			tableModel.removeRow(rowcount);		
		}
				
		/*Practice.division();			
		infor=new Object[Practice.e2][2];
		for (int i=0;i< Practice.e2;i++) {
			infor[i][0]=false;
			infor[i][1]=Practice.email2Title[i];
			System.out.println(infor[i][0]);
			System.out.println(infor[i][1]);
			
		}*/
		
		String[] title = {"地址(域名)"};
		tableModel=new DefaultTableModel(infor, title);					
		table.setModel(tableModel);		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
