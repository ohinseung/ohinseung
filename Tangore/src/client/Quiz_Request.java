/* 4.2.3 Ȯ�ι�ư ���� �� �Է��� IP(�����)�� �޼���
 * �ۼ��� : �̽���
 * �ۼ��� : 2017-05-23
 * */
package client;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Quiz_Request extends JFrame 
{
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public Quiz_Request() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>����û�� �Խ��ϴ�.<br>��� �Ͻðڽ��ϱ�?</html>");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 25));
		lblNewLabel.setBounds(104, 24, 240, 82);
		getContentPane().add(lblNewLabel);
		
		JButton versus_yes_btn = new JButton("��");
		versus_yes_btn.setFont(new Font("����", Font.PLAIN, 25));
		versus_yes_btn.setBounds(81, 161, 105, 27);
		getContentPane().add(versus_yes_btn);
		
		JButton versus_no_btn = new JButton("�ƴϿ�");
		versus_no_btn.setFont(new Font("����", Font.PLAIN, 25));
		versus_no_btn.setBounds(263, 161, 105, 27);
		getContentPane().add(versus_no_btn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}