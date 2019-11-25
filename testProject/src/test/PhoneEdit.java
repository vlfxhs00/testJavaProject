package test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class PhoneEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfNumBer;
	private PhoneList parent;
	private PhoneDAO dao;
	private PhoneDTO dto;
	private String name;
	private ImageIcon icon;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PhoneEdit frame = new PhoneEdit();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	
	
	public PhoneEdit(PhoneList parent, String name) {
		this.parent = parent;
		this.name = name;
		System.out.println(parent);
		System.out.println(dto);
		
		setTitle("전화번호 수정/삭제");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 286, 397);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(12, 48, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(94, 45, 156, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("전화번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(12, 114, 64, 15);
		contentPane.add(lblNewLabel_1);
		
		tfNumBer = new JTextField();
		tfNumBer.setBounds(94, 111, 156, 21);
		contentPane.add(tfNumBer);
		tfNumBer.setColumns(10);
		
		dao=new PhoneDAO();
		dto=dao.viewPhone(name);
		tfName.setText(dto.getName());
		tfNumBer.setText(dto.getNumber());
		
		JButton btnNewButton = new JButton("정보 수정");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String number=tfNumBer.getText();
				PhoneDTO dto=new PhoneDTO(name, number);
				int result=dao.updatePhone(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(PhoneEdit.this, "정보가 수정되었습니다.");
					parent.refreshTable();
					dispose();
					
				}
				
			}
		});
		btnNewButton.setBounds(12, 243, 102, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("번호 삭제");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				int result = 0;
				int response = JOptionPane.showConfirmDialog(PhoneEdit.this, "번호를 제거하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deletePhone(name);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(PhoneEdit.this, "번호가 제거되었습니다.");
					parent.refreshTable();
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(148, 243, 102, 23);
		contentPane.add(btnNewButton_1);
	}
}
