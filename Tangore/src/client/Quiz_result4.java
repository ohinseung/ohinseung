package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Quiz_result4 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz_result4 frame = new Quiz_result4();
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
	public Quiz_result4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("���� ���� / �� ������");
		label.setFont(new Font("����", Font.PLAIN, 25));
		label.setBounds(96, 63, 241, 48);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("���º��Դϴ�.");
		label_1.setFont(new Font("����", Font.PLAIN, 25));
		label_1.setBounds(142, 116, 152, 48);
		contentPane.add(label_1);
		
		JButton button = new JButton("Ȯ��");
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(141, 176, 143, 38);
		contentPane.add(button);
	}

}
