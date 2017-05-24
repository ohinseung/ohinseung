package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

public class Quiz_result3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz_result3 frame = new Quiz_result3();
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
	public Quiz_result3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("È®ÀÎ");
		button.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		button.setBounds(141, 176, 143, 38);
		contentPane.add(button);
		
		JLabel label = new JLabel("½ÂÀÚ´Â ÇÃ·¹ÀÌ¾î 2ÀÔ´Ï´Ù.");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label.setBounds(75, 116, 282, 48);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("¸ÂÃá °³¼ö / ÃÑ ¹®Á¦¼ö");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label_1.setBounds(96, 63, 241, 48);
		contentPane.add(label_1);
	}

}
