package client;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exception.ManagerException;
import vo.Tango;

public class InsertUI extends JFrame 
{
	private JPanel contentPane;
	public JTextField add_Hanja;
	public JTextField add_Hiragana;
	public JTextField add_Meaning;
	public File image;
	private ClientManager manager; // ��û�� ���õ� ó���� �ϱ� ���� ������ ClientManager Ŭ������ ��ü
	protected File imageFile;
	
	/**
	 * Create the frame.
	 */
	public InsertUI() 
	{	
		image=null;
		
		manager = new ClientManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
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
				MainUI mu = new MainUI();
				dispose();
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
                    imageFile = fc.getSelectedFile();
                    
                }
                
			}
		});
		
//		Ȯ�� ��ư
//		Ȯ�� ��ư�� ������ swingâ ���� + �߰��Ǵ� �ܾ��ȯ
		
		input_Ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String hiragana = add_Hiragana.getText();
				String hanja = add_Hanja.getText();
				String meaning = add_Meaning.getText();
				if(hiragana.equals("")) JOptionPane.showMessageDialog(null, "�ƹ��͵� �Էµ��� �ʾҽ��ϴ�.");
				else{
					Tango tango = new Tango(hiragana, hanja, meaning, imageFile);
					boolean insertResult = false;
					try {
						insertResult = manager.insertTango(tango);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					
					if(insertResult){
						JOptionPane.showMessageDialog(null, "����� ���� �Ͽ����ϴ�.");
						MainUI mu = new MainUI();
						dispose();
					}		
				}
			}
		});		
	}
	
}