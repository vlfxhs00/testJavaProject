package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class PhoneSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfNumBer;
	
	private PhoneList frm;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PhoneSave frame = new PhoneSave();
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
	public PhoneSave(PhoneList frm) {
		this();
		this.frm = frm;
	}
	
	public PhoneSave() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 327, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(12, 55, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(100, 52, 148, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("전화번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(12, 120, 76, 15);
		contentPane.add(lblNewLabel_1);
		
		tfNumBer = new JTextField();
		tfNumBer.setBounds(100, 117, 148, 21);
		contentPane.add(tfNumBer);
		tfNumBer.setColumns(10);
		
		JButton btnNewButton = new JButton("전화번호 저장");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String number=tfNumBer.getText();
				
				PhoneDTO dto = new PhoneDTO(name, number);
				PhoneDAO dao = new PhoneDAO();
				int result=dao.insertPhone(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(PhoneSave.this, "번호가 저장되었습니다.");
					frm.refreshTable();
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(73, 266, 175, 23);
		contentPane.add(btnNewButton);
	}

}
