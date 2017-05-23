/* 4.2.1 ����Ǯ�� �ɼ� ���� â 
 * �ۼ��� : �̽���
 * �ۼ��� : 2017-05-23
 * */
package client;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Quiz_1 extends JFrame
{
	private JPanel contentPane;
	JTextField quiz_num_textField = null;

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
					Quiz_1 frame = new Quiz_1();
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
	public Quiz_1() 
	{
		setTitle("����Ǯ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel quiz_title_label = new JLabel("����Ǯ�� �ɼ�");
		quiz_title_label.setFont(new Font("����", Font.PLAIN, 25));
		quiz_title_label.setBounds(155, 12, 152, 44);
		contentPane.add(quiz_title_label);
		
		JLabel quiz_num_label = new JLabel("���� ���� :");
		quiz_num_label.setFont(new Font("����", Font.PLAIN, 25));
		quiz_num_label.setBounds(21, 71, 139, 44);
		contentPane.add(quiz_num_label);
		
		quiz_num_textField= new JTextField("");
		quiz_num_textField.setFont(new Font("����", Font.PLAIN, 20));
		quiz_num_textField.setBounds(174, 77, 195, 36);
		contentPane.add(quiz_num_textField);
		quiz_num_textField.setColumns(10);
		
		JLabel player_label = new JLabel("��       �� : ");
		player_label.setFont(new Font("����", Font.PLAIN, 25));
		player_label.setBounds(24, 133, 130, 33);
		contentPane.add(player_label);
		
		JRadioButton player_1_btn = new JRadioButton("1��");
		player_1_btn.setFont(new Font("����", Font.PLAIN, 25));
		player_1_btn.setBounds(199, 133, 65, 33);
		contentPane.add(player_1_btn);
		
		JRadioButton player_2_btn = new JRadioButton("2��");
		player_2_btn.setFont(new Font("����", Font.PLAIN, 25));
		player_2_btn.setBounds(294, 136, 75, 26);
		contentPane.add(player_2_btn);
		
		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(player_1_btn);
		btngroup.add(player_2_btn);
		
		JLabel test_subject_label = new JLabel("���� �� �׸�");
		test_subject_label.setFont(new Font("����", Font.PLAIN, 25));
		test_subject_label.setBounds(21, 186, 146, 36);
		contentPane.add(test_subject_label);
		
		JCheckBox hanja_ckbox = new JCheckBox("����");
		hanja_ckbox.setFont(new Font("����", Font.PLAIN, 20));
		hanja_ckbox.setBounds(195, 195, 69, 27);
		contentPane.add(hanja_ckbox);
		
		JCheckBox hiragana_ckbox = new JCheckBox("���󰡳�");
		hiragana_ckbox.setFont(new Font("����", Font.PLAIN, 20));
		hiragana_ckbox.setBounds(270, 195, 114, 27);
		contentPane.add(hiragana_ckbox);
		
		JCheckBox meaning_ckbox = new JCheckBox("��");
		meaning_ckbox.setFont(new Font("����", Font.PLAIN, 20));
		meaning_ckbox.setBounds(390, 195, 58, 27);
		contentPane.add(meaning_ckbox);
		
		JButton quiz_ok_Btn = new JButton("Ȯ��");
		quiz_ok_Btn.setFont(new Font("����", Font.PLAIN, 25));
		quiz_ok_Btn.setBounds(65, 248, 146, 44);
		contentPane.add(quiz_ok_Btn);
		
		JButton quiz_cancel_Btn = new JButton("���");
		quiz_cancel_Btn.setFont(new Font("����", Font.PLAIN, 25));
		quiz_cancel_Btn.setBounds(253, 248, 146, 44);
		contentPane.add(quiz_cancel_Btn);
	}
}