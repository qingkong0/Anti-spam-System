

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
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class BlackAndWhiteList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
    public DefaultTableModel tableModel;
	private Object infor[][];
	private String BlackOrWhite;
	private String thisType;
	private String thatType;
	private String thisFilePath ;
	private String thatFilePath ;

	class closeWin extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			Window w = e.getWindow();
			w.dispose();
		}
	}

	public BlackAndWhiteList(String BlackOrWhite) {
		Image icon = Toolkit.getDefaultToolkit().getImage("image\\邮件标志.png");
		setIconImage(icon);
		this.BlackOrWhite = BlackOrWhite;
		thisType = BlackOrWhite;
		thatType=thisType.equals("黑")?"白":"黑";
		thisFilePath=LoginEmail.EmailUserName+"\\黑白名单\\"+thisType+"名单.txt";
		thatFilePath=LoginEmail.EmailUserName+"\\黑白名单\\"+thatType+"名单.txt";
		this.setTitle("设置" + BlackOrWhite + "名单");
		addWindowListener(new closeWin());
		setBounds(100, 100, 771, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)));

		JPanel panel_2 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE).addGap(25))
						.addGroup(Alignment.TRAILING,
								gl_panel_1.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
										.addGap(40)))));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		String ComlumnName = "地址ַ(域名)";

		table = new JTable();
		table .getTableHeader().setReorderingAllowed(false);
		infor=getInformation(thisFilePath);
		initTable(infor);

		

		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("添加到" + BlackOrWhite + "名单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = textField.getText();
				if (!address.equals("")) {		
					
					String s;
					Boolean thisflag=true;
					Boolean thatflag=true;
					try {						
						BufferedReader in = new BufferedReader(new FileReader(thatFilePath));
						while((s=in.readLine())!=null)
						{
							if(s.equals(address))
							{
								thatflag=false;								
								JOptionPane.showMessageDialog(null, thatType+"名单已存在该地址或者域名的记录", "", JOptionPane.ERROR_MESSAGE);
								break;								
							}
						}
						in.close();						
						if(thatflag)
						{
							int count=tableModel.getRowCount();
							for(int i=0;i<count;i++)
							{
								if(tableModel.getValueAt(i, 1).equals(address))
								{
									thisflag=false;
									JOptionPane.showMessageDialog(null, "该地址或域名已存在", "", JOptionPane.ERROR_MESSAGE);
									break;
								}
								
							}
							if(thisflag)
							{
								String line = System.getProperty("line.separator");
								StringBuffer str = new StringBuffer();
								FileWriter fw;
								fw = new FileWriter(thisFilePath, true);						
								str.append(address).append(line);						
								fw.write(str.toString());						
								fw.close();
								infor=getInformation(thisFilePath);
								initTable(infor);
							}
							
						}						
						LoginEmail.BW=new EmailHashMap();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}					
				} else {
					JOptionPane.showMessageDialog(null, "添加的域名不能为空", "", JOptionPane.ERROR_MESSAGE);
				}
				textField.setText("");
			}
		});
		btnNewButton.setFocusPainted(false);

		JButton btnNewButton_1 = new JButton("从" + BlackOrWhite + "名单中删除");
		btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					if ((boolean) tableModel.getValueAt(i, 0)) {
						count++;		
						break;
					}
				}
				if (count == 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的域名", "", JOptionPane.ERROR_MESSAGE);
				} 
				else {
					int i = 0;// ɨ�赽�ڼ���
					
					HashMap<String, Integer> hm = new LinkedHashMap();
					try {
						FileReader fr=new FileReader(thisFilePath);
						BufferedReader in = new BufferedReader(fr);
						String s;
						while ((s = in.readLine()) != null) {
							if ((boolean) tableModel.getValueAt(i, 0)) {
								i++;
							} else {
								hm.put(s, i);
								i++;
							}
						}
						in.close();
						fr.close();
						// ��ɾ���������txt
						String line = System.getProperty("line.separator");
						StringBuffer str = new StringBuffer();
						File file = new File(thisFilePath);
						if (file.exists()) {
							
							file.delete();
							
						}
						FileWriter fw;
						fw = new FileWriter(thisFilePath, true);
						
						for (Entry<String, Integer> entry : hm.entrySet()) {
							str.append(entry.getKey()).append(line);
							
							
						}
						System.out.println(str);
						fw.write(str.toString());
				
						fw.close();
						hm.clear();
						LoginEmail.BW=new EmailHashMap();
					} catch (IOException e1) {
						System.out.println("Error: " + e1);
					}
					infor=getInformation(thisFilePath);
					initTable(infor);
					
				}

			}
		});

		JButton btnNewButton_2 = new JButton("清空");
		btnNewButton_2.setFocusPainted(false);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(thisFilePath);
				if (file.exists()) {				
					file.delete();	
					try {
						file.createNewFile();
						LoginEmail.BW=new EmailHashMap();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				infor=new Object[0][2];
				initTable(infor);

			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE).addGap(18)
						.addComponent(btnNewButton).addGap(18).addComponent(btnNewButton_1)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton_2).addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel_2.setLayout(gl_panel_2);
		panel_1.setLayout(gl_panel_1);

		JLabel lblNewLabel = new JLabel("设置" + BlackOrWhite + "名单");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 24));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addContainerGap().addComponent(lblNewLabel).addContainerGap(534, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addContainerGap(33,
						Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.gray);
		g.drawLine(0, 100, 1000, 100);

	}

	public void initTable(Object [][]infor) {

		int rowcount = table.getRowCount();
		while (rowcount > 0) {
			rowcount--;
			tableModel.removeRow(rowcount);
		}

		String[] title = { "选中", "地址(域名)" };		
		tableModel = new DefaultTableModel(infor, title) {
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return true;
				} else {
					return false;				}
			}
		};

		table.setModel(tableModel);

		TableColumn tc = table.getColumnModel().getColumn(0);
		tc.setMaxWidth(100);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

	}
	
	public Object[][] getInformation(String filePath) {
		Object infor[][];
		try {
			LineNumberReader fileLine = new LineNumberReader(new FileReader(filePath));
			fileLine.skip(Long.MAX_VALUE);
			infor = new Object[fileLine.getLineNumber()][2];
			System.out.println(fileLine.getLineNumber());
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String s;
			int i = 0;
			while (i<fileLine.getLineNumber()) {
				System.out.println(i);
				infor[i][0] = false;
				infor[i][1] = in.readLine();
				i++;
			}
			in.close();
			fileLine.close();
			return infor;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
