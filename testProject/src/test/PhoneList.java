package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class PhoneList extends JFrame {
	
	private JTable table;
	private JPanel contentPane;
	private ImageIcon icon;
	private JTextField tfName;
	private PhoneDAO dao;
	private Vector cols;
	private DefaultTableModel model;
	private PhoneDTO dto;
	private JButton btnMyNumber;
	private JButton btnSave;
	private JButton btnEdit;
	private Vector<String> col;
	private MyPhone my;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneList frame = new PhoneList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhoneList() {
		dao = new PhoneDAO();
		cols=new Vector();
		cols.add("이름");
		cols.add("전화번호");
		list();
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int idx=table.getSelectedRow();
				
				tfName.setEditable(false);
				tfName.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
						}
		});
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				icon=new ImageIcon("C:\\핸드폰.jpg");
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);	
				super.paintComponent(g);					
			}		
		};
		
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
		panel.setBounds(0, 0, 409, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("연락처");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 24));
		lblNewLabel.setBounds(30, 66, 104, 33);
		panel.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfName.setBounds(129, 109, 242, 26);
		panel.add(tfName);
		tfName.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 225, 349, 257);
		panel.add(scrollPane);
		
		btnMyNumber = new JButton("나의 연락처");
		btnMyNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MyPhone my=new MyPhone();
				my.setVisible(true);
				my.setLocation(200,200);
			}
		});
			
		
		btnMyNumber.setBackground(SystemColor.text);
		btnMyNumber.setForeground(SystemColor.infoText);
		btnMyNumber.setFont(new Font("돋움", Font.BOLD, 16));
		btnMyNumber.setBounds(50, 145, 148, 39);
		panel.add(btnMyNumber);
		
		btnSave = new JButton("전화번호 추가");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhoneSave frm = new PhoneSave(PhoneList.this);
				frm.setVisible(true);
				frm.setLocation(200,200);
						
			}
		});
		btnSave.setBackground(SystemColor.text);
		btnSave.setFont(new Font("굴림", Font.BOLD, 12));
		btnSave.setBounds(60, 192, 119, 23);
		panel.add(btnSave);
		
		btnEdit = new JButton("전화번호 수정/삭제");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				System.out.println(idx);
				if(idx == -1) {
					JOptionPane.showMessageDialog(PhoneList.this, "수정/삭제할 번호를 선택하세요.");
					return;
				}else {
					String name=String.valueOf(table.getValueAt(idx, 0));
					PhoneEdit frm = new PhoneEdit(PhoneList.this,name);
					frm.setVisible(true);
					frm.setLocation(550,100);
				}
			}
		});
		btnEdit.setBackground(SystemColor.text);
		btnEdit.setFont(new Font("굴림", Font.BOLD, 12));
		btnEdit.setBounds(201, 192, 154, 23);
		panel.add(btnEdit);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		refreshTable();
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setFont(new Font("굴림", Font.BOLD, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(40, 109, 77, 23);
		panel.add(btnSearch);
		
		
		
	}
	
	public void search() {
		String name=tfName.getText();
		model=new DefaultTableModel(dao.searchPhone(name),cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}//search()
	public void refreshTable() {
		DefaultTableModel model = new DefaultTableModel(dao.listPhone(),cols);
		table.setModel(model);
	}
	
	public void list() {
		model=new DefaultTableModel(dao.listPhone(), cols) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
}
