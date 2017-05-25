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
		
		JLabel label = new JLabel("맞춘 개수 / 총 문제수");
		label.setFont(new Font("a옛날사진관3", Font.PLAIN, 25));
		label.setBounds(96, 63, 241, 48);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("무승부입니다.");
		label_1.setFont(new Font("a옛날사진관3", Font.PLAIN, 25));
		label_1.setBounds(142, 116, 152, 48);
		contentPane.add(label_1);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("a옛날사진관3", Font.PLAIN, 20));
		button.setBounds(141, 176, 143, 38);
		contentPane.add(button);
	}

}
