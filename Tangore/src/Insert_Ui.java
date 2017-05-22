/* �ۼ��� : �̽���
 * �ۼ��� : 2017-05-22
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Insert_Ui extends JFrame 
{
	private JPanel contentPane;
	private JTextField add_Hanja;
	private JTextField add_Hiragana;
	private JTextField add_Meaning;

	JLabel	image;
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
					Insert_Ui insert_ui_frame = new Insert_Ui();
					insert_ui_frame.setVisible(true);
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
		
//		���� �Է�
		setTitle("�Է�");
		
//		���� Label
		JLabel image_Label = new JLabel("����");
		image_Label.setFont(new Font("����", Font.PLAIN, 25));
		image_Label.setBounds(50, 39, 56, 36);
		contentPane.add(image_Label);
		
//		���� Label
		JLabel hanja_Label = new JLabel("����");
		hanja_Label.setFont(new Font("����", Font.PLAIN, 25));
		hanja_Label.setBounds(173, 39, 56, 36);
		contentPane.add(hanja_Label);
		
//		���󰡳� Label
		JLabel hiragana_Label = new JLabel("���󰡳�");
		hiragana_Label.setFont(new Font("����", Font.PLAIN, 25));
		hiragana_Label.setBounds(298, 39, 101, 36);
		contentPane.add(hiragana_Label);
		
//		�� Label
		JLabel meaning_Label = new JLabel("��");
		meaning_Label.setFont(new Font("����", Font.PLAIN, 25));
		meaning_Label.setBounds(495, 39, 32, 36);
		contentPane.add(meaning_Label);
		
//		���� Textfield
		add_Hanja = new JTextField();
		add_Hanja.setFont(new Font("����", Font.PLAIN, 23));
		add_Hanja.setBounds(144, 87, 101, 36);
		contentPane.add(add_Hanja);
		add_Hanja.setColumns(10);
		
//		���󰡳� Textfield
		add_Hiragana = new JTextField();
		add_Hiragana.setFont(new Font("����", Font.PLAIN, 23));
		add_Hiragana.setColumns(10);
		add_Hiragana.setBounds(286, 87, 130, 36);
		contentPane.add(add_Hiragana);
		
//		�� Textfield
		add_Meaning = new JTextField();
		add_Meaning.setFont(new Font("����", Font.PLAIN, 23));
		add_Meaning.setColumns(10);
		add_Meaning.setBounds(457, 87, 101, 36);
		contentPane.add(add_Meaning);
		
//		���� �߰� ��ư
		JButton add_Image_btn = new JButton("�߰�");
		add_Image_btn.setBounds(14, 87, 116, 36);
		contentPane.add(add_Image_btn);
		
//		Ȯ�� ��ư
		JButton input_Ok = new JButton("Ȯ��");
		input_Ok.setFont(new Font("����", Font.PLAIN, 30));
		input_Ok.setBounds(60, 158, 143, 59);
		contentPane.add(input_Ok);
		
		JButton input_Cancel = new JButton("���");
		input_Cancel.setFont(new Font("����", Font.PLAIN, 30));
		input_Cancel.setBounds(366, 158, 143, 59);
		contentPane.add(input_Cancel);
		
//		��� ��ư
//		��� ��ư�� ������ swingâ ����
		input_Cancel.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		
//		���� �߰� ��ư ������ ���� ���� â ������.
		add_Image_btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
				JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        image.setIcon(new ImageIcon(ImageIO.read(file)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			}
		});
		
	}
}