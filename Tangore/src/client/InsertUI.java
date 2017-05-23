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
	private ClientManager manager; // 요청과 관련된 처리를 하기 위해 생성한 ClientManager 클래스의 객체
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
		
//		제목 입력
		setTitle("입력");
		
//		사진 Label
		JLabel image_Label = new JLabel("사진");
		image_Label.setFont(new Font("굴림", Font.PLAIN, 25));
		image_Label.setBounds(50, 39, 56, 36);
		contentPane.add(image_Label);
		
//		한자 Label
		JLabel hanja_Label = new JLabel("한자");
		hanja_Label.setFont(new Font("굴림", Font.PLAIN, 25));
		hanja_Label.setBounds(173, 39, 56, 36);
		contentPane.add(hanja_Label);
		
//		히라가나 Label
		JLabel hiragana_Label = new JLabel("히라가나");
		hiragana_Label.setFont(new Font("굴림", Font.PLAIN, 25));
		hiragana_Label.setBounds(298, 39, 101, 36);
		contentPane.add(hiragana_Label);
		
//		뜻 Label
		JLabel meaning_Label = new JLabel("뜻");
		meaning_Label.setFont(new Font("굴림", Font.PLAIN, 25));
		meaning_Label.setBounds(495, 39, 32, 36);
		contentPane.add(meaning_Label);
		
//		한자 Textfield
		add_Hanja = new JTextField();
		add_Hanja.setFont(new Font("굴림", Font.PLAIN, 23));
		add_Hanja.setBounds(144, 87, 101, 36);
		contentPane.add(add_Hanja);
		add_Hanja.setColumns(10);
		
//		히라가나 Textfield
		add_Hiragana = new JTextField();
		add_Hiragana.setFont(new Font("굴림", Font.PLAIN, 23));
		add_Hiragana.setColumns(10);
		add_Hiragana.setBounds(286, 87, 130, 36);
		contentPane.add(add_Hiragana);
		
//		뜻 Textfield
		add_Meaning = new JTextField();
		add_Meaning.setFont(new Font("굴림", Font.PLAIN, 23));
		add_Meaning.setColumns(10);
		add_Meaning.setBounds(457, 87, 101, 36);
		contentPane.add(add_Meaning);
		
//		사진 추가 버튼
		JButton add_Image_btn = new JButton("추가");
		add_Image_btn.setBounds(14, 87, 116, 36);
		contentPane.add(add_Image_btn);
		
//		확인 버튼
		JButton input_Ok = new JButton("확인");
		input_Ok.setFont(new Font("굴림", Font.PLAIN, 30));
		input_Ok.setBounds(60, 158, 143, 59);
		contentPane.add(input_Ok);
		
		JButton input_Cancel = new JButton("취소");
		input_Cancel.setFont(new Font("굴림", Font.PLAIN, 30));
		input_Cancel.setBounds(366, 158, 143, 59);
		contentPane.add(input_Cancel);
		
//		취소 버튼
//		취소 버튼을 누르면 swing창 종료
		input_Cancel.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MainUI mu = new MainUI();
				dispose();
			}
		});
		
//		사진 추가 버튼 누르면 사진 고르는 창 나오기.
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
		
//		확인 버튼
//		확인 버튼을 누르면 swing창 종료 + 추가되는 단어반환
		
		input_Ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String hiragana = add_Hiragana.getText();
				String hanja = add_Hanja.getText();
				String meaning = add_Meaning.getText();
				if(hiragana.equals("")) JOptionPane.showMessageDialog(null, "아무것도 입력되지 않았습니다.");
				else{
					Tango tango = new Tango(hiragana, hanja, meaning, imageFile);
					boolean insertResult = false;
					try {
						insertResult = manager.insertTango(tango);
					} catch (ManagerException e1) {
						e1.printStackTrace();
					}
					
					if(insertResult){
						JOptionPane.showMessageDialog(null, "등록을 성공 하였습니다.");
						MainUI mu = new MainUI();
						dispose();
					}		
				}
			}
		});		
	}
	
}