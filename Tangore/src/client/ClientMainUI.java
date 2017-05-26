package client;
/* �ۼ��� : ���ο�
 * �ۼ��� : 2017-05-22
 * */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import exception.ManagerException;

public class ClientMainUI extends JFrame implements ActionListener{
	
	private JPanel mainPanel;
	private JButton btn_tangoInsert;
	private JButton btn_tangoshou;
	private JButton btn_tangoQuiz;
	
	public ClientMainUI() {
		setTitle("����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		//�ļ� UI�� �۲� ����
		btn_tangoInsert = new JButton("�ܾ� �Է�");
		btn_tangoInsert.setFont(new Font("a����������3", Font.PLAIN, 32));
		btn_tangoInsert.setBounds(145, 100, 300, 90);
		btn_tangoInsert.addActionListener(this);
		mainPanel.add(btn_tangoInsert);
		
		btn_tangoshou = new JButton("�ܾ���");
		btn_tangoshou.setFont(new Font("a����������3", Font.PLAIN, 32));
		btn_tangoshou.setBounds(145, 270, 300, 90);
		btn_tangoshou.addActionListener(this);
		mainPanel.add(btn_tangoshou);
		
		btn_tangoQuiz = new JButton("�ܾ� ����");
		btn_tangoQuiz.setFont(new Font("a����������3", Font.PLAIN, 32));
		btn_tangoQuiz.setBounds(145, 440, 300, 90);
		btn_tangoQuiz.addActionListener(this);
		mainPanel.add(btn_tangoQuiz);
		
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	            ClientManager cm = new ClientManager();
	            cm.closeStreams();	           
	        }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// �̺�Ʈ�κ��� �̺�Ʈ�� �߻���Ų �ҽ��� ���Ѵ�
		if(source == btn_tangoInsert) {
			//��Ϲ�ư�� ������ �� ���θ޴��� ������ �ʰ� �ϰ�
			//��� UI�� visible�� �����Ѵ�.
			new ClientInsertUI();
			dispose();
		}
		else if(source == btn_tangoshou) {
			//�˻� UI����
			new ClientTangoUI();
			dispose();
		}
		else if(source == btn_tangoQuiz) {
			new Quiz_SelectOption();
			dispose();
		}
	}
	
}
