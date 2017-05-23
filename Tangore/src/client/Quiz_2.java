/*  4.2.2 대결 상대 IP입력창 
 * 	작성자 : 이승현
 * 작성일 : 2017-05-23
 */
package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Quiz_2 extends JFrame 
{
	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Quiz_2 frame = new Quiz_2();
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Quiz_2() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("대결할 상대의 IP를 입력해 주세요");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel.setBounds(30, 26, 377, 44);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.PLAIN, 25));
		textField.setBounds(54, 82, 326, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 25));
		btnNewButton.setBounds(54, 171, 115, 40);
		contentPane.add(btnNewButton);
		
		button = new JButton("취소");
		button.setFont(new Font("굴림", Font.PLAIN, 25));
		button.setBounds(248, 171, 115, 40);
		contentPane.add(button);
	}
}