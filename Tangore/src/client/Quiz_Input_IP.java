/*  4.2.2 ��� ��� IP�Է�â 
 * 	�ۼ��� : �̽���
 * �ۼ��� : 2017-05-23
 */
package client;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Quiz_Input_IP extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public Quiz_Input_IP() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("����� ����� IP�� �Է��� �ּ���");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel.setBounds(30, 26, 377, 44);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 25));
		textField.setBounds(54, 82, 326, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Ȯ��");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 25));
		btnNewButton.setBounds(54, 171, 115, 40);
		contentPane.add(btnNewButton);
		
		button = new JButton("���");
		button.setFont(new Font("����", Font.PLAIN, 25));
		button.setBounds(248, 171, 115, 40);
		contentPane.add(button);
	}
}