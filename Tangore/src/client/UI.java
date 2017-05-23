package client;
/* 작성자 : 김인우
 * 작성일 : 2017-05-22
 * */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI extends JFrame implements ActionListener{
	
	private JPanel mainPanel;
	private JButton btn_tangoInsert;
	private JButton btn_tangoshou;
	private JButton btn_tangoQuiz;
	
	public UI() {
		setTitle("탕고래");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		
		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		//후속 UI와 글꼴 통일
		btn_tangoInsert = new JButton("단어 입력");
		btn_tangoInsert.setFont(new Font("굴림", Font.PLAIN, 32));
		btn_tangoInsert.setBounds(145, 100, 300, 90);
		btn_tangoInsert.addActionListener(this);
		mainPanel.add(btn_tangoInsert);
		
		btn_tangoshou = new JButton("단어장");
		btn_tangoshou.setFont(new Font("굴림", Font.PLAIN, 32));
		btn_tangoshou.setBounds(145, 270, 300, 90);
		btn_tangoshou.addActionListener(this);
		mainPanel.add(btn_tangoshou);
		
		btn_tangoQuiz = new JButton("단어 퀴즈");
		btn_tangoQuiz.setFont(new Font("굴림", Font.PLAIN, 32));
		btn_tangoQuiz.setBounds(145, 440, 300, 90);
		btn_tangoQuiz.addActionListener(this);
		mainPanel.add(btn_tangoQuiz);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// 이벤트로부터 이벤트를 발생시킨 소스를 구한다
		if(source == btn_tangoInsert) {
			//등록버튼을 눌렀을 시 메인메뉴를 보이지 않게 하고
			//등록 UI를 visible로 설정한다.
			Insert_Ui insertUi = new Insert_Ui();
			setVisible(false);
			insertUi.setVisible(true);
		}
		else if(source == btn_tangoshou) {
			//아직 미작성
		}
		else if(source == btn_tangoQuiz) {
			
		}
	}
	
	public static void main(String[] args) {
		new UI();
	}
}
