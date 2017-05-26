package client;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Quiz_Result_Fail extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public Quiz_Result_Fail(){
		super();
	}
	
	public Quiz_Result_Fail(int correctNum, int totalNum, int score) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JButton button = new JButton("Ȯ��");
		button.setFont(new Font("a����������3", Font.PLAIN, 20));
		button.setBounds(141, 176, 143, 38);
		contentPane.add(button);
		
		JLabel label = new JLabel("�����ϴ�.");
		label.setFont(new Font("a����������3", Font.PLAIN, 25));
		label.setBounds(75, 116, 282, 48);
		contentPane.add(label);
		
		String msg = "���� ���� : " + correctNum +"\n �� ������ : "+totalNum +"\n ���� : "+score;
		JLabel label_1 = new JLabel("���� ���� / �� ������");
		label_1.setFont(new Font("a����������3", Font.PLAIN, 25));
		label_1.setBounds(96, 63, 241, 48);
		contentPane.add(label_1);
	}

}
