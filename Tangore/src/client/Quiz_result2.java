package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Quiz_result2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz_result2 frame = new Quiz_result2();
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
	public Quiz_result2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("맞춘 개수 / 총 문제수");
		label.setFont(new Font("a옛날사진관3", Font.PLAIN, 25));
		label.setBounds(101, 43, 241, 65);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("승자는 플레이어 1입니다.");
		label_1.setFont(new Font("a옛날사진관3", Font.PLAIN, 25));
		label_1.setBounds(83, 99, 295, 65);
		contentPane.add(label_1);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("a옛날사진관3", Font.PLAIN, 20));
		button.setBounds(141, 176, 143, 38);
		contentPane.add(button);
	}

}
