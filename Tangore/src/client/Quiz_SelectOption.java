/* 4.2.1 ����Ǯ�� �ɼ� ���� â 
 * �ۼ��� : �̽���
 * �ۼ��� : 2017-05-23
 * */
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exception.ManagerException;
import vo.Tango;

public class Quiz_SelectOption extends JFrame
{
	private JPanel	contentPane;
	JTextField		quiz_num_textField;
	private int 	count;
	ClientManager 	cm = new ClientManager();
	ArrayList<Tango> quiz_alist = new ArrayList<>();
	/**
	 * Create the frame.
	 */


	public Quiz_SelectOption()
	{
		setTitle("����Ǯ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel quiz_title_label = new JLabel("����Ǯ�� �ɼ�");
		quiz_title_label.setFont(new Font("a����������3", Font.PLAIN, 25));
		quiz_title_label.setBounds(155, 12, 152, 44);
		contentPane.add(quiz_title_label);

		JLabel quiz_num_label = new JLabel("���� ���� :");
		quiz_num_label.setFont(new Font("a����������3", Font.PLAIN, 25));
		quiz_num_label.setBounds(21, 71, 139, 44);
		contentPane.add(quiz_num_label);

		count = cm.data_all();
		String allCount = Integer.toString(count);
		quiz_num_textField = new JTextField();
		quiz_num_textField.setFont(new Font("a����������3", Font.PLAIN, 20));
		quiz_num_textField.setBounds(174, 77, 195, 36);
		quiz_num_textField.setText(allCount);
		quiz_num_textField.setForeground(Color.gray);
		contentPane.add(quiz_num_textField);

		JLabel player_label = new JLabel("��       �� : ");
		player_label.setFont(new Font("a����������3", Font.PLAIN, 25));
		player_label.setBounds(24, 133, 130, 33);
		contentPane.add(player_label);

		JRadioButton player_1_btn = new JRadioButton("1��");
		player_1_btn.setFont(new Font("a����������3", Font.PLAIN, 25));
		player_1_btn.setBounds(199, 133, 65, 33);
		contentPane.add(player_1_btn);

		JRadioButton player_2_btn = new JRadioButton("2��");
		player_2_btn.setFont(new Font("a����������3", Font.PLAIN, 25));
		player_2_btn.setBounds(294, 136, 75, 26);
		contentPane.add(player_2_btn);

		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(player_1_btn);
		btngroup.add(player_2_btn);

		JLabel test_subject_label = new JLabel("���� �� �׸�");
		test_subject_label.setFont(new Font("a����������3", Font.PLAIN, 25));
		test_subject_label.setBounds(21, 186, 146, 36);
		contentPane.add(test_subject_label);

		JCheckBox hanja_ckbox = new JCheckBox("����");
		hanja_ckbox.setFont(new Font("a����������3", Font.PLAIN, 20));
		hanja_ckbox.setBounds(195, 195, 69, 27);
		contentPane.add(hanja_ckbox);

		JCheckBox hiragana_ckbox = new JCheckBox("���󰡳�");
		hiragana_ckbox.setFont(new Font("a����������3", Font.PLAIN, 20));
		hiragana_ckbox.setBounds(270, 195, 114, 27);
		contentPane.add(hiragana_ckbox);

		JCheckBox meaning_ckbox = new JCheckBox("��");
		meaning_ckbox.setFont(new Font("a����������3", Font.PLAIN, 20));
		meaning_ckbox.setBounds(390, 195, 58, 27);
		contentPane.add(meaning_ckbox);

		JButton quiz_ok_Btn = new JButton("Ȯ��");
		quiz_ok_Btn.setFont(new Font("a����������3", Font.PLAIN, 25));
		quiz_ok_Btn.setBounds(65, 248, 146, 44);
		contentPane.add(quiz_ok_Btn);

		JButton quiz_cancel_Btn = new JButton("���");
		quiz_cancel_Btn.setFont(new Font("a����������3", Font.PLAIN, 25));
		quiz_cancel_Btn.setBounds(253, 248, 146, 44);
		contentPane.add(quiz_cancel_Btn);
		
		quiz_ok_Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���� ��ư ���ÿ� ���� ���� �޴��� �и��ؼ� �ҷ��´�.
				if(e.getSource() == player_1_btn) new ClientQuizUI();
				else if(e.getSource() == player_2_btn) new Quiz_Input_IP();
				
				int testCase = 0;
				int caseCount = 0;
				
				//üũ�ڽ� ����� ���� ���� case�� �з��Ѵ�.
				if(e.getSource() == hanja_ckbox) caseCount += 1;
				if(e.getSource() == hiragana_ckbox) caseCount += 10;
				if(e.getSource() == meaning_ckbox) caseCount += 100;
				
				switch(caseCount) {
				case 1: 
					testCase = 1;
					break;
				case 10:
					testCase = 5;
					break;
				case 100:
					testCase = 9;
					break;
				case 11:
					testCase = 2;
					break;
				case 101:
					testCase = 6;
					break;
				case 110:
					testCase = 10;
					break;
				case 111:
					testCase = 3;
					break;
				}
				
				try {
					new ClientQuizUI(quiz_alist, testCase);
				} catch (ManagerException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		quiz_cancel_Btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new ClientMainUI();
			}
		});

		setVisible(true);
		quiz_num_textField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				quiz_num_textField.setText("");
				quiz_num_textField.setForeground(Color.BLACK);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
		
		quiz_num_textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(quiz_num_textField.getText().equals(null)) {
					JOptionPane.showConfirmDialog(null, "���� �Է����ּ���");
				}
				int quiz_list = Integer.parseInt(quiz_num_textField.getText());
				try {
					if(quiz_list <= 0) JOptionPane.showConfirmDialog(null, "���� ������ 1 �̻��̾�� �մϴ�.");
					else if(quiz_list > count) JOptionPane.showConfirmDialog(null, "������ �ִ���� �ʰ��մϴ�.");
					else quiz_alist = cm.getQuizList(quiz_list);
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, "���ڸ� �Է����ּ���.");
				}
				
				
			}
		});
		
		player_1_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		player_2_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		hanja_ckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		hiragana_ckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		meaning_ckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	
}