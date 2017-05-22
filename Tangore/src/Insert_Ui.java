/* ÀÛ¼ºÀÚ : ÀÌ½ÂÇö
 * ÀÛ¼ºÀÏ : 2017-05-22
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Insert_Ui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
					Insert_Ui frame = new Insert_Ui();
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
	public Insert_Ui() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("ÀÔ·Â");
		
		JLabel label = new JLabel("»çÁø");
		label.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label.setBounds(50, 39, 56, 36);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ÇÑÀÚ");
		label_1.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label_1.setBounds(173, 39, 56, 36);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("È÷¶ó°¡³ª");
		label_2.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label_2.setBounds(298, 39, 101, 36);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("¶æ");
		label_3.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		label_3.setBounds(495, 39, 32, 36);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setFont(new Font("±¼¸²", Font.PLAIN, 23));
		textField.setBounds(144, 87, 101, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("±¼¸²", Font.PLAIN, 23));
		textField_1.setColumns(10);
		textField_1.setBounds(286, 87, 130, 36);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("±¼¸²", Font.PLAIN, 23));
		textField_2.setColumns(10);
		textField_2.setBounds(457, 87, 101, 36);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Ãß°¡");
		btnNewButton.setBounds(14, 87, 116, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("È®ÀÎ");
		btnNewButton_1.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnNewButton_1.setBounds(60, 158, 143, 59);
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("Ãë¼Ò");
		button.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		button.setBounds(366, 158, 143, 59);
		contentPane.add(button);
	}
}